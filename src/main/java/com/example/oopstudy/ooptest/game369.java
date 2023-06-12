package com.example.oopstudy.ooptest;

public class game369 {
    public static void main(String[] args) {
        game369Start();
    }

    private static void game369Start() {
        for (int i = 0; i < 100; i++) {
            System.out.print(i);
            int n = i;
            while (n > 0) {
                if (n % 10 == 3 || n % 10 == 6 || n % 10 == 9) {
                    System.out.print("*");
                }
                n /= 10;
            }
            System.out.println();
        }
    }

    public static String is369(int num) {
        String result = "";
        while (num > 0) {
            if (num % 10 == 3 || num % 10 == 6 || num % 10 == 9) {
                result += "*";
            }
            num /= 10;
        }

        return result;
    }
}
