package wpringle;

import dnd.models.Monster;
import java.util.HashMap;
import java.util.ArrayList;
/*
A passage begins at a door and ends at a door.  It may have many
other doors along the way

You will need to keep track of which door is the "beginning" of the
passage so that you know how to
*/

public final class Passage extends Space {
    // these instance variables are suggestions only
    // you can change them if you wish.

    /** . */
    private ArrayList<PassageSection> mySections;
    /** . */
    private int numSections;
    /** . */
    private ArrayList<Door> myDoors;
    /** . */
    private HashMap<PassageSection, Door> doorMap;

    /** constructor. */
    public Passage() {
        mySections = new ArrayList<PassageSection>();
        numSections = 0;
        myDoors = new ArrayList<Door>();
        doorMap = new HashMap<>();
    }

    /**
     *  @return myDoors the arrayList of doors.
     */
    @Override
    public ArrayList<Door> getDoors() {
        //gets all of the doors in the entire passage
        return myDoors;
    }

    /**
     * @param i the index of the door the user wants.
     * @return myDoors.get(i) the door.
     */
    public Door getDoor(final int i) {
        //returns the door in section 'i'. If there is no door, returns null

        return myDoors.get(i); // error handling?
    }

    /**
     * @param theMonster monster to be added.
     * @param i the index to replace the monster with the one to be added
     */
    public void addMonster(final Monster theMonster, final int i) {
        // adds a monster to section 'i' of the passage
        if (i <= numSections) {
            mySections.get(i).addMonster(theMonster);
        }
    }

    /**
     * @param i the index of monster they want.
     * @return monster the monster from index i.
     */
    public Monster getMonster(final int i) {
        //returns Monster door in section 'i'. If there is no Monster, returnull
        Monster monster = new Monster();
        monster = null;

        if (i <= numSections) {
            monster = mySections.get(i).getMonster();
        }

        return monster;
    }

    /** @param toAdd the passageSection to add. */
    public void addPassageSection(final PassageSection toAdd) {

        mySections.add(toAdd);
        numSections++;
    }

    /** @return myDoors.size() the amount of chambers. */
    public int getNumChambers() {
        return myDoors.size();
    }


    @Override
    public void setDoor(final Door newDoor) {
        //should add a door connection to the current Passage Section
        mySections.get(numSections).addDoor(newDoor);
    }

    @Override
    public String getDescription() {
        String description;
        int numDoors = 0;

        description = "PASSAGE\nPassage broken down into " + numSections
        + " sections with " +  this.getNumChambers() + " Chambers\n";

        // add the description of each section
        for (int i = 0; i < numSections; i++) {
            description = description
            + mySections.get(i).getDescription()
            + "\n";

            // add monster and everything
            if (mySections.get(i).getMonster() != null) {
                description = description
                + mySections.get(i).getMonster().getDescription() + "\n";
            }

            // print the chamber if there is one
            if (mySections.get(i).getChamberDoor()) {
                description = description + "\n"
                + myDoors.get(numDoors).getDescription(); // not i
                numDoors++;
            }
        }

        description = description + "\n";



        return description;
    }

    // I want to delete this method.
    /**
     *  Create passages until there is a dead end or more than 10 appear.
     */
    @Override
    public void makeSections() {
        boolean end = false;
        Door door;

        for (int i = 0; i < 10 && !end; i++) {

            // create a new passage
            this.addPassageSection(new PassageSection());

            // if
            if (mySections.get(i).getChamberDoor()) {
                door = new Door(this);

                if (mySections.get(i).getChamberDoorArchway()) {
                    door.setArchway(true);
                }

                myDoors.add(door);
            }

            // if end then exit
            if (mySections.get(i).getDeadEnd()) {

                end = true;

                // if there has been no doors generated, change it to spawn door
                if (myDoors.size() < 1) {
                    mySections.set(i, new PassageSection(3));
                    myDoors.add(new Door(this));
                }

                break;
            }
        }

        // if there were no dead ends after 10 chambers, spawn a dead end
        if (!end) {
            this.addPassageSection(new PassageSection(19));
        }
    }
}
