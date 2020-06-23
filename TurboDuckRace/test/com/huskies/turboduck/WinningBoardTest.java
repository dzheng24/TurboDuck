package com.huskies.turboduck;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class WinningBoardTest{

        WinningBoard wb = new WinningBoard();
        List<raceFans> allFans = wb.getFans4TheWin();


    @Test
    public void testfindWinnerByID_shouldReturnFansObjectIfMatch() {
        assertEquals("Anna", wb.findWinnerByID(1));
    }

    @Test
    public void testFindWinnerByID_shouldReturnEmptyListIfNotMatch() {
        assertNull(wb.findWinnerByID(100));
    }


    @Test
    public void testRecordResultIfRetriveTXT() throws IOException {
        wb.recordResult();
    }
}