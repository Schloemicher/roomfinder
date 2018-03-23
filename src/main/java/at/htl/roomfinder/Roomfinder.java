package at.htl.roomfinder;

public class Roomfinder {


    public static void main(String[] args) {
        System.out.println("PURGE DATABASE");
        GraphController.purgeDatabase();
        System.out.println("INIT SCHEMA");
        GraphController.initSchema();
    }
}
