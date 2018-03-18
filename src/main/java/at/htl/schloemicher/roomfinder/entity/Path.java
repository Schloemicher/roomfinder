package at.htl.schloemicher.roomfinder.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "NEXT")
public class Path{

    @StartNode
    Room startRoom;

    @EndNode
    Room endRoom;

    double pathLength;

    public Path() {
    }
}
