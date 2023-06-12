package com.example.oopstudy.ooptest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class game369Test {

    @Test
    void game369Start() {
        assertEquals("*", game369.is369(3));
        assertNotEquals("*", game369.is369(15));
        assertEquals("**", game369.is369(33));
        assertEquals("***", game369.is369(333));
        assertNotEquals("****", game369.is369(333));

    }
}