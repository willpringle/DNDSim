package gui;

import java.util.ArrayList;
import wpringle.*;

public class Controller {
	private Level myLevel;
	private GuiDemo myGui;


    public Controller(GuiDemo theGui){
        myGui = theGui;
        myLevel = new Level();
    }

    public ArrayList<String> getNameList(){
        
		ArrayList<String> dude = new ArrayList<>();
		
		dude.add(myLevel.getDescription());
		
        return dude;
    }

    public void reactToButton(){
        System.out.println("Thanks for clicking test!");
    }

    public String getNewDescription(){
        //return "this would normally be a description pulled from the model of the Dungeon level.";
        return String.join("\n", getNameList());
    }

}
