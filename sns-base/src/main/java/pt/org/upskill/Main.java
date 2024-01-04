package pt.org.upskill;

import pt.org.upskill.session.Context;

public class Main {

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();

        Context.getInstance().drawer().draw(Context.getInstance().mainMenu());
        /*
        try {
            MainMenu menu = new MainMenu();
            menu.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }
}