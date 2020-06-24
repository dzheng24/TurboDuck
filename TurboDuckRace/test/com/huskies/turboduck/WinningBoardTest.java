package com.huskies.turboduck;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class WinningBoardTest{

        WinningBoard wb = new WinningBoard();
        List<RaceFans> allFans = wb.getFans4TheWin();


    @Test
    public void testfindWinnerByID_shouldReturnFansObjectIfMatch() {
        assertEquals("Anna", wb.findWinnerByID(1));
    }

    @Test
    public void testFindWinnerByID_shouldReturnEmptyListIfNotMatch() {
        assertNull(wb.findWinnerByID(100));
    }

    @Test
    public void testAwardPrize_shouldReturnEnumWhenClientChooseOne() {
    }

    @Test
    public void testRecordResultIfRetriveTXT() throws IOException {
    }

    @Test
    public void testUpdateBoard() throws IOException {
        WinningBoard wbt = new WinningBoard();
        wbt.readBoard();
        wbt.updateBoard();


    }
}