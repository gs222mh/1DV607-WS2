package Control;

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
}
