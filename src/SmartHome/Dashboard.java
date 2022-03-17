package SmartHome;

public class Dashboard {

    public static void main(String[] args) {
        ConsoleHelper console = new ConsoleHelper();
        int numRooms = console.getInt("How many rooms are there in this property?");
        int numPlugs = console.getInt("How many plugs do you want to place in this property?");
        SmartHome home = new SmartHome(numPlugs, numRooms);
        console.populateHome(home);

        while(true) {
            console.createDashboard(home);
            console.displayMenu(home);
        }
    }
}
