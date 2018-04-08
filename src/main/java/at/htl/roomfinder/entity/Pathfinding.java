package at.htl.roomfinder.entity;

import java.util.ArrayList;
import java.util.List;

public class Pathfinding {
    private List<GraphJsonPath> edges;
    private List<Room> nodes;

    public Pathfinding(List<GraphJsonPath> edges, List<Room> nodes) {
        this.edges = edges;
        this.nodes = nodes;
    }

    public Pathfinding() {
        this.edges = new ArrayList<>();
        this.nodes = new ArrayList<>();
    }

    public List<GraphJsonPath> getEdges() {
        return edges;
    }

    public void setEdges(List<GraphJsonPath> edges) {
        this.edges = edges;
    }

    public List<Room> getNodes() {
        return nodes;
    }

    public void setNodes(List<Room> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "Pathfinding{" +
                "edges=" + edges +
                ", nodes=" + nodes +
                '}';
    }
}
