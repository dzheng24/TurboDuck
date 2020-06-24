package com.huskies.turboduck;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import static java.lang.Integer.parseInt;

public class WinningBoard {

    public Award award;
    enum Award {PRIZE, CASH};
    String path = ClassLoader.getSystemResource("winningBoard/wb.txt").getPath();
    Map<String, Map<Award, Integer>> winnersOnBoard = new TreeMap<>();


    // List of raceFans
    public static List<RaceFans> fans4TheWin = Arrays.asList(
            new RaceFans(1, "Anna"),
            new RaceFans(2, "Bob"),
            new RaceFans(3,"Chris"),
            new RaceFans(4,"David"),
            new RaceFans(5,"Emma"),
            new RaceFans(6,"Fox"),
            new RaceFans(7,"Gorge"),
            new RaceFans(8,"Henry"),
            new RaceFans(9,"Ike"),
            new RaceFans(10,"Jesse"),
            new RaceFans(11,"Kris"),
            new RaceFans(12,"Levi")
    );

    //getter/setters
    public List<RaceFans> getFans4TheWin() {
        return fans4TheWin;
    }
    public void setAward(Award award) { this.award = award; }
    public Award getAward() {
        return award;
    }

    /**
     * Read the Txt file and store information into winnersOnBoard
     */
    public void readBoard() throws IOException {
        File filename = new File(path);
        FileInputStream filestream = new FileInputStream(filename);
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
    }

    /**
     * find the winner's name from duck Race result
     */
    public String findWinnerByID(int IDnumber) {
        List<RaceFans> winner = fans4TheWin.stream()
                .filter(RaceFans -> RaceFans.getRaceFansNumber() == IDnumber)
                .collect(Collectors.toList());
        if (!winner.isEmpty()) {
            System.out.println("please enter 1 for cash or 2 for prize to " + winner.get(0).raceFansName);
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
    public void awardPrize(Map<Integer, Duck> racers) {
        //client to choose prize
        int winningID = Race.getWinningID(racers);
        String winnerName = findWinnerByID(winningID);  //winningID
        Scanner sc = new Scanner(System.in);
        int prizeNum = sc.nextInt();
        if (prizeNum == 1) {
            System.out.println(Award.CASH.toString() + " to " + winnerName);
            setAward(Award.CASH);
        } else {
                System.out.println(Award.PRIZE.toString() + " to " + winnerName);
                setAward(Award.PRIZE);
        }
        //update winnersOnboard
        Map<Award, Integer> prizeCount = new HashMap<>();
        boolean isWinnerOnBoard = winnersOnBoard.containsKey(winnerName);
        if (isWinnerOnBoard) {
            prizeCount = winnersOnBoard.get(winnerName);
            Set<Award> awardsSet = prizeCount.keySet();
            for (Award awardInMap : awardsSet) {
                if (awardInMap.equals(award)) {
                    prizeCount.put(awardInMap, (prizeCount.get(awardInMap) + 1));
                    winnersOnBoard.put(winnerName, prizeCount);
                } else {
                    prizeCount.put(award, 1);
                    winnersOnBoard.put(winnerName, prizeCount);
                }
            }
        } else {
            prizeCount.put(award, 1);
            winnersOnBoard.put(winnerName, prizeCount);
        }
    }

    /**
     * Records the result to a text(?) file, updating it to the list of awards.
     */

    public void updateBoard() throws IOException {
        BufferedWriter updateB = new BufferedWriter(new FileWriter(path));
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
    }
}




