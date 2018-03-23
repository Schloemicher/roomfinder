package at.htl.roomfinder;

public class Roomfinder {


    public static void main(String[] args) {
        GraphController.purgeDatabase();
        GraphController.initSchema();
    }
}
