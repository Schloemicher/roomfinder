package at.htl.roomfinder;

import at.htl.roomfinder.entity.Path;
import at.htl.roomfinder.entity.Room;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

public class GraphController {

    public static void initSchema() {
        System.out.println("INIT SCHEMA");
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            Room re1 = new Room("E01");
            Room re2 = new Room("E02");
            Room re3 = new Room("E03");
            Room re4 = new Room("E04");
            Room re5 = new Room("E05");

            Room r11 = new Room("101");
            Room r12 = new Room("102");
            Room r13 = new Room("103");
            Room r14 = new Room("104");
            Room r15 = new Room("105");

            session.save(re1);
            session.save(re2);
            session.save(re3);
            session.save(re4);
            session.save(re5);

            re1.addPathTo(re2).addPathTo(re3).addPathTo(re4).addPathTo(re5).addPathTo(r13);
            session.save(re1);

            r11.addPathTo(r12).addPathTo(r13).addPathTo(r14).addPathTo(r15);
            session.save(r11);

            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void purgeDatabase() {
        System.out.println("PURGE DATABASE");
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            session.purgeDatabase();
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
