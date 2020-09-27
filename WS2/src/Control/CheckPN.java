package Control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CheckPN {

    public CheckPN() {
    }

    public boolean check(String pn) {
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
                    if (arr.getJSONObject(i).get("Personal_Number").equals(pn)) {
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
