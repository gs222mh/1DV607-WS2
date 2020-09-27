package Control;

import Model.Boat;
import Model.Member;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RegistBoat {
    public RegistBoat(Boat boat, Member member) throws JSONException {
        addBoat(boat, member);
    }

    public void addBoat(Boat boat, Member member) throws JSONException {
        DBControl db = new DBControl();
        JSONArray list = db.dbRecall();
        org.json.JSONObject boats = new org.json.JSONObject();
        boats.put("type", boat.getType());
        boats.put("size", boat.getSize());

        System.out.println(member.getPn());
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
