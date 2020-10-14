package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

/**
 * The type Storage.
 */
public class Storage {
    /**
     * The constant file.
     */
//Here you can write the path of the DB'
    public File file = new File("../DB.json");
    /**
     * The constant loadFile.
     */
    private JSONArray loadFile = new JSONArray();

    /**
     * Instantiates a new Storage.
     */
    public Storage() {
        //to call this method
    }


    /**
     * Db read json array.
     *
     * @return the json array
     */
    //Method to read json file and add all line to JsonArray
    public JSONArray dbRead() {
        try (Scanner sT = new Scanner(file)) {
            String text = "";
            JSONObject obj;
            while (sT.hasNext()) {
                text = sT.nextLine();
                obj = new JSONObject(text);
                loadFile.put(obj);
            }
        } catch (IOException | JSONException e) {
            e.getMessage();
        }
        return loadFile;
    }

    /**
     * Db write.
     *
     * @param list the list
     */
    //Get back JsonArray and rewrite it again to file
    public void dbWrite(JSONArray list) {
        try (PrintWriter writer = new PrintWriter(file)) {
            //Remove all thing from file
            writer.print("");
            if (!file.createNewFile()) {
                try (FileWriter input = new FileWriter(file, true)) {
                    for (int i = 0; i < list.length(); i++)
                        input.write(list.getJSONObject(i) + "\n");
                }
            } else {
                try (FileWriter fr = new FileWriter(file, true)) {
                    for (int i = 0; i < list.length(); i++)
                        fr.write(list.getJSONObject(i) + "\n");
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }
}
