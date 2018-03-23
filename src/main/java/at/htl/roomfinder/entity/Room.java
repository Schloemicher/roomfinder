package at.htl.roomfinder.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@NodeEntity
public class Room{

    @Id
    @GeneratedValue
    private Long id;

    String name;

    @Relationship(type = "NEXT")
    Set<Path> paths;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Set<Path> getPaths() {
        return paths;
    }


    public Room addPathTo(Room nextRoom){
        Random r = new Random();
        double pathLength = (double) ((r.nextInt(75) + 62) / 10d);
        return addPathTo(nextRoom,pathLength);
    }

    public Room addPathTo(Room nextRoom, double pathLength){
        if(paths == null)
            paths = new LinkedHashSet<>();
        paths.add(new Path(this,nextRoom,pathLength));
        return this;
    }
}
