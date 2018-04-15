package at.htl.roomfinder.entity;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "NEXT")
public class Path{

    @StartNode
    Room startRoom;

    @EndNode
    Room endRoom;

    @Property
    double pathLength;

    @Id
    @GeneratedValue
    Long id;

    public Path() {
    }

    public Path(Room startRoom, Room endRoom, double pathLength) {
        this.startRoom = startRoom;
        this.endRoom = endRoom;
        this.pathLength = pathLength;
    }

    public Room getStartRoom() {
        return startRoom;
    }

    public void setStartRoom(Room startRoom) {
        this.startRoom = startRoom;
    }

    public Room getEndRoom() {
        return endRoom;
    }

    public void setEndRoom(Room endRoom) {
        this.endRoom = endRoom;
    }

    public double getPathLength() {
        return pathLength;
    }

    public void setPathLength(double pathLength) {
        this.pathLength = pathLength;
    }

    @Override
    public String toString() {
        return "Path{" +
                "startRoom=" + startRoom +
                ", endRoom=" + endRoom +
                ", pathLength=" + pathLength +
                ", id=" + id +
                '}';
    }
}
