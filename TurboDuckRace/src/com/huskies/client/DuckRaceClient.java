package com.huskies.client;

import com.huskies.turboduck.Duck;
import com.huskies.turboduck.DuckFarm;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.huskies.turboduck.Race.startRace;

public class DuckRaceClient {

    public static void main(String[] args) {
        try {
            printBanner();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("WELCOME TO TURBO DUCK RACE");
        // TODO startRace() needs racers
        System.out.println("How many racers are in this race? Enter below");
        Scanner numOfRacersScanner = new Scanner(System.in);
        int numOfRacers = numOfRacersScanner.nextInt();
        System.out.println(numOfRacers + " racers");

        //  TODO startRace() needs double duration
        System.out.println("How long is the race in minutes? Enter below");
        Scanner raceDurationScanner = new Scanner(System.in);
        double raceDuration = raceDurationScanner.nextDouble();
        System.out.println(raceDuration + " minutes");

        //  TODO boolean logResults
        /*
        System.out.println("Would you like the results to be logged? Enter 'true' or 'false'");
        Scanner logResultsInput = new Scanner(System.in);
        boolean isLog = logResultsInput.nextBoolean();
        System.out.println(logResultsInput.nextBoolean());
        if (isLog == true) {
            System.out.println("Your results will be logged!");
        } else {
            System.out.println("Your results will not be logged.");
        }
        */



        // DONE passing the information to startRace()
        Map<Integer, Duck> DucksForRace = DuckFarm.getDucks(numOfRacers);
        double durationForRace = raceDuration;
        boolean isLog = true;
        startRace(DucksForRace, durationForRace, true);


        List<Duck> duckList = DucksForRace.values().stream()
                .sorted(Comparator.comparingDouble((Duck duck) -> duck.getDistanceTraveled()).reversed())
                .limit(3)
                .collect(Collectors.toList());

                duckList.forEach((duck) -> System.out.println("Duck \"" + duck.getName() + "\" finished at "
                + duck.getDistanceTraveled()));

    }

    private static void printBanner() throws URISyntaxException, IOException {
        URL asciiFile = (ClassLoader.getSystemResource(Path.of("ascii_art","Banner.txt").toString()));
        String banner = "\n\n" + Files.lines(Path.of(asciiFile.toURI())).collect(Collectors.joining("\n"));
        System.out.println(banner + "\n");

    }
}
