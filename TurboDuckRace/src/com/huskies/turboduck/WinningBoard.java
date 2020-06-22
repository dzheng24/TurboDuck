package src.com.huskies.turboduck;

import java.io.*;
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
        Scanner sc = new Scanner(System.in); // create a Scanner variable

        for(raceFans fans : fans4TheWin) {
            if (fans.getRaceFansNumber() == 2) {
                System.out.println("congratulations! " + fans.getRaceFansName() +
                        "wins the game, please enter 1 for Cash prize or 2 for a present");
            }
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
           String path= "C:\\TurboDucks\\TurboDuck\\board.txt";
           File winnerBoard = new File(path);
           InputStreamReader reader = new InputStreamReader(new FileInputStream(winnerBoard));
           BufferedReader br = new BufferedReader(reader);


           String fileName = "winnerBoard";
           File boardFile = new File(path + fileName);
           if(!boardFile.exists()){
               try {
                   boardFile.createNewFile();
               }catch (IOException e){
                   e.printStackTrace();
               }
           }
           //create the txt doc
           FileWriter fw = new FileWriter(fileName);
           BufferedWriter recordWinner = new BufferedWriter(fw);
           recordWinner.write ("Bob Cash +1");
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
