package com.huskies.turboduck;

import com.huskies.turboduck.models.Color;
import com.huskies.turboduck.models.Duck;
import com.huskies.turboduck.models.GreenDuck;
import com.huskies.turboduck.models.YellowDuck;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class WinningBoardTest{

        WinningBoard wb;
        List<RaceFan> allFans;
        Map<Integer, Duck> ducks;
        String boardTestPass;
        String boardTestEmpty;
        String boardTestUpdate;

    @Before
    public void setUp() throws URISyntaxException {
        wb = new WinningBoard();
        ducks = new HashMap<>();
        ducks.put(1, new GreenDuck("Anna", Color.GREEN));
        ducks.put(2, new YellowDuck("George", Color.YELLOW));
        ducks.put(3, new YellowDuck("Bob", Color.YELLOW));
        ducks.get(1).move();
        allFans = RaceFanFactory.getRaceFans(ducks);

        wb.setFans4TheWin(allFans);

        boardTestPass = Paths.get(getClass().getClassLoader().getResource("testing/ReadBoardTestPass.txt").toURI()).toString();
        boardTestEmpty = Paths.get(getClass().getClassLoader().getResource("testing/ReadBoardTestEmpty.txt").toURI()).toString();
        boardTestUpdate = Paths.get(getClass().getClassLoader().getResource("testing/BoardTestUpdate.txt").toURI()).toString();

    }

    @Test
    public void testFindByID_shouldReturnFansNameIfMatch() {
        assertEquals("Anna", wb.findByID(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByID_shouldThrowIllegalArgumentException_ifIDNotThere() {
        wb.findByID(100);
        fail("Should have thrown IllegalArgumentException");
    }

    @Test
    public void testReadBoard_Passes() {
        wb.setPath(boardTestPass);
        wb.readBoard();
        var board = wb.getWinnersOnBoard();
        assertEquals(1, board.get("Anna").get(WinningBoard.Award.PRIZE).intValue());
        assertEquals(2, board.get("George").get(WinningBoard.Award.CASH).intValue());

    }

    @Test
    public void testReadBoard_emptyBoard_withEmptyFile() {
        wb.setPath(boardTestEmpty);
        wb.readBoard();
        assertTrue(wb.getWinnersOnBoard().isEmpty());
    }

    @Test
    public void testAwardPrize_updatesWinningBoardWithPrize() {
        wb.awardPrize(ducks, 2);
        var board = wb.getWinnersOnBoard();
        assertEquals(1, (int) board.get("Anna").get(WinningBoard.Award.PRIZE));
    }

    @Test
    public void testAwardPrize_updatesWinningBoardWithCash() {
        wb.awardPrize(ducks, 1);
        var board = wb.getWinnersOnBoard();
        assertEquals(1, (int) board.get("Anna").get(WinningBoard.Award.CASH));
    }

    @Test
    public void testUpdateBoard_overwritesWithNewValue() {
        wb.setPath(boardTestUpdate);
        wb.readBoard();
        wb.printWinningBoard();
        wb.awardPrize(ducks, 1);
        wb.updateBoard();
        wb.printWinningBoard();
    }

    @Test
    public void testUpdateBoard_addsInNewWinner_whenNotPresent() {
        wb.setPath(boardTestUpdate);
        wb.readBoard();
        wb.printWinningBoard();
        System.out.println();
        String newEntry = LocalDateTime.now().toString();
        ducks.put(6, new GreenDuck(newEntry, Color.GREEN));
        while(ducks.get(1).getDistanceTraveled() > ducks.get(6).getDistanceTraveled()) {
            ducks.get(6).move();
        }
        wb.setFans4TheWin(RaceFanFactory.getRaceFans(ducks));
        wb.awardPrize(ducks, 2);
        wb.updateBoard();
        wb.printWinningBoard();

    }

    @Test
    public void testPrintBoard() {
        wb.setPath(boardTestPass);
        wb.printWinningBoard();
    }
}