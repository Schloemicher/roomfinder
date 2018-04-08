package at.htl.roomfinder.rest;

import at.htl.roomfinder.business.GraphController;
import at.htl.roomfinder.business.Neo4jSessionFactory;
import at.htl.roomfinder.business.PathFinder;
import at.htl.roomfinder.entity.Pathfinding;
import at.htl.roomfinder.entity.Room;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.ogm.model.Result;
import org.neo4j.ogm.session.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("graph")
public class GraphResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response allNodes() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();

        Collection<Room> rooms = session.loadAll(Room.class);

        Map<String, Object> map = new LinkedHashMap<>();
        Object o = session.query("match (n) return n", map);

        return Response.serverError().build();
    }

    @GET
    @Path("shortest/{fromId}/{toId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response shortestPath(@PathParam("fromId") long fromId, @PathParam("toId") long toId) {
        Pathfinding pathfinding = PathFinder.findPath(fromId,toId);
        if (pathfinding != null) {
            return Response.ok(pathfinding).build();
        } else {
            return Response.ok().build();
        }
    }
}
