package at.htl.roomfinder.entity;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.neo4j.ogm.annotation.*;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

@NodeEntity
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("caption")
    String name;

    @Relationship(type = "NEXT")
    Set<Path> paths;

    @Transient
    String type;

    public Room() {
        type = "room";
    }

    public Room(String name) {
        this();
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    @JsonIgnore
    public Set<Path> getPaths() {
        return paths;
    }


    public Room addPathTo(Room nextRoom) {
        Random r = new Random();
        double pathLength = (double) ((r.nextInt(75) + 62) / 10d);
        return addPathTo(nextRoom, pathLength);
    }

    public Room addPathTo(Room nextRoom, double pathLength) {
        if (paths == null)
            paths = new LinkedHashSet<>();
        paths.add(new Path(this, nextRoom, pathLength));
        return this;
    }

    public Room addPath(Path p){
        paths.add(p);
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("%d: %s", id, name);
    }
}
