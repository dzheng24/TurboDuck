package com.huskies.client;

import com.huskies.turboduck.Prompter;

public class DuckRaceClient {

    public static void main(String[] args) {
        Prompter prompter = new Prompter();

        prompter.printBanner();
        var racers = prompter.runRace();
        prompter.chooseAward(racers);

    }

}
