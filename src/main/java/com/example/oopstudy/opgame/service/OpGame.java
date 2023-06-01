package com.example.oopstudy.opgame.service;

public class OpGame {
    int remainingAnswer;
    String cheeringUpMsg = "한번 더 해보자";
    IOperator op;

    public OpGame(IOperator op) {
        this.op = op;
    }

    // 문제 만들기
    public void makeQuestion() {
        // 3번의 기회
        remainingAnswer = 3;
        op.setA(1);
        op.setB(1);
    }

    public void makeQuestion(int max) {
        op.generateQuestion(max);
    }

    // 문제 가져오기
    public String getQuestion() {
        return op.getQuestionMsg();
    }

    // 몇번 기회가 남았는지 확인
    public int getRemainingAnswer() {
        return remainingAnswer;
    }

    // 정답인지 확인
    public boolean isAnswer(int answer) {
        remainingAnswer--;
        return op.isEqueals(answer);
    }

    // 기회를 다 날렸을 경우
    public String getCheeringUpMsg() {
        return cheeringUpMsg;
    }

}
