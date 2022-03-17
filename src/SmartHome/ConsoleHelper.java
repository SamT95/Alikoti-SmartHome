package SmartHome;

import java.util.Scanner;

public class ConsoleHelper {
    public void print(String message) { System.out.println(message); }
    public int roomID;

    public int getInt(String instructions) {
        Scanner input = new Scanner(System.in);
        print(instructions);
        int i = 0;
        try {
            i = input.nextInt();
        } catch (Exception e) {
            print("Please enter an integer");
            getInt(instructions);
        }
        return i;
    }

    public String getString(String instructions) {
        Scanner input = new Scanner(System.in);
        print(instructions);
        String s = "";
        try {
            s = input.nextLine();
        } catch (Exception e) {
            print("Please enter a string");
            getString(instructions);
        }
        return s;
    }

    public void populateHome(SmartHome home) {
        for (int i = 0; i < home.roomCount(); i++) { //Loop runs from 0 to the number of rooms
            String roomName = getString("Please provide a name for room number " + (i + 1) + ": ");
            home.newRoom(roomName); // Add the new room to the smart home
        }
        for (int i = 0; i < home.plugCount(); i++) { //Loop runs from 0 to the number of plugs
            populateRoom(home); // Call the function which asks for the room and device
        }
    }

    public void addRoomsToHome(SmartHome home, int oldRoomCount) {
        for (int i = oldRoomCount; i < home.roomCount; i++) {
            String roomName = getString("Please provide a name for room number " + (i + 1) + ": ");
            home.newRoom(roomName);
        }
    }

    public void addNewDevice(SmartHome home, int numNewDevices) {
        for (int i = 0; i < numNewDevices; i++) {
            String deviceName = getString("Please provide a name for the new device: ");
            home.newDevice(deviceName);
        }
    }

    public void populateRoom(SmartHome home) {
        print("ENTER PLUG INFORMATION BELOW");
        print("ROOMS AVAILABLE: " + home.displayRooms());
        print("Using the above list, please select a room for this plug (integer input only): ");
        int optionLevel = getInt("");
        attachDevice(home, optionLevel-1);
    }

    public void displayRooms(SmartHome home) {
        print("Please select a room: ");
        print(home.displayRooms());
        int optionLevel = getInt("");
        roomID = optionLevel-1;
        print(home.displayInfo(optionLevel-1));
    }

    public void attachDevice(SmartHome home, int roomNumber) {
        print(home.displayDeviceList());
        int optionLevel = getInt("Using the above list, please select the device to attach to the smart plug (integer input only): ");
        home.createPlug(roomNumber, optionLevel-1);
    }

    public void createDashboard(SmartHome home) {
        print("---------------DASHBOARD--------------");
        print(home.dashboardInfo());
    }

    public void displayMenu(SmartHome home) {
        print("-------------MENU OPTIONS-------------\n" +
        "1 - House level options\n" +
        "2 - Room level options\n" +
        "3 - Plug level options\n" +
        "4 - System options");
        int optionLevel = getInt("Please choose an option from the above list (integer input only): ");
        switch(optionLevel) {
            case 1:
                displayHouseOptions(home);
            case 2:
                displayRooms(home);
                displayRoomOptions(home);
            case 3:
                displayPlugOptions(home);
            case 4:
                displaySystemOptions(home);
        }
    }

    public void displayHouseOptions(SmartHome home) {
        print("HOUSE LEVEL OPTIONS\n" +
        "1 - Switch all plugs off\n" +
        "2 - Switch all plugs on\n" +
        "Please choose an option from the above list (integer input only): ");
        int optionLevel = getInt("Select an option");
        switch (optionLevel) {
            case 1:
                home.allPlugsOff(true, optionLevel);
                break;
            case 2:
                home.allPlugsOn(true, optionLevel);
                break;
        }
        updateDashboard(home);
    }

    public void displayRoomOptions(SmartHome home) {
        print("ROOM LEVEL OPTIONS\n" +
                "1 - Switch all plugs in this room off\n" +
                "2 - Switch all plugs in this room on\n" +
                "3 - Select a device in this room and toggle its on/off status");
        int optionLevel = getInt("");
        switch (optionLevel) {
            case 1:
                home.allPlugsOff(false, roomID);
                break;
            case 2:
                home.allPlugsOn(false, roomID);
                break;
            case 3:
                int plugID = getInt("Please select the plug you want to toggle by entering its ID: ");
                int status = getInt("Enter 1 to turn this plug on or 0 to turn this plug off");
                home.togglePlug(plugID-1, status);
                break;
        }
        updateDashboard(home);
    }

    public void displayPlugOptions(SmartHome home) {
        print(home.displayAllPlugs());
        int plugSelected = getInt("Please select a plug from the list above by entering its ID: ");
        plugSelected -= 1;
        print("PLUG LEVEL OPTIONS\n" +
                "1 - Switch plug off\n" +
                "2 - Switch plug on\n" +
                "3 - Change attached device\n" +
                "4 - Move plug to different room");
        int optionLevel = getInt("Please select an option");
        switch (optionLevel) {
            case 1:
                home.togglePlug(plugSelected, 0);
                break;
            case 2:
                home.togglePlug(plugSelected, 1);
                break;
            case 3:
                print(home.displayDeviceList());
                int newDevice = getInt("Please select a new device to attach to smart plug (integer input only): \n");
                newDevice -= 1;
                home.updateAttachedDevice(plugSelected, newDevice);
                break;
            case 4:
                print(home.displayRooms());
                int newRoomNumber = getInt("Please select a new room to move this smart plug to (integer input only): ");
                newRoomNumber -= 1;
                home.updateRoom(plugSelected, newRoomNumber);
        }
        updateDashboard(home);
    }

    public void displaySystemOptions(SmartHome home) {
        print("SYSTEM OPTIONS\n" +
        "1 - Add more smart plugs\n" +
        "2 - Add devices that can be attached to smart plugs\n" +
        "3 - Add more rooms to the house");
        int optionLevel = getInt("Please select an option");
        switch (optionLevel) {
            case 1:
                int numNewPlugs = getInt("Please enter the number of smart plugs you want to add: ");
                home.addPlugs(numNewPlugs);
                for (int i = 0; i < numNewPlugs; i++) {
                    populateRoom(home);
                }
                break;
            case 2:
                int numNewDevices = getInt("Please enter the number of devices you want to add: ");
                home.addDevices(numNewDevices);
                addNewDevice(home, numNewDevices);
                break;
            case 3:
                int numNewRooms = getInt("Please enter the number of rooms you want to add: ");
                int roomCount = home.roomCount;
                home.addRooms(numNewRooms);
                addRoomsToHome(home, roomCount);
        }
        updateDashboard(home);
    }


    public void updateDashboard(SmartHome home) {
        createDashboard(home);
        displayMenu(home);
    }

}
