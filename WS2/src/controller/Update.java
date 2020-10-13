package controller;

import model.Boat;
import model.Member;
import org.json.JSONArray;
import org.json.JSONException;
import view.Console;


/**
 * The type Update.
 */
public class Update {
    private Register re = new Register();
    private Console c = new Console();
    private static final String PN = "Personal_Number";

    /**
     * Instantiates a new Update.
     */
    public Update() {
        //Implement the class to call the methods
    }

    /**
     * Update member.
     *
     * @param member the member
     * @param reg    the reg
     * @param oldPn  the old pn
     * @throws JSONException the json exception
     */
    public void updateMember(Member member, Register reg, String oldPn) throws JSONException {
        JSONArray arr = reg.listBeforeWrite;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get(PN).equals(oldPn)) {
                if (!oldPn.equals(member.getPn())) {
                    if (!re.check(member)) {
                        arr.getJSONObject(i).put("name", member.getName());
                        arr.getJSONObject(i).put(PN, member.getPn());
                        reg.idGenerator(member);
                        arr.getJSONObject(i).put("ID", reg.id);
                    } else {
                        c.feedBackExisted();
                    }
                } else {
                    arr.getJSONObject(i).put("name", member.getName());
                    arr.getJSONObject(i).put(PN, member.getPn());
                    reg.idGenerator(member);
                    arr.getJSONObject(i).put("ID", reg.id);
                    break;
                }
            }
        }
        reg.test(arr);
    }

    /**
     * Update boat.
     *
     * @param boat   the boat
     * @param member the member
     * @param reg    the reg
     * @param pos    the pos
     * @throws JSONException the json exception
     */
    public void updateBoat(Boat boat, Member member, Register reg, int pos) throws JSONException {
        JSONArray arr = reg.listBeforeWrite;
        pos = pos - 1;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get(PN).equals(member.getPn())) {
                JSONArray list = arr.getJSONObject(i).getJSONArray("Boats");
                list.getJSONObject(pos).put("type", boat.getType());
                list.getJSONObject(pos).put("size", boat.getSize());
            }
        }
        reg.test(arr);
    }
}
