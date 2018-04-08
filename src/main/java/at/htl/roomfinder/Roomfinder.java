package at.htl.roomfinder;

import at.htl.roomfinder.business.GraphController;
import org.neo4j.driver.internal.InternalPath;
import org.neo4j.driver.v1.types.Path;

import java.util.Iterator;
import java.util.Map;

public class Roomfinder {


    public static void main(String[] args) {
        GraphController.purgeDatabase();
        GraphController.initSchema();

        for (Map<String, Object> item : GraphController.queryShortestPath()) {
            for (Map.Entry<String, Object> innerTest : item.entrySet()) {
                InternalPath p = (InternalPath) innerTest.getValue();
                Iterator<Path.Segment> segmentIterator = p.iterator();
                while (segmentIterator.hasNext()) {
                    Path.Segment segment = segmentIterator.next();
                    System.out.println(
                            String.format("%s [%.2f] %s",
                                    segment.start().get("name").asString(),
                                    segment.relationship().get("pathLength").asFloat(),
                                    segment.end().get("name").asString()));
                }
            }
        }
    }
}
