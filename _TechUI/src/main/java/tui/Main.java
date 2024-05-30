package tui;

import org.apache.log4j.Logger;

import tui.controller.MenuController;
import tui.util.InitCheckers;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        logger.debug("Program start");
        InitCheckers.init();

        MenuController menu = new MenuController();
        menu.execute();

        InitCheckers.stop();
        util.HibernateUtil.close();
        logger.debug("Program finish");
    }
}