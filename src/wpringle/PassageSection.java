package wpringle;

import dnd.models.Monster;
import java.util.ArrayList;
import java.util.Random;

/* Represents a 10 ft section of passageway */

public final class PassageSection {

    /** . */
    private ArrayList<Door> myDoors;
    /** . */
    private ArrayList<Monster> myMonsters;
    /** . */
    private int myRoll;
    /** . */
    private boolean foreward;
    /** . */
    private boolean archwayRight;
    /** . */
    private boolean archwayLeft;
    /** . */
    private boolean passageTurnLeft;
    /** . */
    private boolean passageTurnRight;
    /** . */
    private boolean hasStairs;
    /** . */
    private boolean end;
    /** . */
    private boolean hasDoorToChamber;
    /** . */
    private boolean chamberDoorArchway;
    /** . */
    private boolean hasMonster;
    /** . */
    private String passedString;

    /**
     *  constructor.
     */
    public PassageSection() {
        //
        Random rand = new Random();

        //sets up the 10 foot section with default settings
        myDoors = new ArrayList<Door>();
        myMonsters = new ArrayList<Monster>();
        myRoll = rand.nextInt(20) + 1;
        this.initializeBooleans();
        this.initalizeOnRoll(myRoll);
        passedString = null;
    }

    /**
     *  constructor that initializes with a roll.
     *  @param roll the roll.
     */
    public PassageSection(final int roll) {
        // call constructor to initialize instance variables to default
        this();

        myRoll = roll;

        // reInitialize
        this.initalizeOnRoll(myRoll);

    }

    /**
     *  .
     */
    private void initializeBooleans() {
        foreward = false;
        archwayRight = false;
        archwayLeft = false;
        passageTurnLeft = false;
        passageTurnRight = false;
        hasStairs = false;
        end = false;
        hasDoorToChamber = false;
        chamberDoorArchway = false;
        hasMonster = false;
    }


    /**
     *
     *  @return door.
     */
    public Door getDoor() {
        if (myDoors.size() > 0) {
            return myDoors.get(0);
        }
        return null;
    }

    /**
     *  @param  door the door.
     */
    public void addDoor(final Door door) {
        myDoors.add(door);
    }

    /**
     *  @return monster.
     */
    public Monster getMonster() {
        Monster monster = new Monster();
        monster = null;

        if (myMonsters.size() > 0) {
            monster = myMonsters.get(0);
        }

        return monster;
    }

    /**
     *  @param monster monster to be added.
     */
    public void addMonster(final Monster monster) {

        myMonsters.add(monster);

    }

    /**
     *  @return description.
     */
    public String getDescription() {
        Table table = new Table();
        String description;

        String head = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        + "~~~~";

        // start the description
        description = head + "\n~\tPASSAGE SECTION\n";

        // if no string was passed, print the roll
        if (passedString == null) {
            description = description + "~\t" + table.getDescription(myRoll)
            + "\n";
        } else { // otherwise print the string
            description = description + passedString + "\n";
        }

        return description;
    }

    /**
     *  @param roll the roll.
     */
    private void initalizeOnRoll(final int roll) {

        if (roll < 3) {
            foreward = true;

        } else if (roll < 6) {
            hasDoorToChamber = true;
            end = true;

        } else if (roll < 8) {
            archwayRight = true;
            hasDoorToChamber = true;
            chamberDoorArchway = true;

        } else if (roll < 10) {
            archwayLeft = true;
            hasDoorToChamber = true;
            chamberDoorArchway = true;

        } else if (roll < 12) {
            passageTurnLeft = true;

        } else if (roll < 14) {
            passageTurnRight = true;

        } else if (roll < 17) {
            hasDoorToChamber = true;
            chamberDoorArchway = true;

        } else if (roll < 18) {
            hasStairs = true;

        } else if (roll < 20) {
            end = true;

        } else if (roll == 20) {
            hasMonster = true;
        }
    }

    /**
     *  @return end.
     */
    public boolean getDeadEnd() {
        return end;
    }

    /**
     *  @return hasDoorToChamber.
     */
    public boolean getChamberDoor() {
        return hasDoorToChamber;
    }

    /**
     *  @return chamberDoorArchway.
     */
    public boolean getChamberDoorArchway() {
        return chamberDoorArchway;
    }

    /**
     *  @return myRoll;
     */
    public int getRoll() {
        return myRoll;
    }
}
