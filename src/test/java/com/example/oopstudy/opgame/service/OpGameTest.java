package com.example.oopstudy.opgame.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpGameTest {

    @Test
    public void test() {
        OpGame game = new OpGame(new PlusOperatorLevelOne());
        game.makeQuestion();
        String question = game.getQuestion();

        assertEquals("1 + 1 = ", question);
        assertEquals(3, game.getRemainingAnswer());
        assertEquals(false, game.isAnswer(3));
        assertEquals(2, game.getRemainingAnswer());
        assertEquals("한번 더 해보자", game.getCheeringUpMsg());
        assertEquals(true, game.isAnswer(2));
    }

    // 10 미만의 랜덤한 수가 잘 나오는지 확인
    @Test
    public void testRandom() {
        System.out.println((int)(Math.random() * 10));
    }

    // 둘을 더했을 때 10 미만인지 확인
    @Test
    public void testSumMax() {
        int max = 10;
        int a = (int)(Math.random() * max);
        int b = (int)(Math.random() * (max - a));
        System.out.printf("%d + %d = %d", a, b, a + b);
        assertTrue(a + b < max);
    }
}