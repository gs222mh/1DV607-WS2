package model;

/**
 * The type Member.
 */
public class Member {
    /**
     * The Name.
     */
    protected String name;
    /**
     * The Pn.
     */
    protected String pn;

    /**
     * Instantiates a new Member.
     *
     * @param name the name
     * @param pn   the pn
     */
// member class that can set the name and pn input
    public Member(String name, String pn) {
        setName(name);
        setPn(pn);
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets pn.
     *
     * @param pn the pn
     */
    public void setPn(String pn) {
        this.pn = pn;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets pn.
     *
     * @return the pn
     */
    public String getPn() {
        return pn;
    }
}
