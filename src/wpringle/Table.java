package wpringle;

/** . */
public final class Table {

    /** . */
    public Table() {

    }

    /**
     *  @param i the roll.
     *  @return description the description for the roll.
     */
    public String getDescription(final int i) {
        int roll = i;
        String description = "";

        if (roll < 3) {
            description = "passage goes straight for 10 ft";
        } else if (roll < 6) {
            description = "passage ends in Door to a Chamber";
        } else if (roll < 8) {
            description = "archway (door) to right (main passage continues "
            + "straight for";
        } else if (roll < 10) {
            description = "archway (door) to left (main passage continues "
            + "straight for 10 ft";
        } else if (roll < 12) {
            description = "passage turns to left and continues for 10 ft";
        } else if (roll < 14) {
            description = "passage turns to right and continues for 10 ft";
        } else if (roll < 17) {
            description = "passage ends in archway (door) to chamber";
        } else if (roll < 18) {
            description = "Stairs, (passage contunues straight for 10 ft)";
        } else if (roll < 20) {
            description = "Dead End";
        } else if (roll == 20) {
            description = "Wandering Monster (passage continues straight for 10"
            + " ft)";
        }
        return description;
    }
}
