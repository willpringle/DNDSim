package wpringle;

import dnd.models.Trap;
import java.util.ArrayList;
import java.util.Random;
import java.io.Serializable;

public class Door implements Serializable {

    /** Boolean value on whether door is open or closed. */
    private boolean open;
    /** Boolean value on whether door is an archway. */
    private boolean archway;
    /** Boolean value on whether door is trapped. */
    private boolean trapped;
    /** Boolean value on whether door is locked. */
    private boolean locked;
    /** The trap object. */
    private Trap myTrap;
    /** ArrayList of spaces. */
    private ArrayList<Space> mySpaces;
	
	private String myDescription;

    /** constructor. */
    public Door() {
        myTrap = new Trap();

        setRandom();

        if (archway) {
            this.setArchway(true);
        }

        if (locked) {
            open = false;
        }

        // space stuff
        mySpaces = new ArrayList<Space>();
		
		myDescription = null;

    }

    /** @param space1 the first space. @param space 2 the second space*/
    public Door(final Space space1) {
        // call the constructor
        this();

        // add the first space
        mySpaces.add(space1);

        // add the other type of space as the second space
        Space space2 = new Chamber();
        if (space1 instanceof Chamber) {
            space2 = new Passage();
        } else {
            space2 = new Chamber();
        }

        // add the second space to the arraylist of spaces
        mySpaces.add(space2);

    }

    private void setRandom() {
        Random rand = new Random();

        myTrap.setDescription(rand.nextInt(20) + 1);

        trapped = (rand.nextInt(20) == 1); // 1 in 20 chance
        locked = (rand.nextInt(6) == 1);
        archway = (rand.nextInt(10) == 1);
        open = (rand.nextInt(2) == 1);

    }

    /** @param flag whether or not trapped.
    @param roll the roll of teh trap. */
    public void setTrapped(final boolean flag, final int... roll) {
        trapped = flag;

        // if door is trapped, set the trap description
        if (flag && roll.length > 0) {
            myTrap = new Trap();

            if (roll.length > 0) {
                myTrap.setDescription(roll[0]);
            } else {
                Random rand = new Random();
                myTrap.setDescription(rand.nextInt(20) + 1);
            }
        }
    }

    /** @param flag whether or not there is an archway. */
    public void setOpen(final boolean flag) {

        // if its an archway, force the door open
        if (!archway) {
            open = flag;
        }

    }

    /** @param flag whether or not there is an archway. */
    public void setArchway(final boolean flag) {
        archway = flag;

        if (archway) {
            trapped = false;
            locked = false;
            open = true;
        }
    }

    /** @return trapped. */
    public boolean isTrapped() {
        return trapped;
    }

    /** @return open. */
    public boolean isOpen() {
        return open;
    }

    /** @return archway. */
    public boolean isArchway() {
        return archway;
    }

    /** @return myTrap.getDescription(). */
    public String getTrapDescription() {
        return myTrap.getDescription();
    }

    /** @return mySpaces. */
    public ArrayList<Space> getSpaces() {
        return mySpaces;
    }

    /** @param spaceOne first space.
        @param spaceTwo second space. */
    public void setSpaces(final Space spaceOne, final Space spaceTwo) {
        //identifies the two spaces with the door
        // this method should also call the addDoor method from Space
        mySpaces.clear();

        spaceOne.setDoor(new Door());
        spaceTwo.setDoor(new Door());

        mySpaces.add(spaceOne);
        mySpaces.add(spaceTwo);
    }

    /** @param i index of the space to replace.
      * @param space the space to replace. */
    public void replaceSpace(final int i, final Space space) {
        if (space != null) {
            space.setDoor(new Door());
        }

        mySpaces.set(i, space);
    }

    /** @param i the index of the space the user wants.
    @return mySpaces.get(i) the space. */
    public Space getSpace(final int i) {

        return mySpaces.get(i);
    }

    /** @return description. */
    public String getDescription() {
        String description;
		
		if (myDescription == null) {

			// print the header
			description = addFormatting();
			description = addLine(description, "DOOR\n");

			// add further details
			if (trapped) {
				description = addLine(description, "There is a trap:"
				+ myTrap.getDescription() + "\n");
			}

			if (archway) {
				description = addLine(description, "Actually its an archway\n");
			}

			if (open) {
				description = addLine(description, "The door is open\n");
			} else {
				description = addLine(description, "The door is closed\n");
			}

			if (locked) {
				description = addLine(description, "The door is locked\n");
			}
			
			myDescription = description;
			
		}

        return myDescription;
    }

    /** @return line. */
    private String addFormatting() {
        String line = "";

        for (int i = 0; i < 60; i++) {
            line = line + "D";
        }

        line = line + "\n";

        return line;
    }

    /**
     * @param input info.
     * @param text info.
     * @return input plus formatting.
     */
    private String addLine(final String input, final String text) {
        return input + "D\t" + text;
    }
	
	public void setString(String newDescription) {
		myDescription = newDescription;
	}

}
