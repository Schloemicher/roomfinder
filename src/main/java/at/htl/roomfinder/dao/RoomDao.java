package at.htl.roomfinder.dao;

import at.htl.roomfinder.business.Neo4jSessionFactory;
import at.htl.roomfinder.entity.Room;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import javax.ejb.Stateless;
import java.util.Collections;

@Stateless
public class RoomDao {

    public boolean persist(Room newRoom) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            session.save(newRoom);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean delete(Room curRoom) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            session.query(
                    String.format("MATCH (n:Room { name: '%s' }) DETACH DELETE n",
                            curRoom.getName()),
                    Collections.EMPTY_MAP);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
