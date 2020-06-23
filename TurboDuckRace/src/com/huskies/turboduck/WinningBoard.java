package com.huskies.turboduck;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class WinningBoard {

    public Award award;
    enum Award {PRIZE, CASH};
    public Award getAward() {
        return award;
    }
    Map<String, Map<Award, Integer>> winnersOnBoard = new TreeMap<>();

    // List of raceFans
    private List<raceFans> fans4TheWin = Arrays.asList(
            new raceFans(1,"Anna"),
            new raceFans(2,"Bob")
    );

    //getter
    public List<raceFans> getFans4TheWin() {
        return fans4TheWin;
    }

    /**
     * Allows the winner to choose the prize, and awarded to them in a grand fashion.
     */

    public String findWinnerByID(int IDnumber){
        List<raceFans> winner = fans4TheWin.stream()
                .filter(raceFans -> raceFans.getRaceFansNumber() == IDnumber )
                .collect(Collectors.toList());
        if (!winner.isEmpty()){
            return winner.get(0).raceFansName;
        } else {
            System.out.println("nobody wins");
            return null;
        }
    }

    public void awardPrize(Map<Integer, Duck> racers) {
        int winingID = Race.getWinningID(racers);
        String winnerName = findWinnerByID(winingID);

            Scanner sc = new Scanner(System.in); // create a Scanner variable
            int prizeNum = sc.nextInt();
            switch(prizeNum){
                case 1:
                    System.out.println("Cash to " + winnerName);
                    break;
                case 2:
                    System.out.println("Prize to " + winnerName);
                    break;
            }
        }

    /**
     * Records the result to a text(?) file, updating it to the list of awards.
     * @return
     */
    public void recordResult() throws IOException {

        String path = "C:\\MiniProject\\TurboDuck\\TurboDuckRace\\src\\wb.txt";
        File filename = new File(path);
        FileInputStream filestream = new FileInputStream(filename);
        InputStreamReader reader = new InputStreamReader(filestream);
        BufferedReader br = new BufferedReader(reader);
        String line = "";

        while ((line = br.readLine()) != null){
            Map<Award, Integer> prizeAwards = new HashMap<>();
            List<String> listLine = Arrays.asList(line.split(",")); //split line into a String List
            for(int i =1; i < listLine.size(); i++){
                String awardX = listLine.get(i);
                List<String> listPrize = Arrays.asList(awardX.split("-"));
                prizeAwards.put(Award.valueOf(listPrize.get(0).toUpperCase().strip()),parseInt(listPrize.get(1))); //store in the Map
            }
            winnersOnBoard.put(listLine.get(0),prizeAwards);
            /*
            String presentX = listLine.get(2);
            List<String> listPresent = Arrays.asList(presentX.split("-"));
            int presentCount = parseInt(listPresent.get(1));
            prizeAwards.put(Award.CASH,presentCount);
            */
        }
        System.out.println("exit loop");
    }
}
