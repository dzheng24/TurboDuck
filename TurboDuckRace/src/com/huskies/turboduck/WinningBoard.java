package com.huskies.turboduck;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import static java.lang.Integer.parseInt;

public class WinningBoard {

    public Award award;
    enum Award {PRIZE, CASH};
    String path = "C:\\Users\\levid\\Documents\\Apprentice\\Mini-Project-Java\\TurboDuckRace\\resources\\winningBoard\\wb.txt";
//    String path = ClassLoader.getSystemResource("winningBoard/wb.txt").getPath();
    Map<String, Map<Award, Integer>> winnersOnBoard = new TreeMap<>();


    // List of raceFans
    public List<RaceFans> fans4TheWin;

    // = Arrays.asList(
//            new RaceFans(1, "Anna"),
//            new RaceFans(2, "Bob"),
//            new RaceFans(3,"Chris"),
//            new RaceFans(4,"David"),
//            new RaceFans(5,"Emma"),
//            new RaceFans(6,"Fox"),
//            new RaceFans(7,"Gorge"),
//            new RaceFans(8,"Henry"),
//            new RaceFans(9,"Ike"),
//            new RaceFans(10,"Jesse"),
//            new RaceFans(11,"Kris"),
//            new RaceFans(12,"Levi")
//    );

    public WinningBoard() {

    }


    public WinningBoard(List<RaceFans> fans4TheWin) {
        this.fans4TheWin = fans4TheWin;
    }


    //getter/setters
    public List<RaceFans> getFans4TheWin() {
        return fans4TheWin;
    }
    public void setAward(Award award) { this.award = award; }
    public Award getAward() {
        return award;
    }

    public void setPath(String path) {
        this.path = path;
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
                winnersOnBoard.put(listLine.get(0), prizeAwards);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find the winner's name from duck Race result
     */
    public String findWinnerByID(int IDnumber) {
        List<RaceFans> winner = fans4TheWin.stream()
                .filter(RaceFans -> RaceFans.getRaceFansNumber() == IDnumber)
                .collect(Collectors.toList());
        if (!winner.isEmpty()) {
            return winner.get(0).raceFansName;
        } else {
            System.out.println("nobody wins");
            return null;
        }
    }

    /**
     * println the award and also update WinnersOnBoard
     * @param racers
     */
    public void awardPrize(Map<Integer, Duck> racers, int prizeNum) {
        //client to choose prize
        int winningID = Race.getWinningID(racers);
        String winnerName = findWinnerByID(winningID);  //winningID
        if (prizeNum == 1) {
            System.out.println(Award.CASH.toString() + " to " + winnerName);
            setAward(Award.CASH);
        } else {
                System.out.println(Award.PRIZE.toString() + " to " + winnerName);
                setAward(Award.PRIZE);
        }

        //update winnersOnboard
        Map<Award, Integer> prizeCount;
        boolean isWinnerOnBoard = winnersOnBoard.containsKey(winnerName);
        if (isWinnerOnBoard) {
            prizeCount = winnersOnBoard.get(winnerName);
            int newAmount = (prizeCount.get(award) != null) ? (prizeCount.get(award) + 1) : 1;
            prizeCount.put(award, newAmount);
//            for (Award awardInMap : awardsSet) {
//                if (awardInMap.equals(award)) {
//                    prizeCount.put(awardInMap, (prizeCount.get(awardInMap) + 1));
//                    winnersOnBoard.put(winnerName, prizeCount);
//                } else {
//                    prizeCount.put(award, 1);
//                    winnersOnBoard.put(winnerName, prizeCount);
//                }
//            }
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
}




