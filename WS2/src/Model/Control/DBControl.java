package Model.Control;

import Model.Member;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DBControl {
    public File file = new File("/home/ghayth/Desktop/DB.json");

    public DBControl() {
    }

    public JSONArray dbRead() {

        try {
            Scanner sT = new Scanner(file);
            String text = "";
            JSONObject obj;
            JSONArray arr = new JSONArray();
            while (sT.hasNext()) {
                text = sT.nextLine();
                obj = new JSONObject(text);
                arr.put(obj);
            }
            return arr;

        } catch (IOException | JSONException e) {

        }
        return null;
    }

    public boolean check(Member member) {
        boolean checking = false;
        try {
            if (!file.createNewFile()) {
                Scanner sT = new Scanner(file);
                String text = "";
                JSONObject obj;
                JSONArray arr = new JSONArray();
                while (sT.hasNext()) {
                    text = sT.nextLine();
                    obj = new JSONObject(text);
                    arr.put(obj);
                }
                for (int i = 0; i < arr.length(); i++) {
                    if (arr.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                        checking = true;
                    }
                }
            } else {
                checking = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return checking;
    }

    public void dbWrite(JSONArray list) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.close();
            try {
                if (file.createNewFile()) {
                    FileWriter input = new FileWriter(file, true);
                    for (int i = 0; i < list.length(); i++)
                        input.write(list.getJSONObject(i) + "\n");
                    input.close();

                } else {
                    FileWriter fr = new FileWriter(file, true);
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
