package com.example.oopstudy.opgame.service;

public class OpGame {
    int remainingAnswer;
    int a, b;
    String cheeringUpMsg = "한번 더 해보자";

    public OpGame(PlusOperatorLevelOne plusOperatorLevelOne) {

    }

    // 문제 만들기
    public void makeQuestion() {
        // 3번의 기회
        remainingAnswer = 3;
        a = 1;
        b = 1;
    }

    public void makeQuestion(int max) {
        a = (int)(Math.random() * max);
        b = (int)(Math.random() * (max - a));
    }

    // 문제 가져오기
    public String getQuestion() {
        return String.format("%d + %d = ", a, b);
    }

    // 몇번 기회가 남았는지 확인
    public int getRemainingAnswer() {
        return remainingAnswer;
    }

    // 정답인지 확인
    public boolean isAnswer(int answer) {
        remainingAnswer--;
        return (a + b) == answer;
    }

    // 기회를 다 날렸을 경우
    public String getCheeringUpMsg() {
        return cheeringUpMsg;
    }

}
