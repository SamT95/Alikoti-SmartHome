package SmartHome;

public class Rooms {
    private int roomNumber;
    private int roomID;

    public Rooms(int roomNumber, int roomID) {
        this.roomNumber = roomNumber;
        this.roomID = roomID;
    }

    public int getRoomName() {
        return roomNumber;
    }

    public void setRoom(int newRoomNumber) {this.roomNumber = newRoomNumber;}

    public int getRoomID() {
        return roomID;
    }

    public int getRoomNumber() {
        return roomNumber;
    }


}
