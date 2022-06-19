package com.hamdaankhalid.rpi.converter;


import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StringConverterTest {


    @Test
    public void testStringConverts() {
        List<String> output = new StringConverter(10, 10).convert("Hello World! I am Hamdaan");
        System.out.println(output);
        for(String row : output) {
            assertEquals(10, row.length());
        }
    }

    @Test
    public void testScreenTooSmallException() {
        try {
            new StringConverter(3, 10).convert("Hello World! I am Hamdaan");
            fail("Never threw exception :(");
        } catch (InvalidParameterException e) {
        }
    }
}
