package com.example.oopstudy.opgame.service;

public class MinusOperatorLevelOne implements IOperator {
    int a, b;
    @Override
    public void setA(int a) {
        this.a = a;
    }

    @Override
    public void setB(int b) {
        this.b = b;
    }

    @Override
    public void generateQuestion(int max) {
        a = (int)(Math.random() * max);
        b = (int)(Math.random() * max);
        // 음수가 나오지 않도록 a가 작을 경우 위치 변경
        if(a < b) {
            int tmp = b;
            a = b;
            b = tmp;
        }
    }

    @Override
    public String getQuestionMsg() {
        return String.format("%d - %d = ", a, b);
    }

    @Override
    public boolean isEqueals(int answer) {
        return (a - b) == answer;
    }
}
