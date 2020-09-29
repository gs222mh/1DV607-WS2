package Model.Control;

import Model.Boat;
import Model.Member;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;


import java.util.Random;
import java.util.UUID;


public class Register {
    protected String id;
    public boolean result;

    public Register(Member member) throws JSONException {
        idGenerator(member);
        addMember(member);
    }

    public Register(Boat boat, Member member) throws JSONException {
        addBoat(boat, member);
    }

    public Register() {
    }

    public void addMember(Member member) {
        DBControl db = new DBControl();
        JSONArray list = db.dbRead();
        JSONObject addMem = new JSONObject();
        JSONArray arr = new JSONArray();

        addMem.put("name", member.getName());
        addMem.put("Personal_Number", member.getPn());
        addMem.put("ID", id);
        addMem.put("Boats", arr);

        list.put(addMem);
        db.dbWrite(list);
    }

    public void idGenerator(Member member) {
        String firstCharFromName = String.valueOf(member.getName().charAt(0)).toLowerCase();
        String secondCharFromName = String.valueOf(member.getName().charAt(1)).toLowerCase();
        String firstCharFromPn = String.valueOf(member.getPn().charAt(0));
        String secondCharFromPn = String.valueOf(member.getPn().charAt(1));
        Random rnd = new Random();
        String chars = "abcdefghijklmnopqrstuvwxyz";
        char c1 = chars.charAt(rnd.nextInt(chars.length()));
        char c2 = chars.charAt(rnd.nextInt(chars.length()));
        String rndChar1 = String.valueOf(c1);
        String rndChar2 = String.valueOf(c2);
        int lng = member.getPn().length();
        String lastCharFromPn = String.valueOf(member.getPn().charAt(lng - 2));
        String secondLastCharFromPn = String.valueOf(member.getPn().charAt(lng - 1));
        id = firstCharFromName + secondCharFromName + firstCharFromPn + secondCharFromPn + rndChar1 + rndChar2 + lastCharFromPn + secondLastCharFromPn;
    }

    public void search(Member member) throws JSONException {
        DBControl db = new DBControl();
        JSONArray list = db.dbRead();
        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                result = true;
                if (list.getJSONObject(i).get("name").toString().length() <= 3) {
                    System.out.println("Name" + "\t" + "PN" + "\t\t\t" + "ID");
                    System.out.println(list.getJSONObject(i).get("name") + "\t\t" + list.getJSONObject(i).get("Personal_Number") + "\t" + list.getJSONObject(i).get("ID"));
                } else {
                    System.out.println("Name" + "\t" + "PN" + "\t\t\t" + "ID");
                    System.out.println(list.getJSONObject(i).get("name") + "\t" + list.getJSONObject(i).get("Personal_Number") + "\t" + list.getJSONObject(i).get("ID"));
                }
                break;
            } else
                result = false;
        }
    }

    public void addBoat(Boat boat, Member member) throws JSONException {
        DBControl db = new DBControl();
        JSONArray list = db.dbRead();
        org.json.JSONObject boats = new org.json.JSONObject();
        boats.put("type", boat.getType());
        boats.put("size", boat.getSize());

        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                JSONArray add = list.getJSONObject(i).getJSONArray("Boats");
                add.put(boats);
                break;
            }
        }
        db.dbWrite(list);
    }
}
