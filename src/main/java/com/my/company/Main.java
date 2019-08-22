package com.my.company;

import javafx.util.Pair;

/**
 * This is Main class
 */
public class Main {
    public static void main(String[] args) {
        final App app = new App();
        final Pair<Long, Long> pair = app.readRange();
        long beforeIp = pair.getKey();
        long afterIp = pair.getValue();
        while (beforeIp < afterIp - 1) {
            beforeIp++;
            app.printIp(beforeIp);
        }
    }
}
