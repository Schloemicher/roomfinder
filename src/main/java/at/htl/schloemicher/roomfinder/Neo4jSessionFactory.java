package at.htl.schloemicher.roomfinder;

import at.htl.schloemicher.roomfinder.entity.Room;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.metadata.MetaData;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4jSessionFactory {

    private Configuration configuration;
    private SessionFactory sessionFactory;
    private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

    public static Neo4jSessionFactory getInstance() {
        return factory;
    }

    // prevent external instantiation
    private Neo4jSessionFactory() {

        configuration = new Configuration.Builder()
                .uri("bolt://localhost:7687")
                .credentials("roomfinder", "1234")
                .build();

        System.out.println(configuration.getURI());

        sessionFactory = new SessionFactory(configuration,
//                "Person.entity.roomfinder.schloemicher.htl.at",
                "entity.roomfinder.schloemicher.htl.at"
//                "roomfinder.schloemicher.htl.at"
        );
    }

    public Session getNeo4jSession() {
        return sessionFactory.openSession();
    }
}
