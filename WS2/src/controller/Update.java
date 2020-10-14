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
    private Console console = new Console();
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
        JSONArray list = reg.dbBefore;
        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get(PN).equals(oldPn)) {
                if (!oldPn.equals(member.getPn())) {
                    if (!reg.check(member)) {
                        list.getJSONObject(i).put("name", member.getName());
                        list.getJSONObject(i).put(PN, member.getPn());
                        reg.idGenerator(member);
                        list.getJSONObject(i).put("ID", reg.id);
                    } else {
                        console.feedBackExisted();
                    }
                } else {
                    list.getJSONObject(i).put("name", member.getName());
                    list.getJSONObject(i).put(PN, member.getPn());
                    reg.idGenerator(member);
                    list.getJSONObject(i).put("ID", reg.id);
                    break;
                }
            }
        }
        reg.reWriteOnList(list);
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
        JSONArray list = reg.dbBefore;
        pos = pos - 1;
        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get(PN).equals(member.getPn())) {
                JSONArray boatList = list.getJSONObject(i).getJSONArray("Boats");
                boatList.getJSONObject(pos).put("type", boat.getType());
                boatList.getJSONObject(pos).put("size", boat.getSize());
            }
        }
        reg.reWriteOnList(list);
    }
}
