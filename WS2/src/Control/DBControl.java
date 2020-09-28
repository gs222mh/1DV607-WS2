package Control;

import Model.Member;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DBControl {
    public DBControl(){

    }
    public JSONArray dbRecall() {

        try {
            File file = new File("/home/ghayth/Desktop/DB.json");
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
            File file = new File("/home/ghayth/Desktop/DB.json");
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
}
