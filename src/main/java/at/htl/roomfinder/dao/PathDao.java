package at.htl.roomfinder.dao;

import at.htl.roomfinder.business.Neo4jSessionFactory;
import at.htl.roomfinder.entity.Path;
import at.htl.roomfinder.entity.Room;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.transaction.Transaction;

import javax.ejb.Stateless;
import java.util.Collections;

@Stateless
public class PathDao {

    public boolean persist(Path newPath) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            newPath.getStartRoom().addPath(newPath);
            session.save(newPath.getStartRoom());

            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean delete(Path curPath) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
//            session.query(
//                    String.format("MATCH (n:Room { name: '%s' }) DETACH DELETE n",
//                            curRoom.getName()),
//                    Collections.EMPTY_MAP);
            tx.commit();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public Path findById(long id) {
        Session session = Neo4jSessionFactory.getInstance().getNeo4jSession();
        try (Transaction tx = session.beginTransaction()) {
            return session.load(Path.class,id,1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
