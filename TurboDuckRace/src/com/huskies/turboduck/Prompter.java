package com.huskies.turboduck;

import com.huskies.turboduck.models.Duck;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Prompter {

    private final Scanner scanner = new Scanner(System.in);    // To get input
    private List<RaceFan> fans;
    private String RACER_DEFAULT_PATH;
    private String WINNINGBOARD_DEFAULT_PATH;

    public Prompter() {
        try {
            // messy way to get the resource files. Come back later to refactor
            RACER_DEFAULT_PATH = Paths.get(getClass().getClassLoader().getResource("data/racers.txt").toURI()).toString();
            WINNINGBOARD_DEFAULT_PATH = Paths.get(getClass().getClassLoader().getResource("data/wb.txt").toURI()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public Map<Integer, Duck> runRace() {
        // instantiate variables
        Map<Integer, Duck> racers;

        // read from file or not?
        boolean readFromFile = willDoThis("Do you want to read racers from a file?");

        if (readFromFile) {
            fans = RaceFanFactory.getRaceFans(getFilePath(RACER_DEFAULT_PATH));
            racers = DuckFarm.getDucks(fans);
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
            fans = RaceFanFactory.getRaceFans(racers);

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



    public void chooseAward(Map<Integer, Duck> racers) {
        List<Duck> topThree = racers.values().stream()
                .sorted(Comparator.comparingDouble(Duck::getDistanceTraveled).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println();
        System.out.println(topThree.get(0).getName() + " won!");

        topThree.forEach((duck) -> System.out.println(duck.getName() + " finished at "
                + duck.getDistanceTraveled() + " Light Years away."));
        System.out.println();

        if (willDoThis("Do you want to save the results?")) { // save to a file
            WinningBoard wb = new WinningBoard(fans);
            String filePath = getFilePath(WINNINGBOARD_DEFAULT_PATH);
            wb.setPath(filePath);
            wb.readBoard();
            int awardChoice = getAwardChoice();
            wb.awardPrize(racers, awardChoice);
            wb.updateBoard();
            wb.printWinningBoard();

        }

    }

    private boolean willDoThis(String message) {
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

    private int getAwardChoice() {
        System.out.println("What award do you choose?");
        System.out.println("Cash = 1; Prize = 2");
        int awardChoice = 0;
        while (awardChoice == 0) {
            try {
                awardChoice = scanner.nextInt();
                if (awardChoice != 1 && awardChoice != 2) {
                    awardChoice = 0;
                    throw new IllegalArgumentException();
                }
            } catch (InputMismatchException | IllegalArgumentException e) {
                scanner.nextLine();
                System.out.println("Input has to be either 1 or 2!");
            }
        }
        return awardChoice;
    }

    public void printBanner() {
        URL asciiFile = (ClassLoader.getSystemResource(Path.of("ascii_art","banner.txt").toString()));
        String banner = null;

        try {
            banner = "\n\n" + Files.lines(Path.of(asciiFile.toURI())).collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        System.out.println(banner + "\n");
        System.out.println("Welcome to TurboDuck Race 2020!");
        System.out.println("Please fasten your seat belt! These ducks go fast!!!");
        System.out.println("Ducks are warming up their engines, help them by configuring a few things....\n\n");
    }

    private String getFilePath(String defaultPath) {
        String returning = "";
        boolean gotInput = false;
        System.out.print("Use default? (enter for yes)");
        while (!gotInput) {
            String filePath = scanner.nextLine();
            // check if want to use default.
            if (filePath.isEmpty() || "YES".equals(filePath.toUpperCase().strip())
                    || "Y".equals(filePath.toUpperCase().strip())) {
                filePath = defaultPath;
            } else if ("NO".equals(filePath.toUpperCase().strip()) || "N".equals(filePath.toUpperCase().strip())) {
                System.out.println("New File Path: ");
                filePath = scanner.nextLine();
            }

            if (Files.exists(Path.of(filePath))) { // make sure file exists
                gotInput = true;
                returning = filePath;
            } else {
                System.out.println("Error: Cannot find file! Try again...");
            }
        }
        return returning;
    }
}
