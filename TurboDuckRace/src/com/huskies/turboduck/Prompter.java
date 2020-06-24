package com.huskies.turboduck;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Prompter {

    private static Scanner scanner = new Scanner(System.in);    // To get input

    private Prompter() {
        // empty for static class
    }

    public static void startRace() {

    }


    public static Map<Integer, Duck> runRace() {

        // read from file or not?
        boolean readFromFile = doThing("Do you want to read racers from a file?");

        // instantiate variables
        Map<Integer, Duck> racers;

        if (readFromFile) {
            // method to read from file
            String filePath = scanner.nextLine(); //TODO path input checking
//            racers = readRacersFromfile(filePath)
            racers = DuckFarm.getDucks(1);
        } else {
            System.out.println("How many racers are in this race? Enter below");
            int numOfRacers = 0;
            while (numOfRacers == 0) {
                try {
                    numOfRacers = scanner.nextInt();
                    if (numOfRacers <= 0) {
                        numOfRacers = 0;
                        throw new IllegalArgumentException();
                    }
                } catch (InputMismatchException | IllegalArgumentException e) {
                    scanner.nextLine();
                    System.out.println("Input has to be a int greater than 0!");
                }
            }
            System.out.println(numOfRacers + " racers");
            racers = DuckFarm.getDucks(numOfRacers);

        }

        System.out.println("How long is the race in minutes? Enter below");
        double raceDuration = 0.0;
        while (raceDuration == 0.0) {
            try {
                raceDuration = scanner.nextDouble();
                if (raceDuration <= 0) {
                    raceDuration = 0;
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                scanner.nextLine();
                System.out.println("Input has to be a number greater than 0!");
            }
        }
        System.out.println(raceDuration + " minutes");

        Race.startRace(racers, raceDuration);
        return racers;

    }


    public static void chooseAward(Map<Integer, Duck> racers) {
        System.out.println();
        boolean logResults = doThing("Do you want to save the results?");

        if (logResults) { // save to a file
            // something in winningboard
        } else {
            // print out the winner
            List<Duck> duckList = racers.values().stream()
                    .sorted(Comparator.comparingDouble((Duck duck) -> duck.getDistanceTraveled()).reversed())
                    .limit(3)
                    .collect(Collectors.toList());

            duckList.forEach((duck) -> System.out.println("Duck \"" + duck.getName() + "\" finished at "
                    + duck.getDistanceTraveled() + " (Light Years)"));
        }

    }

    private static boolean doThing(String message) {
        System.out.println(message);
        boolean gotInput = false;
        boolean returning = false;
        while (!gotInput) {
            String fromFile = scanner.nextLine().toUpperCase();

            if (fromFile.equals("YES") || fromFile.equals("Y")) {
                returning = true;
                gotInput = true;
            } else if (fromFile.equals("NO") || fromFile.equals("N")) {
                gotInput = true;
            } else if (fromFile.isEmpty()) {
                // do nothing, weird empty case hack
            } else {
                System.out.println("ERROR: Answer (yes;y) or (no;n)");
            }
        }
        return returning;
    }

    public static void printBanner() {
        URL asciiFile = (ClassLoader.getSystemResource(Path.of("ascii_art","banner.txt").toString()));
        String banner = null;

        try {
            banner = "\n\n" + Files.lines(Path.of(asciiFile.toURI())).collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println(banner + "\n");
    }
}
