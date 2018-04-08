package at.htl.roomfinder.web;

import at.htl.roomfinder.dao.RoomDao;
import at.htl.roomfinder.entity.Path;
import at.htl.roomfinder.entity.Room;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class IndexController {

    @Inject
    private RoomDao roomDao;

    private Room curRoom;

    private String errorMessage;
    private Path curPath;

    @PostConstruct
    public void init(){
        this.curRoom = new Room();
    }

    //ROOM management
    public void doSaveRoom(){
        if(!curRoom.getName().isEmpty()){
            roomDao.persist(curRoom);
            errorMessage = "";
        }
        else {
            errorMessage = "Enter a name for the new room!";
        }
    }

    public void doDeleteRoom(){
        if(!curRoom.getName().isEmpty()){
            roomDao.delete(curRoom);
        } else {
            errorMessage = "Couldn't delete room!";
        }
    }

    //PATH management
    public void doSavePath(){
        if(!curPath.getStartRoom().getName().isEmpty() &&
                !curPath.getEndRoom().getName().isEmpty()){
            roomDao.persist(curRoom);
            errorMessage = "";
        }
        else {
            errorMessage = "Enter a name for the new room!";
        }
    }

    public void doDeletePath(){
        if(!curRoom.getName().isEmpty()){
            roomDao.delete(curRoom);
        } else {
            errorMessage = "Couldn't delete room!";
        }
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
}
