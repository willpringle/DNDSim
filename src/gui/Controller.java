package gui;

import java.util.ArrayList;
import wpringle.*;

public class Controller {
	private Level myLevel;
	private GuiDemo myGui;
	private String description;
	private Chamber currentChamber;

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
		
		// create a vairbale to return
		ArrayList<String> nameList = new ArrayList<>();
		
		// add the name of the chambers to the list
		for (int i = 0; i < chambers.size(); i++) {
			nameList.add("Chamber " + i);
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
		
		for (int i = 0; i < numChambers; i++) {
			if (name.equals("Chamber " + i)) {
				currentChamber = myLevel.getChamberList().get(i);
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

}
