package gui;

import java.util.ArrayList;
import wpringle.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;


public class Controller {
	private Level myLevel;
	private GuiDemo myGui;
	private String description;
	private Space currentChamber;

    public Controller (GuiDemo theGui) {
        myGui = theGui;
        myLevel = new Level();
		description = "";
    }

	// TODO change name of method to getChamberList or maybe even getSpaceList
    public ArrayList<String> getNameList(){
        // get the chambers
		ArrayList<Chamber> chambers = new ArrayList<>();
		chambers = myLevel.getChamberList();
		
		// get the passages
		ArrayList<Passage> passages = new ArrayList<>();
		passages = myLevel.getPassageList();
		
		// create a vairbale to return
		ArrayList<String> nameList = new ArrayList<>();
		
		// add the name of the chambers to the list
		for (int i = 0; i < chambers.size(); i++) {
			nameList.add("Chamber " + i);
		}
		
		// add the name of the passage to the list
		for (int i = 0; i < passages.size(); i++) {
			nameList.add("Passage " + i);
		}
		
        return nameList;
    }

    public void reactToButton(String selectedString){
        System.out.println("react to buttpn + " + selectedString);
		description = selectedString;
    }

    public String getNewDescription(){
        //return "this would normally be a description pulled from the model of the Dungeon level.";
        return currentChamber.getDescription();
    }
	
	public void setCurrentSpace(String name) {
		int numChambers = myLevel.getChamberList().size();
		int numPassages = myLevel.getPassageList().size();
		int i = 0;
		
		for (i = 0; i < numChambers; i++) {
			if (name.equals("Chamber " + i)) {
				currentChamber = myLevel.getChamberList().get(i);
			}
		}
		
		for (int j = i; j < numPassages + i; j++) {
			if (name.equals("Passage " + (j-i))) {
				currentChamber = myLevel.getPassageList().get(j-i);
			}
		}
		
	}
	
	public ArrayList<String> getDoors() {
		if (currentChamber == null) {
			return null;
		}
		
		ArrayList<Door> doors =  currentChamber.getDoors();
		ArrayList<String> names = new ArrayList<>();
		
		for (int i = 0; i < doors.size(); i++) {
			names.add("Door" + i);
		}
		
		return names;
	}
	
	public void setChamberDescription(String name) {
		currentChamber.setString(name);
	}
	
	// writing to a file
	
	public void save(File theFile) throws Exception {
		FileOutputStream FOStream = new FileOutputStream(theFile);
		ObjectOutputStream OOStream = new ObjectOutputStream(FOStream);
		OOStream.writeObject(myLevel);
		
		OOStream.close();
	}
	
	public void open(File theFile) throws Exception {
		FileInputStream FIStream = new FileInputStream(theFile);
		ObjectInputStream OIStream = new ObjectInputStream(FIStream);
		
		myLevel = (Level) OIStream.readObject();
		
		OIStream.close();
	}
}
