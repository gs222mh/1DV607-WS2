import View.UserInterface;
import org.json.JSONException;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException, JSONException {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
