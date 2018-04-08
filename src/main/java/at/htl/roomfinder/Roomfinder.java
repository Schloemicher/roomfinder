package at.htl.roomfinder;

import at.htl.roomfinder.business.GraphController;
import at.htl.roomfinder.business.PathFinder;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.v1.types.Path;

import java.util.Iterator;
import java.util.Map;

public class Roomfinder {


    public static void main(String[] args) {
//        GraphController.purgeDatabase();
//        GraphController.initSchema();

        PathFinder.findPath(169,171);
    }
}
