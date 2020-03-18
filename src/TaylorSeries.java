import java.util.Scanner;

public class TaylorSeries {
    private static final double PI = Math.PI;
    private static final int ITERATIONS = 10;
    private static boolean isRadian;


    public static void main(String[] args) {
        fun();
    }


    public static void fun() {
        double originalValue = askForValue();
        double value = originalValue;
        if (!isRadian) value = convertToRadians(value);
        value = moveBetweenPeriod(value);
        value = changeBasedOnQuarter(value);
        display(value);
    }

    private static double askForValue() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("If value in radians type r, else if in degrees press d");
        String response = scanner.nextLine();
        double value = scanner.nextDouble();
        if ("R".equals(response.toUpperCase())) {
            isRadian = true;
        } else if ("D".equals(response.toUpperCase())) {
            isRadian = false;


        } else {
            System.out.println("value is not correct");
            throw new IllegalArgumentException();
        }

        return value;
    }


    private static double convertToRadians(double value) {
        System.out.println("after converting to radian" +value * PI / 180);
        return value * PI / 180;
    }

    private static double moveBetweenPeriod(double value) {
        double result = value;
        if (result >= 0)
            while (result > 2 * PI) {
                result -= 2 * PI;
            }
        else {
            while (result < 0) {
                result += 2 * PI;
            }
        }
        System.out.println("after changing period" + result);
        return result;

    }

    private static double changeBasedOnQuarter(double value) {
        double result;
        if (value < PI / 2) result = value;
        else if (value < PI) result = PI - value;
        else if (value < 3 * PI / 2) result = -(value - PI);
        else if (value < 2 * PI) result = -(2 * PI - value);
        else {
            System.out.println("should never happen");
            result = -1;
        }
        System.out.println("after quarter " + result);
        return result;
    }


    private static void display(double value) {
        double approxValue;
        double difference;
        for (int i = 0; i < ITERATIONS; ++i) {
             approxValue =calculateTaylor(value,i);
             difference = Math.sin(value) - approxValue;
            System.out.println("For " + (i+1) + " iteration difference is    " + difference);

        }
    }

    private static double calculateTaylor(double v,int n){
        int coefficient = 1;
        double current;
        double sum = 0;
        for(int i = 0; i<=n; ++i){
            current = Math.pow(v,coefficient)/factorial(coefficient);
            if(i%2 == 1)
                current = -current;
                sum+=current;
                coefficient += 2;
        }
        return sum;
    }





        private static double factorial(int n) {
            double fact = 1;
            for (int i = 2; i <= n; i++) {
                fact = fact * i;
            }
            return fact;
        }
    }



