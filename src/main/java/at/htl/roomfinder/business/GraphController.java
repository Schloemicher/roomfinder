package at.htl.roomfinder.business;

import at.htl.roomfinder.entity.Room;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import java.util.*;

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
            Room re6 = new Room("E06");

            session.save(re1);
            session.save(re2);
            session.save(re3);
            session.save(re4);
            session.save(re5);
            session.save(re6);

            re1.addPathTo(re2,4)
                    .addPathTo(re3,10)
                    .addPathTo(re5,1)
                    .addPathTo(re4);
            session.save(re1);

            re5.addPathTo(re6,1);
            session.save(re5);

            re6.addPathTo(re3,1);
            session.save(re6);

            re2.addPathTo(re3,3);
            session.save(re2);

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
    
    public static String prettyMap(Map<String, Object> map){
        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<String, Object>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, Object> entry = iter.next();
            sb.append(entry.getValue());
            sb.append('=').append('"');
            sb.append(entry.getValue());
            sb.append('"');
            if (iter.hasNext()) {
                sb.append(',').append(' ');
            }
        }
        return sb.toString();
    }

    public static Iterable<Map<String, Object>> queryShortestPath(){
        String query = "MATCH (cs:Room {name: 'E01'}),(ms:Room {name: 'E03'}), p = shortestPath((cs)-[*]-(ms))\n" +
                "WHERE length(p)> 1\n" +
                "RETURN p";

        return Neo4jSessionFactory.getInstance().getNeo4jSession().query(query, Collections.EMPTY_MAP);
    }
}
