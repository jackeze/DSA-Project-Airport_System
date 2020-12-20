import java.util.Scanner;
// TODO: Auto-generated Javadoc
/*

 * Purpose: A airport system that adds and removes planes from runways

 * Status: Complete and thoroughly tested

 * Last update: 12/05/19

 * Submitted:  12/05/19

 * Comment: test suite and sample run attached

 * @author: Anthony Polanco / Jackie Zheng

 * @version: 2019.12.05

 */

/**
 * The Class Driver.
 */
public class Driver {

    /** The sc. */
    static Scanner sc;

    /** The number. */
    static int number;

    /** The round robin. */
    static int roundRobin = 0;

    /** The wait. */
    static int wait = 0;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        sc = new Scanner(System.in);

        ListArrayBasedPlus<Runway> airport = new ListArrayBasedPlus<Runway>();

        ListArrayBasedPlus<Plane> waiting = new ListArrayBasedPlus<Plane>();
        System.out.print("Welcome to the Airport program!\nEnter number of runways: ");
        int runways = sc.nextInt();
        System.out.print(runways);

        for (int i = 1; i <= runways; i++) {
            System.out.print("\nEnter the name of runway number " + i + ":");
            String runwayName = sc.next();
            System.out.print(runwayName);
            Runway run = new Runway(runwayName);
            airport.add(i - 1, run);
        }

        System.out.println("\nSelect from the following menu:\r\n" + "  0. Exit program.\r\n"
                           + "  1. Plane enters the system.\r\n" + "  2. Plane takes off.\r\n"
                           + "  3. Plane is allowed to re-enter a runway.\r\n" + "  4. Runway opens.\r\n"
                           + "  5. Runway closes.\r\n" + "  6. Display info about planes waiting to take off.\r\n"
                           + "  7. Display info about planes waiting to be allowed to re-enter a runway.\r\n"
                           + "  8. Display number of planes who have taken off.");

        boolean check = true;

        while (check == true) {
            try {
                int input;
                System.out.print("\nMake your menu selection now: ");
                input = sc.nextInt();
                System.out.print(input + "\n");

                switch (input) {
                case 0: {
                    check = false;
                    end();
                    break;
                }
                case 1: {

                    enter(airport);

                    break;
                }
                case 2: {
                    takeOff(airport, waiting);

                    break;
                }
                case 3: {
                    reEnter(airport, waiting);

                    break;
                }
                case 4: {
                    runwayOpen(airport);

                    break;
                }
                case 5: {
                    runwayClose(airport, waiting);

                    break;
                }
                case 6: {
                    displayWaitingToTakeOff(airport);

                    break;
                }
                case 7: {
                    displayWaitingToReEnter(waiting);
                    break;
                }
                case 8: {
                    displayNumberOfPlanes();

                    break;
                }
                }
            } catch (ListException | ListIndexOutOfBoundsException | QueueException e) {
                System.out.print("Error Exception!\n");
            }
        }
    }

    /**
     * End.
     */
    public static void end() { // ends the program
        System.out.print("The Airport is closing :Bye Bye....");
    }

    /**
     * enters a plane in the runway
     *
     * @param airport the airport
     */
    public static void enter(ListArrayBasedPlus<Runway> airport) {

        System.out.print("Enter flight number: ");

        String flightNumber = sc.next();
        System.out.print(flightNumber);
        if (checkIfValid(airport, flightNumber)) {
            while (checkIfValid(airport, flightNumber)) {
                System.out.print("Enter flight number: ");

                flightNumber = sc.next();
                System.out.print(flightNumber);
            }
        }

        System.out.print("\nEnter destination: ");
        String destination = sc.next();
        System.out.print(destination+"\n");
        String runway = "";
        int check = -1;
        while (check == -1) {

            System.out.print("Enter runway: ");

            runway = sc.next();
            System.out.print(runway + "\n");
            check = searchRunWay(airport, runway);
            if (check == -1) {
                System.out.print("No such runway! \n");

            }
        }

        Plane newPlane = new Plane(flightNumber, destination, runway);
        int index = check;
        Runway run = airport.get(index);

        run.addPlane(newPlane);
        System.out.print("Flight " + flightNumber + " is now waiting for takeoff on runway " + runway + ".\n");
    }

    /**
     * makes a plane takeoff in a roundRobin
     * fashion and asks the user if they want to make the plane takeoff
     * or await to enter runway
     *
     * @param airport the airport
     * @param waiting the waiting
     */
    public static void takeOff(ListArrayBasedPlus<Runway> airport, ListArrayBasedPlus<Plane> waiting) {

        boolean flag = true;

        for (int i = 0; i < airport.size() && flag; i++) {

            if (airport.get(roundRobin).isEmpty()) {

                roundRobin = (roundRobin + 1) % airport.size();
            } else {

                String name = airport.get(roundRobin).getPlane().getFlightNumber();
                System.out.print("Is flight" + name + "cleared for takeoff(Y/N): ");
                String option = sc.next();
                System.out.print(option+"\n");
                if (option.contentEquals("Y")) {

                    airport.get(roundRobin).removePlane();
                    System.out.print("Flight " + name + " has now taken off from runway "
                                     + airport.get(roundRobin).getRunwayName()+"\n");
                    number++;
                } else {

                    waiting.add(wait, airport.get(roundRobin).removePlane());
                    System.out.print("Flight " + name + " is now waiting to be allowed to re-enter a runway \n");

                    wait++;
                }
                roundRobin = (roundRobin + 1) % airport.size();
                flag = false;

            }

        }

        if (flag == true) {

            System.out.print("All runways are empty \n");
        }

    }

    /**
     * re-enters a plane specified by the user
     * thats waiting to re-enter a runway
     *
     * @param airport the airport
     * @param waiting the waiting
     */
    public static void reEnter(ListArrayBasedPlus<Runway> airport, ListArrayBasedPlus<Plane> waiting) {

        System.out.print("Enter flight number: ");

        String flightNumber = sc.next();
        System.out.print(flightNumber + "\n");
        boolean flag = true;
        if (waiting.isEmpty()) {
            System.out.println("There are no planes waiting for clearance!\n ");

        } else {


            for (int i = 0; i < waiting.size() && flag; i++) {

                if (waiting.get(i).getFlightNumber().equals(flightNumber)) {

                    flag = false;

                    Plane plane = waiting.get(i);

                    int index = searchRunWay(airport, plane.getRunway());

                    Runway run = airport.get(index);

                    run.addPlane(plane);
                    waiting.remove(i);
                    System.out.print("Flight " + plane.getFlightNumber() + " is now waiting for takeoff on runway "
                                     + run.getRunwayName() + "\n ");


                }
            }

        }

    }

    /**
     * opens a new runway
     *
     * @param airport the airport
     */
    public static void runwayOpen(ListArrayBasedPlus<Runway> airport) {
        boolean flag = true;
        while (flag == true) {
            System.out.print("Enter the name of the new runway : ");
            String name = sc.next();
            System.out.print(name + "\n");
            if (searchRunWay(airport, name) == -1) {
                airport.add(airport.size(), new Runway(name));
                System.out.print("Runway " + name + " has opened.\n");
                flag = false;
            } else {
                System.out.print("Runway " + name + " already exists, please choose another name.\n");
            }
        }
    }

    /**
     * closes a runway specified by the user and asks the user where
     * to move all of the planes in the runway
     *
     * @param airport the airport
     * @param waiting the waiting
     */
    public static void runwayClose(ListArrayBasedPlus<Runway> airport, ListArrayBasedPlus<Plane> waiting) {
        int way = 0;
        boolean flag = true;
        int check = -1;
        String runway = "";
        while (check == -1) {

            System.out.print("Enter runway: ");

            runway = sc.next();
            System.out.print(runway + "\n");
            check = searchRunWay(airport, runway);
            if (check == -1) {
                System.out.print("No such runway! \n");

            }
        }

        for (int i = 0; i < airport.size() && flag; i++) {

            if (airport.get(i).getRunwayName().contentEquals(runway)) {
                flag = false;
                way = i;
                Runway run = airport.get(i);
                for (int j = 0; j <= run.getSize(); j++) {

                    if (!run.isEmpty()) {

                        Plane plane = run.removePlane();

                        System.out.print("Enter new runway for plane " + plane.getFlightNumber() + ":");

                        String newRunName = sc.next();
                        System.out.print(newRunName + "\n");
                        while (run.getRunwayName().equals(newRunName) || check == -1) {

                            System.out.print("Enter new runway for plane " + plane.getFlightNumber() + ":");

                            newRunName = sc.next();
                            System.out.print(newRunName + "\n");
                            check = searchRunWay(airport, newRunName);

                            if(run.getRunwayName().equals(newRunName)) {
                                System.out.print("This is the runway that is closing!\n");
                            }

                            if (check == -1) {
                                System.out.print("No such runway! \n");

                            }
                        }


                        System.out.print("Flight " + plane.getFlightNumber() + "  is now waiting for takeoff on runway "
                                         + newRunName + "\n");
                        plane.setRunway(newRunName);

                        Runway newRun = airport.get(searchRunWay(airport, newRunName));

                        newRun.addPlane(plane);

                    }
                }
            }
        }


        for(int i = 0; i < waiting.size(); i++) {
            if(waiting.get(i).getRunway().equals(runway)) {

                Plane plane = waiting.get(i);
                waiting.remove(i);
                System.out.print("Enter new runway for plane " + plane.getFlightNumber() + ":");

                String newRunName = sc.next();
                System.out.print(newRunName + "\n");
                while (runway.equals(newRunName)) {

                    System.out.print("This is the runway that is closing!");
                    System.out.print("Enter new runway for plane " + plane.getFlightNumber() + ":");
                    newRunName = sc.next();
                }

                System.out.print(
                    "Flight " + plane.getFlightNumber() + " has changed runway to " + newRunName + "\n");

                plane.setRunway(newRunName);
            }

        }

        System.out.println("Runway "+runway+" has been closed.");
        airport.remove(way);
    }

    /**
     * Display waiting to take off.
     *
     * @param airport the airport
     */
    public static void displayWaitingToTakeOff(ListArrayBasedPlus<Runway> airport) {
        for(int i = 0; i < airport.size(); i++) {
            if(airport.get(i).getQueue().isEmpty() != true) {
                System.out.print(
                    "These planes are waiting for takeoff on runway " + airport.get(i).getRunwayName() + " :");
                System.out.println(airport.get(i).toString());
            } else {
                System.out
                .print("No planes are waiting for takeoff on runway " + airport.get(i).getRunwayName() + "!\n");
            }
        }
    }

    /**
     * Display waiting to re enter.
     *
     * @param waiting the waiting
     */
    public static void displayWaitingToReEnter(ListArrayBasedPlus<Plane> waiting) {
        if(waiting.isEmpty() == true) {
            System.out.print("No planes are waiting to be cleared to re-enter a runway!\n");
        } else {
            System.out.print("These planes are waiting to be cleared to re-enter a runway:\n");

            for(int i = 0; i < waiting.numItems; i++) {
                Plane plane = waiting.get(i);
                System.out.print("Flight " + plane.getFlightNumber() + " to " + plane.getDestination() + "\n");
            }
        }

    }

    /**
     * Display number of planes.
     */
    public static void displayNumberOfPlanes() {
        System.out.print(number + " planes have taken off from the airport.\n");
    }

    /**
     * this class searches through the airport for a specific class
     *
     * @param airport the airport
     * @param name the name
     * @return index returns the index of where the runway is and if its not in it returns -1
     */
    public static int searchRunWay(ListArrayBasedPlus<Runway> airport, String name) {
        boolean flag = false;
        int index = -1;
        for(int i = 0; i < airport.size() && flag != true; i++) {
            if(airport.get(i) != null) {
                if((airport.get(i)).getRunwayName().equals(name)) {
                    flag = true;
                    index = i;
                }

            }
        }
        return index;
    }

    /**
     * this class checks if the flightnumber is valid or not and if theres any duplicates
     *
     * @param airport the airport
     * @param flightNumber the flight number
     * @return true, if successful
     */
    public static boolean checkIfValid(ListArrayBasedPlus<Runway> airport, String flightNumber) {
        boolean alreadyInuse = false;

        for(int i = 0; i < airport.size(); i++) {

            Runway run = airport.get(i);
            for(int j = 0; i < run.getSize(); i++) {

                if(run.getPlane().getFlightNumber().contentEquals(flightNumber)) {

                    System.out.print("Enter another flightNumber already in use \n");
                    alreadyInuse = true;

                }
            }
        }
        return alreadyInuse;

    }

}


