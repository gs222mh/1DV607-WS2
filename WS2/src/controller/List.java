package controller;

import org.json.JSONArray;
import org.json.JSONException;
import view.Console;


/**
 * The type List.
 */
public class List {
    private Console c = new Console();

    /**
     * Instantiates a new List.
     */
    public List() {
        //Implement the class to call the methods
    }

    /**
     * Compact.
     *
     * @param reg the reg
     * @throws JSONException the json exception
     */
    //List all member
    public void compact(Register reg) throws JSONException {
        JSONArray list = reg.dbBefore;
        for (int i = 0; i < list.length(); i++) {
            c.printCompact(reg, i);
            c.printNumberSign();
        }
    }

    /**
     * Verbose.
     *
     * @param reg the reg
     * @throws JSONException the json exception
     */
    public void verbose(Register reg) throws JSONException {
        JSONArray list = reg.dbBefore;
        for (int i = 0; i < list.length(); i++) {
            c.printVerbose(reg, i);
            c.printNumberSign();
        }
    }
}
