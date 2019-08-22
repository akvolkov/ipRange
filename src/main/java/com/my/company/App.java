package com.my.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        long beforeIp = 0;
        long afterIp = 0;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String beforeLine = "";
            String afterLine = "";
            boolean isValidRange = false;
            while (!isValidRange) {
                System.out.println("Enter range of ips!");
                boolean checkBeforeLine = false;
                while (!checkBeforeLine) {
                    System.out.print("before ip:> ");
                    beforeLine = reader.readLine();
                    checkBeforeLine = checkIp(beforeLine);
                }
                boolean checkAfterLine = false;
                while (!checkAfterLine) {
                    System.out.print("after ip:> ");
                    afterLine = reader.readLine();
                    checkAfterLine = checkIp(afterLine);
                }
                beforeIp = ipToLong(beforeLine);
                afterIp = ipToLong(afterLine);
                isValidRange = beforeIp < afterIp;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        while (beforeIp < afterIp - 1) {
            beforeIp++;
            printIp(beforeIp);
        }
    }

    private static boolean checkIp(String ip) {
        String[] split = ip.split("\\.");
        if (split.length != 4) {
            System.out.println("The number of parts of the IP address is not equal to 4.");
            return false;
        }
        for (int i = 0; i < split.length; i++) {
            int partAsInteger = 0;
            try {
                partAsInteger = Integer.parseInt(split[i]);
            } catch (NumberFormatException e) {
                System.out.println("Parts " + i + " does not contain a parsable integer.");
                return false;
            }
            if (partAsInteger > 255 || partAsInteger < 0) {
                System.out.println("Part number " + i + " > 255 || < 0");
                return false;
            }
        }
        return true;
    }

    private static long ipToLong(String string) {
        final String[] split = string.split("\\.");
        return ((long) Math.pow(256, 3) * Long.parseLong(split[0]) +
                (long) Math.pow(256, 2) * Long.parseLong(split[1]) +
                (long) Math.pow(256, 1) * Long.parseLong(split[2]) +
                (long) Math.pow(256, 0) * Long.parseLong(split[3]));
    }

    private static void printIp(long ip) {
        StringBuilder sb = new StringBuilder();
        sb.append(ip / (long) Math.pow(256, 3));
        long number = ip % (long) Math.pow(256, 3);
        for (int i = 2; i >= 0; i--) {
            sb.append(".").append(number / (long) Math.pow(256, i));
            number = ip % (long) Math.pow(256, i);
        }
        System.out.println(sb.toString());
    }
}
