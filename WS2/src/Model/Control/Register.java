package Model.Control;

import Model.Boat;
import Model.Member;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;


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
        JSONObject addMem = new JSONObject();
        JSONArray arr = new JSONArray();

        addMem.put("name", member.getName());
        addMem.put("Personal_Number", member.getPn());
        addMem.put("ID", id);
        addMem.put("Boats", arr);

        try {
            File Name_File = new File("/home/ghayth/Desktop/DB.json");
            try {
                if (Name_File.createNewFile()) {
                    FileWriter input = new FileWriter(Name_File, true);
                    input.write(addMem.toJSONString() + "\n");
                    input.close();

                } else {
                    FileWriter fr = new FileWriter(Name_File, true);
                    fr.write(addMem.toJSONString() + "\n");
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void idGenerator(Member member) {
        String firstCharFromName = String.valueOf(member.getName().charAt(0)).toLowerCase();
        String secondCharFromName = String.valueOf(member.getName().charAt(1)).toLowerCase();
        String firstCharFromPn = String.valueOf(member.getPn().charAt(0));
        String secondCharFromPn = String.valueOf(member.getPn().charAt(1));
        Random rnd = new Random();
        int randomNumber = rnd.nextInt(9) + 1;
        String rndNum = String.valueOf(randomNumber);
        int lng = member.getPn().length();
        String lastCharFromPn = String.valueOf(member.getPn().charAt(lng - 2));
        String secondLastCharFromPn = String.valueOf(member.getPn().charAt(lng - 1));
        id = firstCharFromName + secondCharFromName + firstCharFromPn + secondCharFromPn + rndNum + lastCharFromPn + secondLastCharFromPn;
    }

    public void search(Member member) throws JSONException {
        DBControl db = new DBControl();
        JSONArray list = db.dbRecall();
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
        JSONArray list = db.dbRecall();
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
        try {
            File dataBase = new File("/home/ghayth/Desktop/DB.json");
            PrintWriter writer = new PrintWriter(dataBase);
            writer.print("");
            writer.close();
            try {
                if (dataBase.createNewFile()) {
                    FileWriter input = new FileWriter(dataBase, true);
                    for (int i = 0; i < list.length(); i++)
                        input.write(list.getJSONObject(i) + "\n");
                    input.close();

                } else {
                    FileWriter fr = new FileWriter(dataBase, true);
                    for (int i = 0; i < list.length(); i++)
                        fr.write(list.getJSONObject(i) + "\n");
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
