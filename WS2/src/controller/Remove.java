package controller;

import model.Member;
import org.json.JSONArray;
import org.json.JSONException;
import view.Console;


/**
 * The type Remove.
 */
public class Remove {
    private Console c = new Console();
    private static final String PN = "Personal_Number";
    private int num = 1;
    /**
     * The Size.
     */
    public int size = 0;

    /**
     * Instantiates a new Remove.
     */
    public Remove() {
        //Implement the class to call the methods
    }

    /**
     * Remove member.
     *
     * @param member the member
     * @param reg    the reg
     * @throws JSONException the json exception
     */
    public void removeMember(Member member, Register reg) throws JSONException {
        JSONArray arr = reg.listBeforeWrite;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get(PN).equals(member.getPn())) {
                arr.remove(i);
            }
        }
        reg.test(arr);
    }


    /**
     * List boat.
     *
     * @param member the member
     * @param reg    the reg
     * @throws JSONException the json exception
     */
    public void listBoat(Member member, Register reg) throws JSONException {
        JSONArray arr = reg.listBeforeWrite;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get(PN).equals(member.getPn())) {
                JSONArray list = arr.getJSONObject(i).getJSONArray("Boats");
                size = list.length();
                c.printboatList(list, num);
            }
        }
        num = 1;
    }

    /**
     * Remove boat.
     *
     * @param member the member
     * @param reg    the reg
     * @param number the number
     * @throws JSONException the json exception
     */
    public void removeBoat(Member member, Register reg, int number) throws JSONException {
        JSONArray arr = reg.listBeforeWrite;
        number = number - 1;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get(PN).equals(member.getPn())) {
                JSONArray list = arr.getJSONObject(i).getJSONArray("Boats");
                list.remove(number);
            }
        }
        reg.test(arr);
    }
}

