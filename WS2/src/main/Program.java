package main;

import controller.Register;
import view.UserInterface;
import org.json.JSONException;
import model.Storage;

import java.io.IOException;

/**
 * The type Program.
 */
public class Program {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException   the io exception
     * @throws JSONException the json exception
     */
// The main class, Here you can start the program
    public static void main(String[] args) throws JSONException {
        Storage db = new Storage();
        UserInterface ui = new UserInterface();
        Register reg = new Register(ui);

        reg.loadProgram(db);
    }
}
