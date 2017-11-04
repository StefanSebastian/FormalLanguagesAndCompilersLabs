package app;

import app.controller.Controller;

/**
 * Created by Sebi on 04-Nov-17.
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        UI ui = new UI(controller);
        ui.runMenu();
    }
}
