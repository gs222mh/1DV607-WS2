package Control;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class View {
    public View() {
    }

    public void compact() throws JSONException, IOException {
        DBControl db = new DBControl();
        JSONArray list = db.dbRecall();
        System.out.println("Name" + "\t" + "PN" + "\t\t\t" + "ID");
        File file = new File("/home/ghayth/Desktop/DB.json");
        if (!file.createNewFile()) {

            for (int i = 0; i < list.length(); i++) {
                if (list.getJSONObject(i).get("name").toString().length() <= 3)
                    System.out.println(list.getJSONObject(i).get("name") + "\t\t" + list.getJSONObject(i).get("Personal_Number") + "\t" + list.getJSONObject(i).get("ID"));
                else
                    System.out.println(list.getJSONObject(i).get("name") + "\t" + list.getJSONObject(i).get("Personal_Number") + "\t" + list.getJSONObject(i).get("ID"));
            }
        }
    }

    public void verbose() throws JSONException, IOException {
        DBControl db = new DBControl();
        JSONArray list = db.dbRecall();
        System.out.println("Name" + "\t" + "PN" + "\t\t\t" + "ID" + "\t\t\t" + "Boat/Type" + "\t\t\t" + "size");
        File file = new File("/home/ghayth/Desktop/DB.json");
        if (!file.createNewFile()) {

            for (int i = 0; i < list.length(); i++) {
                if (list.getJSONObject(i).get("name").toString().length() <= 3) {
                    System.out.print(list.getJSONObject(i).get("name") + "\t\t" + list.getJSONObject(i).get("Personal_Number") + "\t" + list.getJSONObject(i).get("ID"));
                    if (list.getJSONObject(i).getJSONArray("Boats").length() != 0) {
                        for (int j = 0; j < list.getJSONObject(i).getJSONArray("Boats").length(); j++) {
                            if (j == 0) {
                                if (list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type").toString().length() <= 3)
                                    System.out.println("\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                                else {
                                    System.out.println("\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                                }
                            } else {
                                if (list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type").toString().length() <= 3)
                                    System.out.println("\t\t\t\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                                else
                                    System.out.println("\t\t\t\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                            }
                        }
                    } else {
                        System.out.println("");
                    }
                } else {
                    System.out.print(list.getJSONObject(i).get("name") + "\t" + list.getJSONObject(i).get("Personal_Number") + "\t" + list.getJSONObject(i).get("ID"));
                    if (list.getJSONObject(i).getJSONArray("Boats").length() != 0) {
                        for (int j = 0; j < list.getJSONObject(i).getJSONArray("Boats").length(); j++) {
                            if (j == 0) {
                                if (list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type").toString().length() <= 3)
                                    System.out.println("\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                                else {
                                    System.out.println("\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                                }
                            } else {
                                if (list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type").toString().length() <= 3)
                                    System.out.println("\t\t\t\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                                else
                                    System.out.println("\t\t\t\t\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("type") + "\t\t\t\t" + list.getJSONObject(i).getJSONArray("Boats").getJSONObject(j).get("size"));
                            }
                        }
                    } else {
                        System.out.println("");
                    }
                }
            }
        }
    }
}