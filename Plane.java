/*

 * Purpose: makes planes sets and gets attributes for the plane

 * Status: Complete and thoroughly tested

 * Last update: 12/05/19

 * Submitted:  12/05/19

 * Comment: test suite and sample run attached

 * @author: Anthony Polanco / Jackie Zheng

 * @version: 2019.12.05

 */
/**
 * The Class Plane which has the flight number, destination, and runway.
 */
public class Plane {

    /** The flight number. */
    private String flightNumber;

    /** The destination. */
    private String destination;

    /** The runway. */
    private String runway;

    /**
     * Creates a new plane.
     *
     * @param flight the flight
     * @param dest the destination
     * @param run the run
     */
    public Plane(String flight, String dest, String run) {
        flightNumber = flight;
        destination = dest;
        runway = run;
    }

    /**
     * Gets the flight number.
     *
     * @return the flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * Sets the flight number.
     *
     * @param flightNumber the new flight number
     */
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    /**
     * Gets the destination.
     *
     * @return the destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Sets the destination.
     *
     * @param destination the new destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * Gets the runway.
     *
     * @return the runway
     */
    public String getRunway() {
        return runway;
    }

    /**
     * Sets the runway.
     *
     * @param runway the new runway
     */
    public void setRunway(String runway) {
        this.runway = runway;
    }
}
