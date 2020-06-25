package com.huskies.turboduck;

import com.huskies.turboduck.models.Color;
import com.huskies.turboduck.models.Duck;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RaceFanFactory {

    private RaceFanFactory() {
        // private for static class
    }

    public static List<RaceFan> getRaceFans(String path) {
        List<RaceFan> returning = new ArrayList<>();
        File filename = new File(path);
        FileInputStream fileStream = null;
        try {
            fileStream = new FileInputStream(filename);
            InputStreamReader reader = new InputStreamReader(fileStream);
            BufferedReader br = new BufferedReader(reader);
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] items = line.split(":");

                if (items.length < 2) {
                    String message = "Each Fan in file " + path + " needs at least a number and name" +
                            "(in that order), separated by a \":\"";
                    throw new IllegalArgumentException(message);
                }

                Color preferredColor;
                try { // set the color as described in doc
                    preferredColor = Color.valueOf(items[items.length - 1].strip().toUpperCase());
                } catch (IllegalArgumentException e) { // no or wrong set, doing yellow
                    preferredColor = Color.YELLOW;
                }
                returning.add(new RaceFan(Integer.parseInt(items[0].strip()),
                        items[1].strip(), preferredColor));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returning;
    }

    public static List<RaceFan> getRaceFans(Map<Integer, Duck> racers) {
        List<RaceFan> returning = new ArrayList<>();
        for (Integer id : racers.keySet()) {
            Duck duck = racers.get(id);
            returning.add(new RaceFan(id, duck.getName(), duck.getColor()));
        }
        return returning;
    }
}
