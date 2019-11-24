package wpringle;

import java.util.ArrayList;
import java.io.Serializable;

/** . */
public abstract class Space implements Serializable {

    /** @return description. */
    public abstract  String getDescription();

    /**
     *  @param theDoor door to be set.
     */
    public abstract void setDoor(Door theDoor);

    /** . */
    public abstract void makeSections();

    /**
     *  @return the array of doors.
     */
    public abstract ArrayList<Door> getDoors();
	
	/**
     *  @param the new description for the thing.
     */
	public abstract void setString(String newDescription);

}
