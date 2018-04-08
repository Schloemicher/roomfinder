package at.htl.roomfinder.entity;

public class GraphJsonPath {
    private long source;
    private long target;
    private String caption;

    public GraphJsonPath(Path path) {
        source = path.getStartRoom().getId();
        target = path.getEndRoom().getId();
        caption = String.valueOf(path.pathLength);
    }

    public long getSource() {
        return source;
    }

    public void setSource(long source) {
        this.source = source;
    }

    public long getTarget() {
        return target;
    }

    public void setTarget(long target) {
        this.target = target;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
