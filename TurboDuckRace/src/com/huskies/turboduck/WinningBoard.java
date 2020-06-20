package com.huskies.turboduck;

public class WinningBoard {
    String winningName;     // the name of the winning racer
    int winningNumber;      // the number id of the winner
    Award award;            // the award for winning the race

    enum Award {PRIZE, CASH};

    public String getWinningName() {
        return winningName;
    }

    public int getWinningNumber() {
        return winningNumber;
    }

    public Award getAward() {
        return award;
    }

    /**
     * Allows the winner to choose the prize, and awarded to them in a grand fashion.
     */
    public void awardPrize() {
        // TODO
    }

    /**
     * Records the result to a text(?) file, updating it to the list of awards.
     * @return
     */
    public boolean recordResult() {
        // TODO
        return false;
    }
}
