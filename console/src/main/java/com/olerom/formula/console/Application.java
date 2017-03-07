package com.olerom.formula.console;

import com.olerom.formula.core.ErgastConnection;

/**
 * Date: 08.03.17
 *
 * @author olerom
 */
public class Application {
    public static void main(String[] args) {
        new Application().run();
    }

    private void run() {
        ErgastConnection ergastConnection = new ErgastConnection();
        ergastConnection.configureRequest();
    }
}
