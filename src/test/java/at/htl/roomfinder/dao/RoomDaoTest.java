package at.htl.roomfinder.dao;

import org.junit.BeforeClass;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RoomDaoTest {

    private static RoomDao dao;

    @BeforeClass
    public static void init() {
        dao = new RoomDao();
    }

    @org.junit.Test
    public void persist() {
    }

    @org.junit.Test
    public void delete() {
    }

    @org.junit.Test
    public void findByName() {
        assertThat("Didn't find E01", dao.findByName("E01").getName(), is("E01"));
    }
}