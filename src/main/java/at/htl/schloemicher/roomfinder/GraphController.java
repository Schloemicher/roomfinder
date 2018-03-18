package at.htl.schloemicher.roomfinder;

import at.htl.schloemicher.roomfinder.entity.Person;
import at.htl.schloemicher.roomfinder.entity.Room;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

public class GraphController {

    public static void initSchema(){
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            Room r = new Room("E01");
            session.save(new Person("Hans"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
