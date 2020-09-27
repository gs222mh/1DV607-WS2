package Control;

import Model.Member;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RemoveMember {
    public RemoveMember(Member member) throws JSONException {
        remove(member);
    }

    public void remove(Member member) throws JSONException {
        DBControl db = new DBControl();
        JSONArray arr = db.dbRecall();

        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                arr.remove(i);
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
                    for (int i = 0; i < arr.length(); i++)
                        input.write(arr.getJSONObject(i) + "\n");
                    input.close();

                } else {
                    FileWriter fr = new FileWriter(dataBase, true);
                    for (int i = 0; i < arr.length(); i++)
                        fr.write(arr.getJSONObject(i)  + "\n");
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
