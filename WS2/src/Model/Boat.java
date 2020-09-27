package Model;

public class Boat {
    protected String type;
    protected String size;

    public Boat(String type, String size){
        setSize(size);
        setType(type);
    }
    public Boat(){

    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}
