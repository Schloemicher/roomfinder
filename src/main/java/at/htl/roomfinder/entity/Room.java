package at.htl.roomfinder.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Set;

@NodeEntity
public class Room{

    @Id
    @GeneratedValue
    private Long id;

    String name;

//    @Relationship(type = "NEXT")
//    Set<Path> paths;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
