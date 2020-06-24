package com.huskies.turboduck;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RaceFanFactory {

    private RaceFanFactory() {
        // private for static class
    }

    public static List<RaceFans> getRaceFans(String path) {
        List<RaceFans> returning = new ArrayList<>();
        File filename = new File(path);
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filename);
            InputStreamReader reader = new InputStreamReader(fileStream);
            BufferedReader br = new BufferedReader(reader);
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] items = line.split(":");
                returning.add(new RaceFans(Integer.parseInt(items[0]), items[1].strip()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returning;
    }
}
