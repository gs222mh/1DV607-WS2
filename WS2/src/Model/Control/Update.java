package Model.Control;

import Model.Boat;
import Model.Member;
import View.Console;
import org.json.JSONArray;
import org.json.JSONException;

public class Update {
    public static final String red = "\u001B[31m";
    DBControl check = new DBControl();

    public Update(Member member, String pn) throws JSONException {
        updateMember(member, pn);
    }

    public Update(Boat boat, Member member, int pos) throws JSONException {
        updateBoat(boat, member, pos);
    }

    public void updateMember(Member member, String oldPn) throws JSONException {
        DBControl db = new DBControl();
        JSONArray arr = db.dbRead();
        Register reg = new Register();
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get("Personal_Number").equals(oldPn)) {
                if (!oldPn.equals(member.getPn())) {
                    if (!check.check(member)) {
                        arr.getJSONObject(i).put("name", member.getName());
                        arr.getJSONObject(i).put("Personal_Number", member.getPn());
                        reg.idGenerator(member);
                        arr.getJSONObject(i).put("ID", reg.id);
                    } else {
                        System.out.println(red + "Sorry we have already this PN");
                    }
                } else {
                    arr.getJSONObject(i).put("name", member.getName());
                    arr.getJSONObject(i).put("Personal_Number", member.getPn());
                    reg.idGenerator(member);
                    arr.getJSONObject(i).put("ID", reg.id);
                    break;
                }
            }
        }
        db.dbWrite(arr);
    }

    public void updateBoat(Boat boat, Member member, int pos) throws JSONException {
        DBControl db = new DBControl();
        JSONArray arr = db.dbRead();
        pos = pos - 1;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                JSONArray list = arr.getJSONObject(i).getJSONArray("Boats");
                list.getJSONObject(pos).put("type", boat.getType());
                list.getJSONObject(pos).put("size", boat.getSize());
            }
        }
        db.dbWrite(arr);
    }
}
