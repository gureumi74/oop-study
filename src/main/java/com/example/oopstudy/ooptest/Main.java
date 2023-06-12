package com.example.oopstudy.ooptest;

public class Main {
    public static void main(String[] args) {
        // 코드 메소드로 분리하기 Ctrl + Alt + M
        // test 코드 만들기 Ctrl + Shift + T
        checkLeapYear();
    }

    private static void checkLeapYear() {
        int year = 2100;
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
            System.out.printf("%d년은 윤년입니다.", year);
        } else System.out.printf("%d년은 평년입니다.", year);
    }

    public static boolean isLeapYear(int year) {
        if(year % 400 == 0) return true;
        if(year % 100 == 0) return false;
        if(year % 4 == 0) return true;
        return false;
    }
}
