package com.huskies.turboduck;

import com.huskies.turboduck.models.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RaceFanFactoryTest {
    String pathPass;
    String pathEmpty;
    String pathWrongColor;
    String pathException;

    @Before
    public void setUp() throws Exception {
        pathPass = ClassLoader.getSystemResource("testing/RaceFanPassTest.txt").getPath();
        pathEmpty = ClassLoader.getSystemResource("testing/RaceFanEmptyTest.txt").getPath();
        pathWrongColor = ClassLoader.getSystemResource("testing/RaceFanWrongColor.txt").getPath();
        pathException = ClassLoader.getSystemResource("testing/RaceFanThrowException.txt").getPath();
    }

    @Test
    public void testGetRaceFans_FilePathPasses() {
        List<RaceFan> fans = RaceFanFactory.getRaceFans(pathPass);
        assertEquals(5, fans.size());
        assertEquals("Levi", fans.get(0).getRaceFansName());
        assertEquals(Color.YELLOW, fans.get(0).getPreferredColor());

        assertEquals("Mary", fans.get(2).getRaceFansName());
        assertEquals(Color.BLACK, fans.get(3).getPreferredColor());
    }

    @Test
    public void testGetRaceFans_FileIsEmpty_returnsEmptyList() {
        List<RaceFan> fans = RaceFanFactory.getRaceFans(pathEmpty);
        assertTrue(fans.isEmpty());
    }

    @Test
    public void testGetRaceFans_wrongColorSetToYellowAsDefault() {
        List<RaceFan> fans = RaceFanFactory.getRaceFans(pathWrongColor);
        assertTrue(Color.YELLOW == fans.get(0).getPreferredColor());
    }

    @Test
    public void testGetRaceFans_throwsIllegalArgumentException() {
        try {
            List<RaceFan> fans = RaceFanFactory.getRaceFans(pathException);
            fail("Should have thrown exception");
        } catch (IllegalArgumentException e) {
            String message = "Each Fan in file " + pathException + " needs at least a number and name" +
            "(in that order), separated by a \":\"";
            assertEquals(message, e.getMessage());
        }
    }
}
