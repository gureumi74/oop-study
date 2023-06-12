package com.example.oopstudy.ooptest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void testLeapYear() {
//        assertEquals(기댓값, 실제값);
        assertEquals(true, Main.isLeapYear(2020));
        assertEquals(false, Main.isLeapYear(1900));
        assertEquals(true, Main.isLeapYear(2400));
    }
}