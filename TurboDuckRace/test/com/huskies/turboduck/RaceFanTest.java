package com.huskies.turboduck;

import com.huskies.turboduck.models.Color;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RaceFanTest {


    @Test
    public void testColorIsYellowWithNull() {
        RaceFan fan = new RaceFan(1, "name", null);
        assertEquals(Color.YELLOW, fan.getPreferredColor());
    }

}
