package at.htl.schloemicher.roomfinder.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity(label = "Person")
public class Person {
    String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }
}
