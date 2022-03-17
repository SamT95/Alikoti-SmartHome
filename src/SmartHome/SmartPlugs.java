package SmartHome;

public class SmartPlugs {
    private Rooms room;
    private AttachedDevices devices;
    private int roomNumber;
    private int deviceNumber;
    private boolean status;
    private int roomID;
    private int deviceID;

    public SmartPlugs(int roomNumber, int deviceNumber, int roomID, int deviceID) {
        this.roomNumber = roomNumber;
        this.deviceNumber = deviceNumber;
        this.status = false;
        this.roomID = roomID;
        this.deviceID = deviceID;
        setRoomNumber(roomNumber);
        setDeviceNumber(deviceNumber);
    }

    public void setRoomNumber(int roomNumber) { room = new Rooms(roomNumber, roomID); }

    public int getRoomName() { return room.getRoomName(); }

    public int getRoomNumber() { return room.getRoomNumber();}

    public void setDeviceNumber(int deviceNumber) {devices = new AttachedDevices(deviceNumber, deviceID); }

    public void changeDevice(int newDeviceNumber) {devices.setDevice(newDeviceNumber);}

    public void changeRoom(int roomNumber) {room.setRoom(roomNumber);}

    public int getDeviceName() {
        return devices.getDeviceName();
    }

    public String getStatus() { return this.status ? "On" : "Off";}

    public void setStatus(boolean status) { this.status = status;}

    public int getRoomID() { return room.getRoomID(); }


}
