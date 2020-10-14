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
    private int boatCount = 1;
    /**
     * The Size.
     */
    public int numOfBoat = 0;

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
        JSONArray list = reg.dbBefore;
        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get(PN).equals(member.getPn())) {
                list.remove(i);
            }
        }
        reg.reWriteOnList(list);
    }


    /**
     * List boat.
     *
     * @param member the member
     * @param reg    the reg
     * @throws JSONException the json exception
     */
    public void listBoat(Member member, Register reg) throws JSONException {
        JSONArray list = reg.dbBefore;
        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get(PN).equals(member.getPn())) {
                JSONArray boatList = list.getJSONObject(i).getJSONArray("Boats");
                numOfBoat = boatList.length();
                c.printboatList(boatList, boatCount);
            }
        }
        boatCount = 1;
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
        JSONArray list = reg.dbBefore;
        number = number - 1;
        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get(PN).equals(member.getPn())) {
                JSONArray boatList = list.getJSONObject(i).getJSONArray("Boats");
                boatList.remove(number);
            }
        }
        reg.reWriteOnList(list);
    }
}

