package Model;

public class Member {
    protected String name;
    protected String pn;

    public Member(String name, String pn) {
        setName(name);
        setPn(pn);
    }
    public Member() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getName() {
        return name;
    }

    public String getPn() {
        return pn;
    }
}
