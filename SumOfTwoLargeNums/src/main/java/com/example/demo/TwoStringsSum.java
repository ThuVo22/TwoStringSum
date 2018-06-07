package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This program is used to find to total of two strings as integers. It will
 * prompt the users two strings as integers and then find the addition of them
 * in the order from right to left.
 * 
 * @author Thu Vo.
 */
@SpringBootApplication
public class TwoStringsSum {
   
    public static void main(String[] args) {
        SpringApplication.run(TwoStringsSum.class, args);
    }

    /**
     * This method is used to find the sum of two strings.
     * 
     * @param a
     *            string a.
     * @param b
     *            string b.
     */
    public static String findSum(final String numberA, final String numberB) {
        String result = "";
        int a = 0;
        int b = 0;
        int carry = 0;
        int indexA = 0;
        int indexB = 0;
        int sum = 0;
        int max = numberA.length() > numberB.length() ? numberA.length() : numberB.length();
        for (int i = 0; i < max; i++) {
            a = b = 0;
            indexA = numberA.length() - i - 1;
            indexB = numberB.length() - i - 1;
            if (indexA >= 0) {
                a = numberA.charAt(indexA) - '0';
            }
            if (indexB >= 0) {
                b = numberB.charAt(indexB) - '0';
            }
            sum = a + b + carry;
            carry = sum / 10;
            sum = sum % 10;
            result = sum + result;
        }
        if (carry > 0) {
            result = carry + result;
        }
        return result == "" ? "0" : result;
    }
}