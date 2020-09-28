package Model.Control;

import Model.Member;
import org.json.JSONArray;
import org.json.JSONException;

public class Remove {

    public int num = 1;
    public int size = 0;

    public Remove(Member member) throws JSONException {
        removeMember(member);
    }

    public Remove() {
    }

    public void removeMember(Member member) throws JSONException {
        DBControl db = new DBControl();
        JSONArray arr = db.dbRead();

        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                arr.remove(i);
            }
        }
        db.dbWrite(arr);
    }


    public void listBoat(Member member) throws JSONException {
        DBControl db = new DBControl();
        JSONArray arr = db.dbRead();
        System.out.println("Number" + "\t" + "type" + "\t" + "size");
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                JSONArray list = arr.getJSONObject(i).getJSONArray("Boats");
                size = list.length();
                for (int j = 0; j < list.length(); j++) {
                    if (list.getJSONObject(j).get("type").toString().length() <= 3) {
                        System.out.println(num + "\t\t" + list.getJSONObject(j).get("type") + "\t\t" + list.getJSONObject(j).get("size"));
                        num++;
                    } else {
                        System.out.println(num + "\t\t" + list.getJSONObject(j).get("type") + "\t" + list.getJSONObject(j).get("size"));
                        num++;
                    }
                }
            }
        }
    }

    public void removeBoat(Member member, int number) throws JSONException {
        DBControl db = new DBControl();
        JSONArray arr = db.dbRead();
        number = number - 1;
        for (int i = 0; i < arr.length(); i++) {
            if (arr.getJSONObject(i).get("Personal_Number").equals(member.getPn())) {
                JSONArray list = arr.getJSONObject(i).getJSONArray("Boats");
                if (list.length() == 1)
                    list.remove(number);
                else
                    list.remove(number);

            }
        }
        db.dbWrite(arr);
    }
}

