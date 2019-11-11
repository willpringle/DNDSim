/*
 * The point of this class is to have an object that will store the level.
 * This will allows the level to be generated and store the generated level.
 */

package wpringle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public final class Level {

    /**
     * List of all the chambers.
     */
    private ArrayList<Chamber> myChambers;

    /**
     * HashMap of all the connections.
     */
    private HashMap<Door, Door> myConnections;

    /**
     * List of all the doors.
     */
    private ArrayList<Door> myDoors;

    /**
     * Constructor for the level. Initializes important instance variables.
     */
    public Level() {
        myChambers = new ArrayList<Chamber>();
        myConnections = new HashMap<>();
        myDoors = new ArrayList<>();

        // create the level
        algo();
    }

    /**
     * The algorithm used to generate a level.
     */
    private void algo() {
        // make 5 chambers
        makeChambers(5);

        // add all the doors to a single list
        addDoors();

        // make all the doors connected
        connect();
    }

    /**
     * Connects the doors together in a graph.
     */
    private void connect() {
        int j = 0;
        int num = myChambers.get(0).getDoors().size();
        Door door;

        // for every chamber
        for (int i = 0; i < myChambers.size(); i++) {

            // for every door in each chamber
            for (int k = 0; k < myChambers.get(i).getDoors().size(); k++) {
                // find a door to connect
                door = findDoor(i);

                // if a door is returned
                if (door != null) {
                    // connect it (both ways)
                    myConnections.put(myDoors.get(j), door);
                    myConnections.put(door, myDoors.get(j));
                }

                j++;
            }
        }
    }

    /**
     * Finds a door that is not already mapped to another door.
     * @param currentChamber the index of the current chamber.
     * @return doors.get(j) unmapped door to be returned.
     */
    private Door findDoor(final int currentChamber) {
        ArrayList<Door> doors = new ArrayList<>();

        // add all the doors to it other than from the current chamber
        for (int i = 0; i < 5; i++) {
            if (i != currentChamber) {
                doors.addAll(myChambers.get(i).getDoors());
            }
        }
        // shuffle the list of doors
        Collections.shuffle(doors);

        // return the first door that isn't mapped to anything
        for (int j = 0; j < doors.size(); j++) {
            if (myConnections.get(doors.get(j)) == null) {
                return doors.get(j);
            }
        }
        return null;
    }

    /**
     * Finds the chamber that the door belongs to.
     * @param door the door in question.
     * @return i the chamber the door belongs to.
     */
    private int doorBelongsTo(final Door door) {
        // for every chamber
        for (int i = 0; i < myChambers.size(); i++) {

            // for every door in that chamber
            for (int j = 0; j < myChambers.get(i).getDoors().size(); j++) {

                // if the doors have the same address
                if (door == myChambers.get(i).getDoors().get(j)) {
                    return i;
                }
            }
        }

        // return -1 if failure
        return -1;
    }

    /** Add the doors from the chambers to one list. */
    private void addDoors() {
        for (int i = 0; i < myChambers.size(); i++) {
            myDoors.addAll(myChambers.get(i).getDoors());
        }
    }

    /**
     * Creates chambers and adds them to the arrayLIst of chambers.
     * @param numChambers the number of chambers.
     */
    private void makeChambers(final int numChambers) {
        // make the chambers
        for (int i = 0; i < numChambers; i++) {
            myChambers.add(new Chamber());
        }
    }

    /**
     * Prints the description of the level.
     * @return info the string description of the level.
     */
    public String getDescription() {
        String info = "";
        Door tempDoor;

        int j = 0;
        int num = myChambers.get(0).getDoors().size();

        for (int i = 0; i < myChambers.size(); i++) {
            System.out.println("Chamber #" + i + " description is");
            System.out.println(myChambers.get(i).getDescription());

            System.out.println("\nChamber #" + i + " has "
            + myChambers.get(i).getDoors().size() + " doors");

            for (int k = 0; k < myChambers.get(i).getDoors().size(); k++) {
                tempDoor = myDoors.get(j);
                num = doorBelongsTo(myConnections.get(tempDoor));

                System.out.println("Door #" + k
                + passageDescription(num));

                System.out.println(tempDoor.getDescription());

                j++;
            }
        }


        return info;
    }

    /**
     * Outputs the description of the passage.
     * @param c2 the index of the chamber arrayList.
     * @return desc a description of the passage.
     */
    private String passageDescription(final int c2) {
        String desc = "";

        desc = " leads through a passage with a door to chamber #" + c2;


        return desc;
    }

}
