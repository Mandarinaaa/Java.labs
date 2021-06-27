package com.company;
public class Main {

    public static int minutes_to_seconds_1(int minutes) {
        return minutes * 60;
    }

    public static int points_2(int point2, int point3) {
        return point2 * 2 + point3 * 3;
    }

    public static int football_points_3(int wins, int draws, int losses) {
        return wins * 3 + draws;
    }

    public static boolean divisible_by_five_4(int number) {
        return number % 5 == 0;
    }

    public static boolean and_5(boolean a, boolean b) {
        return a && b;
    }

    public static int howManyWalls_6(int n, int width, int height) {
        return n / (width * height);
    }


    public static int squared(int a) {
        return a * a;
    }

    public static boolean profitableGamble_8(double prob, double prize, double pay) {
        return prob * prize > pay ;
    }

    public static int frames_9(int minutes, int fps) {
        return minutes * 60 * fps;
    }

    public static int mod_10(int a, int b) {
        return a - b * (a/b);
    }


        public static void main(String[] args) {

        System.out.println(football_points_3(6,8,5));
        }
    }
