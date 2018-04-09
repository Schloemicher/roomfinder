package at.htl.roomfinder.business;

import at.htl.roomfinder.dao.PathDao;
import at.htl.roomfinder.dao.RoomDao;
import at.htl.roomfinder.entity.GraphJsonPath;
import at.htl.roomfinder.entity.Path;
import at.htl.roomfinder.entity.Pathfinding;
import at.htl.roomfinder.entity.Room;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PathFinder {

    public static Pathfinding findPath(long fromId, long toId) {
        try {

            Client client = Client.create();
            client.addFilter(
                    new HTTPBasicAuthFilter("roomfinder", "1234"));

            WebResource webResource = client
                    .resource("http://localhost:7474/db/data/node/" +
                            fromId + "/path");

            String input = "{\n" +
                    "  \"to\" : \"http://localhost:7474/db/data/node/" +
                    toId + "\",\n" +
                    "  \"cost_property\" : \"pathLength\",\n" +
                    "  \"relationships\" : {\n" +
                    "    \"type\" : \"NEXT\",\n" +
                    "    \"direction\" : \"out\"\n" +
                    "  },\n" +
                    "  \"algorithm\" : \"dijkstra\"\n" +
                    "}";

            ClientResponse response = webResource.type("application/json")
                    .accept("application/json")
                    .post(ClientResponse.class, input);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            JSONObject output = response.getEntity(JSONObject.class);
            JSONArray nodes = output.getJSONArray("nodes");
            JSONArray relationships = output.getJSONArray("relationships");

            RoomDao roomDao = new RoomDao();
            PathDao pathDao = new PathDao();

            Pathfinding pathfinding = new Pathfinding();
            for (int i = 0; i < nodes.length(); i++) {
                String url = (String) nodes.get(i);
                long id = Long.parseLong(url.substring(url.lastIndexOf('/') + 1));
                pathfinding.getNodes().add(roomDao.findById(id));
            }

            for (int i = 0; i < relationships.length(); i++) {
                String url = (String) relationships.get(i);
                long id = Long.parseLong(url.substring(url.lastIndexOf('/') + 1));
                pathfinding.getEdges().add(new GraphJsonPath(pathDao.findById(id)));
            }
            return pathfinding;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Pathfinding findAll() {
        List<Room> rooms = new ArrayList<>(Neo4jSessionFactory.getInstance().getNeo4jSession().loadAll(Room.class));
        List<Path> paths = new ArrayList<>(Neo4jSessionFactory.getInstance().getNeo4jSession().loadAll(Path.class));
        Pathfinding pathfinding = new Pathfinding();
        pathfinding.getNodes().addAll(rooms);

        for (Path path : paths) {
            pathfinding.getEdges().add(new GraphJsonPath(path));
        }
        return pathfinding;
    }
}
