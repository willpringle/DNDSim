package wpringle;

import java.util.ArrayList;

/** . */
public abstract class Space {

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

}
