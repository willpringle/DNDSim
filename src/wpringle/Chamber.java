package wpringle;

import dnd.models.ChamberContents;
import dnd.models.ChamberShape;
import dnd.models.Monster;
import dnd.models.Treasure;
import dnd.models.Exit;
import java.util.ArrayList;
import java.util.Random;
import dnd.exceptions.NotProtectedException;
import java.io.Serializable;


public final class Chamber extends Space implements Serializable {

    /** . */
    private ChamberContents myContents;
    /** . */
    private ChamberShape myShape;
    /** . */
    private ArrayList<Door> myDoors;
    /** . */
    private ArrayList<Monster> myMonsters;
    /** . */
    private ArrayList<Treasure> myTreasures;
    /** . */
    private ArrayList<Exit> myExits;
    /** . */
    private int numExits;
	
	private String myDescription;

    /** Constructor. */
    public Chamber() {
        Random rand = new Random();

        myContents = new ChamberContents();
        myContents.setDescription(rand.nextInt(20) + 1);

        myShape = ChamberShape.selectChamberShape(rand.nextInt(20) + 1);

        numExits = myShape.getNumExits();
        myExits = myShape.getExits();

        myMonsters = new ArrayList<Monster>();
        myTreasures = new ArrayList<Treasure>();

        // add an exit to make sure every chamber has a door
        if (numExits == 0) {
            myShape.setNumExits(1);
            numExits = myShape.getNumExits();
            myExits = myShape.getExits();
        }

        // make sure there are less than 5 exits he he he
        if (numExits > 4) {
            myShape.setNumExits(8);
            numExits = myShape.getNumExits();
            myExits = myShape.getExits();
        }

        // check if there is a monster to spawn
        if (myContents.getDescription().contains("monster")) {
            Monster monster = new Monster();
            monster.setType(rand.nextInt(20) + 1);

            myMonsters.add(monster);

        }

        // check if there is treasure to spawn
        if (myContents.getDescription().contains("treasure")) {
            Treasure treasure = new Treasure();
            treasure.setDescription(rand.nextInt(20) + 1);

            treasure.setContainer(rand.nextInt(20) + 1);

            myTreasures.add(treasure);
        }

        // door stuff
        myDoors = new ArrayList<Door>();

        // make doors
        for (int i = 0; i < numExits; i++) {
            myDoors.add(new Door());
        }
		
		myDescription = null;

    }

    /** makes the sections. */
    @Override
    public void makeSections() {
        Door door;

        // add as many doors as there are exits
        for (int i = 0; i < myShape.getNumExits(); i++) {

            door = new Door(this); // I want to put THIS in
            myDoors.add(door);
        }
    }

    /** @param theShape chamber shape.
    @param theContents the Chamber Conten. */
    public Chamber(final ChamberShape theShape,
    final ChamberContents theContents) {
        this();
        myShape = theShape;
        myContents = theContents;

    }

    /** @return myShape the chamber shape. */
    public ChamberShape getShape() {
        return myShape;
    }

    /** @param shape the chamber shape. */
    public void setShape(final ChamberShape shape) {
        myShape = shape;
    }

    /** @return myContents the chamber contents. */
    public ChamberContents getContents() {
        return myContents;
    }

    /** @param contents the chamber contents. */
    public void setContents(final ChamberContents contents) {
        myContents = contents;
    }

    /** @param doors an arrayList of doors. */
    public void setDoors(final ArrayList<Door> doors) {
        myDoors = doors;
    }

    /** @return myDoors returns the arraylist of doors. */
    @Override
    public ArrayList<Door> getDoors() {
        return myDoors;
    }

    /** @param newDoor the new door to set. */
    @Override
    public void setDoor(final Door newDoor) {
        myDoors.add(newDoor);
    }

    /** @return myMonsters the arrayList of monsters. */
    public ArrayList<Monster> getMonsters() {
        return myMonsters;
    }

    /** @param monsters the arraylist of monsters to be set. */
    public void setMonsters(final ArrayList<Monster> monsters) {
        myMonsters = monsters;
    }

    /** @param theMonster the monster to be set. */
    public void addMonster(final Monster theMonster) {
        myMonsters.add(theMonster);
    }

    /** @return myTreasures the arrayList of treasures. */
    public ArrayList<Treasure> getTreasureList() {
        return myTreasures;
    }

    /** @param treasures the arrayList of treasures. */
    public void setTreasures(final ArrayList<Treasure> treasures) {
        myTreasures = treasures;
    }

    /** @param theTreasure the treasure. */
    public void addTreasure(final Treasure theTreasure) {
        myTreasures.add(theTreasure);
    }

    /** @return description of the chamber. */
    @Override
    public String getDescription() {
        String description;
        String protectedText = "";
		
		if (myDescription == null) {

			// add the header
			description = addFormatting();

			// add room type and shape description
			description = addLine(description, "CHAMBER\n");
			description = description + getShapeDescription();

			// add monster and treausre information
			description = description + getMonsterDescription();
			description = description + getTreasureDescription();

			// add the door information
			description = description + getDoorsDescription();

			// add the footer
			description = description + addFormatting();
			
			myDescription = description;
		}
		
		

        return myDescription;
    }

    private String getDoorsDescription() {
        String description = "";

        description = addLine(description, "There are " + myDoors.size()
        + " door(s) (not including the one you came in through)\n");

        for (int i = 0; i < numExits; i++) {
            description = addLine(description, "Door by the "
            + myExits.get(i).getLocation() + " "
            + myExits.get(i).getDirection() + "\n");
        }

        return description;
    }

    private String getTreasureDescription() {
        String description = "";
        String protectedText = "";

        // print the treasure
        if (myContents.getDescription().contains("treasure")) {
            description = addLine(description, "But there is some sweet "
            + "Treasure!" + "\n");
            description = addLine(description, "Treasure is: "
            + myTreasures.get(0).getDescription() + "\n");
            description = addLine(description, "Treasure is contained in "
            + myTreasures.get(0).getContainer() + "\n");

            try {
                protectedText = "Treasure is protected by: "
                + myTreasures.get(0).getProtection() + "\n";
            } catch (NotProtectedException e) {
                protectedText = "The treasure is not protected\n";
            }

            description = addLine(description, protectedText);
        }

        return description;
    }

    private String getMonsterDescription() {
        String description = "";

        // get the monster information
        if (myContents.getDescription().contains("monster")) {
            description = addLine(description, "OH NO!! THERE IS A "
            + myMonsters.get(0).getDescription() + "!!\n");
        }

        return description;
    }

    private String getShapeDescription() {
        String description = "";

        // describe the shape of the room
        description = addLine(description, "The room is "
        + myContents.getDescription() + "\n");
        description = addLine(description, "The area of the room is "
        + myShape.getArea() + " feet squared\n");
        description = addLine(description, "The room is shaped this way: "
        + myShape.getDescription() + " feet squared\n");

        return description;
    }

    /** @return line the line with proper formatting. */
    private String addFormatting() {
        String line = "";

        for (int i = 0; i < 60; i++) {
            line = line + "☺";
        }

        line = line + "\n";

        return line;
    }

    /** @param input the input.
     * @param text the text to add.
     * @return input + "☺\t" + text the text iwth proper formatting. */
    private String addLine(final String input, final String text) {
        return input + "☺\t" + text;
    }
	
	@Override
	public void setString(String newDescription) {
		myDescription = newDescription;
		
		System.out.println("test this thing test \n\n" + newDescription);
	}

}
