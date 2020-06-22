package com.huskies.turboduck;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WinningBoard {
    public Award award;            // the award for winning the race
    enum Award {PRIZE, CASH};
    public Award getAward() {
        return award;
    }

    /**
     * Allows the winner to choose the prize, and awarded to them in a grand fashion.
     */
    public void awardPrize() {

        for(raceFans fans : fans4TheWin) {
            if (fans.getRaceFansName().equals("Bob")) {
                System.out.println("congratulations! " + fans.getRaceFansName() +
                        " wins the game, please enter 1 for Cash prize or 2 for a present");
            }
            Scanner sc = new Scanner(System.in); // create a Scanner variable
            int prizeNum = sc.nextInt();
            switch(prizeNum){
                case 1:
                    System.out.println("Cash to "+ fans.getRaceFansName());
                    break;
                case 2:
                    System.out.println("Prize to " + fans.getRaceFansName());
                    break;
            }
        }
    }

    /**
     * Records the result to a text(?) file, updating it to the list of awards.
     * @return
     */
    public void recordResult() {
        try {
            BufferedWriter recordWinner = new BufferedWriter(new FileWriter("winningBoard.txt"));
            recordWinner.write ("Bob Cash +2");
            recordWinner.close();
            System.out.println("Bob is recorded on the Board");
        } catch (IOException e){
        }
    }

    // List of raceFans

    private List<raceFans> fans4TheWin = Arrays.asList(
            new raceFans(1,"Anna"),
            new raceFans(2,"Bob")
    );

    /**
     *this inner class is use to create and store raceFans who is able winner the prize
     */
    private class raceFans {
        String raceFansName;     // the name of the winning racer
        int raceFansNumber;      // the number id of the winner

        public String getRaceFansName() { return raceFansName; }
        public void setRaceFansName(String raceFansName) { this.raceFansName = raceFansName; }

        public int getRaceFansNumber() {
            return raceFansNumber;
        }
        public void setRaceFansNumber(int raceFansNumber) {this.raceFansNumber = raceFansNumber;}

        public raceFans(int raceFansNumber, String raceFansName) {
            setRaceFansNumber(raceFansNumber);
            setRaceFansName(raceFansName);
        }
    }


}