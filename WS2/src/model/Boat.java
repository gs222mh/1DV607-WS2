package model;

/**
 * The type Boat.
 */
public class Boat {
    /**
     * The Type.
     */
    protected String type;
    /**
     * The Size.
     */
    protected String size;
    // boat class that can set the size and type input

    /**
     * Instantiates a new Boat.
     *
     * @param numberOftype the number oftype
     * @param size         the size
     */
    public Boat(int numberOftype, String size) {
        setSize(size);
        setType(numberOftype);
    }

    /**
     * Sets size.
     *
     * @param size the size
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * Sets type.
     *
     * @param numberOftype the number oftype
     */
    public void setType(int numberOftype) {
        if (numberOftype == 1)
            this.type = "Sailboat";
        if (numberOftype == 2)
            this.type = "Motorsailer";
        if (numberOftype == 3)
            this.type = "kayak/Canoe";
        if (numberOftype == 4)
            this.type = "Other";
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }
}
