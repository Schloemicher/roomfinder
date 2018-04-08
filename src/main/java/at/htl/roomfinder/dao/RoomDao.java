package at.htl.roomfinder.dao;

import at.htl.roomfinder.business.Neo4jSessionFactory;
import at.htl.roomfinder.entity.Room;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import javax.ejb.Stateless;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
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

    /**
     * Find a Room by name
     * @param name
     * @return
     */
    public Room findByName(@NotNull @NotBlank String name){
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            Collection<Room> rooms = session.loadAll(
                    Room.class,
                    new Filter("name", ComparisonOperator.EQUALS, name), 1);
            return rooms.iterator().next();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Room findById(long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            return session.load(Room.class,id);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
