package at.htl.roomfinder;

import at.htl.roomfinder.entity.Room;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

public class GraphController {

    public static void initSchema() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            Room r1 = new Room("E01");
            Room r2 = new Room("E02");
            Room r3 = new Room("E03");
            Room r4 = new Room("E04");
            Room r5 = new Room("E05");

            session.save(r1);
            session.save(r2);
            session.save(r3);
            session.save(r4);
            session.save(r5);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void purgeDatabase() {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            session.purgeDatabase();
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
