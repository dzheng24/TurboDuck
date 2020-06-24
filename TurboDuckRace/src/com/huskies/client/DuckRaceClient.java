package com.huskies.client;

import com.huskies.turboduck.Prompter;

public class DuckRaceClient {

    public static void main(String[] args) {

        Prompter.printBanner();

        var racers = Prompter.runRace();

        Prompter.chooseAward(racers);

    }

}
