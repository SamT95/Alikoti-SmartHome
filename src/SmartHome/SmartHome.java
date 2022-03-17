package SmartHome;

public class SmartHome {

    private SmartPlugs[] plugs;
    public String[] roomNames;
    public String[] deviceList;
    private int currentIndex;
    public int roomCount;
    public int plugCount;
    private int counter;
    public int deviceCount = 5;
    public int roomID;
    public int deviceID;

    public SmartHome(int numPlugs, int numRooms) {
        plugCount = numPlugs;
        plugs = new SmartPlugs[plugCount];
        roomCount = numRooms;
        roomNames = new String[numRooms];
        deviceList = new String[deviceCount];
        populateDeviceArray();
    }

    public int plugCount() { return plugCount; }
    public int roomCount() { return roomCount;}

    public void newRoom(String name) {
        roomNames[currentIndex] = name;
        currentIndex++;
    }



    public String displayRooms() {
        String s = "";
        for (int i = 0; i < roomCount(); i++) {
            s += (i+1) + " - " + roomNames[i] + " | ";
        }
        return s;
    }

    public void createPlug(int roomNumber, int deviceNumber) {
        plugs[counter] = new SmartPlugs(roomNumber, deviceNumber, roomID, deviceID);
        counter++; roomID++; deviceID++;
    }

    public void addRooms(int numNewRooms) {
        String[] arrayItems = duplicateArray(roomNames, roomCount);
        roomCount += numNewRooms;
        roomNames = duplicateArray(arrayItems, roomCount);
    }

    public void addPlugs(int numNewPlugs) {
        SmartPlugs[] arrayItems = new SmartPlugs[plugCount];
        for (int i = 0; i < plugCount(); i++) {
            arrayItems[i] = plugs[i];
        }
        plugCount += numNewPlugs;
        plugs = new SmartPlugs[plugCount];
        for (int i = 0; i < arrayItems.length; i++) {
            plugs[i] = arrayItems[i];
        }
    }


    public void addDevices(int numNewDevices) {
        String[] arrayItems = duplicateArray(deviceList, deviceCount);
        deviceCount += numNewDevices;
        deviceList = duplicateArray(arrayItems, deviceCount);
    }

    public void newDevice(String newDeviceName) {
        for (int i = 0; i < deviceCount; i++) {
            if (deviceList[i] == null) {
                deviceList[i] = newDeviceName;
            }
        }
    }

    public String[] duplicateArray(String[] originalArray, int size) {
        String[] arrayItems = new String[size]; //create new array with increased size
        for (int i = 0; i < originalArray.length; i++) {
            arrayItems[i] = originalArray[i]; //copy each item from the original array to the new array
        }
        return arrayItems; //return the new array
    }

    public String displayInfo(int roomID) {
        String s = "";
        for (int i = 0; i < plugCount(); i++) {
            if (plugs[i].getRoomNumber() == roomID) {
                s += "SmartPlug | attached to: " + deviceList[plugs[i].getDeviceName()] + " | room: " + roomNames[plugs[i].getRoomName()]
                        + " | ID: " + (plugs[i].getRoomID()+1) + " | status: " + plugs[i].getStatus() + "\n";
            }
        }
        return s;
    }

    public String displayAllPlugs() {
        String s = "";
        for (int i = 0; i < plugCount(); i++) {
            s += "SmartPlug | attached to: " + deviceList[plugs[i].getDeviceName()] + " | room: " + roomNames[plugs[i].getRoomName()]
                    + " | ID: " + (plugs[i].getRoomID()+1) + " | status: " + plugs[i].getStatus() + "\n";
        }
        return s;
    }

    public String dashboardInfo() {
        String s = "";
        for (int i = 0; i < roomCount(); i++) {
            s += "ROOM: " + (i+1) + "\n" + displayInfo(i) + "\n";
        }
        return s;
    }

    public void allPlugsOff(boolean wholeHouse, int roomID) {
        if (wholeHouse) {
            for (int i = 0; i < plugCount(); i++) {
                plugs[i].setStatus(false);
            }
        } else {
            for (int i = 0; i < plugCount(); i++) {
                if (plugs[i].getRoomNumber() == roomID) {
                    plugs[i].setStatus(false);
                }
            }
        }
    }

    public void allPlugsOn(boolean wholeHouse, int roomID) {
        if (wholeHouse) {
            for (int i = 0; i < plugCount(); i++) {
                plugs[i].setStatus(true);
            }
        } else {
            for (int i = 0; i < plugCount(); i++) {
                if (plugs[i].getRoomNumber() == roomID) {
                    plugs[i].setStatus(true);
                }
            }
        }
    }

    public void togglePlug(int plugID, int status) {
        boolean toggle;
        if (status == 1) { toggle = true;}
        else if (status == 0) { toggle = false;}
        else {return;}
        plugs[plugID].setStatus(toggle);
    }

    public String displayDeviceList() {
        String s = "";
        for (int i = 0; i < deviceArraySize(); i++ ) {
            s += "" + (i+1) +  " - " + deviceList[i] + "\n";
        }
        return s;
    }

    public void populateDeviceArray() {
        deviceList[0] = "Lamp";
        deviceList[1] = "TV";
        deviceList[2] = "Computer";
        deviceList[3] = "Phone Recharger";
        deviceList[4] = "Heater";
    }

    public int deviceArraySize() {
        return deviceList.length;
    }

    public void updateAttachedDevice(int plugID, int newDevice) {
        plugs[plugID].changeDevice(newDevice);
    }

    public void updateRoom(int plugID, int newRoom) {
        plugs[plugID].changeRoom(newRoom);
    }

}
