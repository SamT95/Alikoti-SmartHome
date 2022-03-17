package SmartHome;


public class AttachedDevices {
    private int deviceNumber;



    public AttachedDevices(int deviceNumber, int deviceID) {
        this.deviceNumber = deviceNumber;
    }

    public int getDeviceName() {
        return this.deviceNumber;
    }

    public void setDevice(int newDeviceNumber) {
        this.deviceNumber = newDeviceNumber;
    }

}
