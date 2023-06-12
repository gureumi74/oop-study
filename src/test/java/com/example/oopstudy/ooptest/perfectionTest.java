package com.example.oopstudy.ooptest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class perfectionTest {

    @Test
    void isPerfectionNumber() {
        assertTrue(perfection.isPerfectionNumber(6));
        assertTrue(perfection.isPerfectionNumber(28));
        assertFalse(perfection.isPerfectionNumber(100));
    }
}