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
        Pathfinding pathfinding = PathFinder.findAll();
        if (pathfinding != null) {
            return Response.ok(pathfinding).build();
        } else {
            return Response.ok().build();
        }
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
