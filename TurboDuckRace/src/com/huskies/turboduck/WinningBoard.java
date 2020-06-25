package com.huskies.turboduck;

import com.huskies.turboduck.models.Duck;

import java.io.*;
import java.net.IDN;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import static java.lang.Integer.parseInt;

public class WinningBoard {

    public Award award;
    enum Award {PRIZE, CASH};
    String path;
    Map<String, Map<Award, Integer>> winnersOnBoard;

    // List of raceFans
    private List<RaceFan> fans4TheWin;

    public WinningBoard() {
        winnersOnBoard = new TreeMap<>();
    }


    public WinningBoard(List<RaceFan> fans4TheWin) {
        this();
        this.fans4TheWin = fans4TheWin;
    }


    //getter/setters
    public List<RaceFan> getFans4TheWin() {
        return fans4TheWin;
    }
    public void setFans4TheWin(List<RaceFan> fans){
        fans4TheWin = fans;
    }
    public void setAward(Award award) { this.award = award; }
    public Award getAward() {
        return award;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, Map<Award, Integer>> getWinnersOnBoard() {
        return winnersOnBoard;
    }

    /**
     * Read the Txt file and store information into winnersOnBoard
     */
    public void readBoard() {
        File filename = new File(path);
        FileInputStream filestream = null;
        try {
            filestream = new FileInputStream(filename);
            InputStreamReader reader = new InputStreamReader(filestream);
            BufferedReader br = new BufferedReader(reader);
            String line = "";

            while ((line = br.readLine()) != null) {
                List<String> listLine = Arrays.asList(line.split(","));//split line into a String List
                Map<Award, Integer> prizeAwards = new HashMap<>();
                for (int i = 1; i < listLine.size(); i++) {
                    List<String> listPrize = Arrays.asList(listLine.get(i).split("="));
                    prizeAwards.put(Award.valueOf(listPrize.get(0).toUpperCase().strip()), parseInt(listPrize.get(1))); //store in the Map
                }
                winnersOnBoard.put(listLine.get(0).strip(), prizeAwards);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find the winner's name from duck Race result
     */
    public String findByID(int IDnumber) {
        List<RaceFan> fan = fans4TheWin.stream()
                .filter(RaceFans -> RaceFans.getRaceFansNumber() == IDnumber)
                .collect(Collectors.toList());
        String returning = "";
        if (!fan.isEmpty()) {
            returning = fan.get(0).getRaceFansName();
        } else {
            throw new IllegalArgumentException("No one by that ID number");
        }
        return returning;
    }

    /**
     * println the award and also update WinnersOnBoard
     * @param racers
     */
    public void awardPrize(Map<Integer, Duck> racers, int prizeNum) {
        //client to choose prize
        int winningID = Race.getWinningID(racers);
        String winnerName = findByID(winningID);  //winningID
        if (prizeNum == 1) {
            setAward(Award.CASH);
        } else {
                setAward(Award.PRIZE);
        }
        System.out.println(winnerName + " gets " + award.toString() + "!");

        //update winnersOnboard
        Map<Award, Integer> prizeCount;
        boolean isWinnerOnBoard = winnersOnBoard.containsKey(winnerName);
        if (isWinnerOnBoard) {
            prizeCount = winnersOnBoard.get(winnerName);
            int newAmount = (prizeCount.get(award) != null) ? (prizeCount.get(award) + 1) : 1;
            prizeCount.put(award, newAmount);
        } else {
            prizeCount = new HashMap<>();
            prizeCount.put(award, 1);
            winnersOnBoard.put(winnerName, prizeCount);
        }
    }

    /**
     * Records the result to a text(?) file, updating it to the list of awards.
     */

    public void updateBoard() {
        BufferedWriter updateB = null;
        try {
            updateB = new BufferedWriter(new FileWriter(path));
            for (String name : winnersOnBoard.keySet()) {
                StringBuffer result = new StringBuffer();
                result.append(name);
                for (Map.Entry<Award,Integer> entry: winnersOnBoard.get(name).entrySet()) {
                    result.append(", ");
                    result.append(entry.getKey().toString()+ "=");
                    result.append(entry.getValue().toString());
                }
                String record = result.toString();
                updateB.write(record);
                updateB.write("\r\n");
            }
            updateB.flush();
            updateB.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printWinningBoard() {
        String board = null;
        try {
            board = Files.lines(Paths.get(path)).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nWinner's Circle:");
        System.out.println(board);
    }
}




