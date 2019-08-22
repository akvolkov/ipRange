package com.my.company;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    /**
     * read range from console
     * @return Pair of number as begin and end range
     */
    public Pair<Long, Long> readRange() {
        long beforeIp = 0;
        long afterIp = 0;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String beforeLine = "";
            String afterLine = "";
            boolean isValidRange = false;
            while (!isValidRange) {
                System.out.println("Enter range of ips!");
                boolean isValidBefore = false;
                while (!isValidBefore) {
                    System.out.print("before ip:> ");
                    beforeLine = reader.readLine();
                    isValidBefore = checkIp(beforeLine);
                }
                boolean isValidAfter = false;
                while (!isValidAfter) {
                    System.out.print("after ip:> ");
                    afterLine = reader.readLine();
                    isValidAfter = checkIp(afterLine);
                }
                beforeIp = ipToLong(beforeLine);
                afterIp = ipToLong(afterLine);
                isValidRange = beforeIp < afterIp;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Pair<>(beforeIp, afterIp);
    }

    /**
     * check ip for: number of parts, parts does contain a parsable integer, 0 <= parts <= 255
     * @param ip as string
     * @return value check
     */
    public boolean checkIp(String ip) {
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

    /**
     * Convert Ip as string to long
     * @param string - ip as string
     * @return ip as long
     */
    public long ipToLong(String string) {
        final String[] split = string.split("\\.");
        return ((long) Math.pow(256, 3) * Long.parseLong(split[0]) +
                (long) Math.pow(256, 2) * Long.parseLong(split[1]) +
                (long) Math.pow(256, 1) * Long.parseLong(split[2]) +
                (long) Math.pow(256, 0) * Long.parseLong(split[3]));
    }

    /**
     * print ip
     * @param ip
     */
    void printIp(long ip) {
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
