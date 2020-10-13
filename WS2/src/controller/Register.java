package controller;

import model.Boat;
import model.Member;
import model.Storage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

/**
 * The type Register.
 */
public class Register {
    private static final Random rand = new Random();
    /**
     * The Db.
     */
    protected Storage db = new Storage();
    /**
     * The List before write.
     */
    public JSONArray listBeforeWrite = db.dbRead();
    /**
     * The List list.
     */
    public JSONArray listList = new JSONArray();
    /**
     * The Id.
     */
    protected String id;
    /**
     * The Result.
     */
    public boolean result;
    private static final String PN = "Personal_Number";


    /**
     * Instantiates a new Register.
     */
    public Register() {
        //Implement the class to call the methods
    }

    /**
     * Test.
     *
     * @param lastList the last list
     */
    public void test(JSONArray lastList) {
        listBeforeWrite = lastList;
        this.listList = lastList;
    }

    /**
     * Add member.
     *
     * @param member the member
     * @throws JSONException the json exception
     */
//Read DB add member to the JsonArray and rewrite it again
    public void addMember(Member member) throws JSONException {
        JSONObject addMem = new JSONObject();
        JSONArray arr = new JSONArray();
        addMem.put("name", member.getName());
        addMem.put(PN, member.getPn());
        addMem.put("ID", idGenerator(member));
        addMem.put("Boats", arr);
        listBeforeWrite.put(addMem);
        test(listBeforeWrite);
    }

    /**
     * Id generator string.
     *
     * @param member the member
     * @return the string
     */
//Generat ID using name and PN
    public String idGenerator(Member member) {
        String firstCharFromName = String.valueOf(member.getName().charAt(0)).toLowerCase();
        String secondCharFromName = String.valueOf(member.getName().charAt(1)).toLowerCase();
        String firstCharFromPn = String.valueOf(member.getPn().charAt(0));
        String secondCharFromPn = String.valueOf(member.getPn().charAt(1));
        String chars = "abcdefghijklmnopqrstuvwxyz";
        char c1 = chars.charAt(rand.nextInt(chars.length()));
        char c2 = chars.charAt(rand.nextInt(chars.length()));
        String rndChar1 = String.valueOf(c1);
        String rndChar2 = String.valueOf(c2);
        int lng = member.getPn().length();
        String lastCharFromPn = String.valueOf(member.getPn().charAt(lng - 2));
        String secondLastCharFromPn = String.valueOf(member.getPn().charAt(lng - 1));
        id = firstCharFromName + secondCharFromName + firstCharFromPn + secondCharFromPn + rndChar1 + rndChar2 + lastCharFromPn + secondLastCharFromPn;
        return id;
    }

    /**
     * Check boolean.
     *
     * @param member the member
     * @return the boolean
     */
//check if the PN is already exist on DB
    public boolean check(Member member) {
        boolean checking = false;
        try {
            if (listBeforeWrite.length() > 0) {
                for (int i = 0; i < listBeforeWrite.length(); i++) {
                    if (listBeforeWrite.getJSONObject(i).get(PN).equals(member.getPn())) {
                        checking = true;
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return checking;
    }


    /**
     * Search string.
     *
     * @param member the member
     * @return the string
     * @throws JSONException the json exception
     */
//Search in DB using JsonArray in all pos
    public String search(Member member) throws JSONException {
        JSONArray list = listBeforeWrite;
        String searchResult = "";
        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get(PN).equals(member.getPn())) {
                result = true;
                searchResult = "Name: " + list.getJSONObject(i).get("name") + "\nPN: " + list.getJSONObject(i).get(PN) + "\nID: " + list.getJSONObject(i).get("ID");
                break;
            } else
                result = false;
        }
        return searchResult;
    }

    /**
     * Add boat.
     *
     * @param boat   the boat
     * @param member the member
     * @throws JSONException the json exception
     */
//Read DB add boat depends on member's PN to the JsonArray and rewrite it again
    public void addBoat(Boat boat, Member member) throws JSONException {
        JSONArray list = listBeforeWrite;
        org.json.JSONObject boats = new org.json.JSONObject();
        boats.put("type", boat.getType());
        boats.put("size", boat.getSize());

        for (int i = 0; i < list.length(); i++) {
            if (list.getJSONObject(i).get(PN).equals(member.getPn())) {
                JSONArray add = list.getJSONObject(i).getJSONArray("Boats");
                add.put(boats);
                break;
            }
        }
        test(list);
    }
}
