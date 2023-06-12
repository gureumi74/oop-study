package com.example.oopstudy.ooptest;

import java.util.ArrayList;
import java.util.List;

public class perfection {
    public static void main(String[] args) {
        // 약수들의 합이 자기 자신일 때 완전수
        int num = 28;
        System.out.println(isPerfectionNumber(num));
    }

    public static boolean isPerfectionNumber(int num) {
        // 주어진 숫자의 약수 구하기
        List<Integer> numbers = factors(num);

        // 약수의 합 구하기
        // Integer 요소들을 intValue로 바꿔서 전부 더해준다.
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();

        // 완전수 판별
        return (sum == num);
    }

    public static List<Integer> factors(int num) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= num / 2; i++) {
            if(num % i == 0) numbers.add(i);
        }
        return numbers;
    }
}
