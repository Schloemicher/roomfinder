package at.htl.roomfinder.web;

import at.htl.roomfinder.dao.PathDao;
import at.htl.roomfinder.dao.RoomDao;
import at.htl.roomfinder.entity.Path;
import at.htl.roomfinder.entity.Room;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class IndexController {

    @Inject
    private RoomDao roomDao;

    @Inject
    private PathDao pathDao;

    private Room curRoom;

    private String errorMessage;
    private Path curPath;

    private String shortestPathFrom;
    private String shortestPathTo;
    private long shortestPathFromId;
    private long shortestPathToId;

    @PostConstruct
    public void init() {
        this.curRoom = new Room();
        this.curPath = new Path();
        this.curPath.setStartRoom(new Room());
        this.curPath.setEndRoom(new Room());

        shortestPathFrom = "E01";
        shortestPathTo = "E03";
    }

    //ROOM management
    public void doSaveRoom() {
        if (!curRoom.getName().isEmpty()) {
            roomDao.persist(curRoom);
            errorMessage = "";
        } else {
            errorMessage = "Enter a name for the new room!";
        }
    }

    public void doDeleteRoom() {
        if (!curRoom.getName().isEmpty()) {
            roomDao.delete(curRoom);
        } else {
            errorMessage = "Couldn't delete room!";
        }
    }

    //PATH management
    public void doSavePath() {
        if (!curPath.getStartRoom().getName().isEmpty() &&
                !curPath.getEndRoom().getName().isEmpty()) {

            curPath.setStartRoom(roomDao.findByName(curPath.getStartRoom().getName()));
            curPath.setEndRoom(roomDao.findByName(curPath.getEndRoom().getName()));

            if (pathDao.persist(curPath))
                errorMessage = "";
            else
                errorMessage = "Error while saving path";
        } else {
            errorMessage = "Enter the rooms";
        }
    }

    public void doFindShortestPath(){
        shortestPathFromId = roomDao.findByName(shortestPathFrom).getId();
        shortestPathToId = roomDao.findByName(shortestPathTo).getId();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Room getCurRoom() {
        return curRoom;
    }

    public void setCurRoom(Room curRoom) {
        this.curRoom = curRoom;
    }

    public Path getCurPath() {
        return curPath;
    }

    public String getShortestPathFrom() {
        return shortestPathFrom;
    }

    public void setShortestPathFrom(String shortestPathFrom) {
        this.shortestPathFrom = shortestPathFrom;
    }

    public String getShortestPathTo() {
        return shortestPathTo;
    }

    public void setShortestPathTo(String shortestPathTo) {
        this.shortestPathTo = shortestPathTo;
    }

    public long getShortestPathFromId() {
        return shortestPathFromId;
    }

    public void setShortestPathFromId(long shortestPathFromId) {
        this.shortestPathFromId = shortestPathFromId;
    }

    public long getShortestPathToId() {
        return shortestPathToId;
    }

    public void setShortestPathToId(long shortestPathToId) {
        this.shortestPathToId = shortestPathToId;
    }
}
