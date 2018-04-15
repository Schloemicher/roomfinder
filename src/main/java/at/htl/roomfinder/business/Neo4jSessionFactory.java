package at.htl.roomfinder.business;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

public class Neo4jSessionFactory {

    private Configuration configuration;
    private SessionFactory sessionFactory;
    private static Neo4jSessionFactory factory = new Neo4jSessionFactory();

    public static Neo4jSessionFactory getInstance() {
        return factory;
    }

    private Neo4jSessionFactory() {

        configuration = new Configuration.Builder()
                .uri("bolt://localhost:7687")
                .credentials("neo4j", "roomfinder")  //credentials
                .build();

        System.out.println(configuration.getURI());

        sessionFactory = new SessionFactory(configuration,
                "at.htl.roomfinder.entity"
        );
    }

    public Session getNeo4jSession() {
        return sessionFactory.openSession();
    }
}
