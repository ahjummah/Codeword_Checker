/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jessa
 */
import java.util.Scanner;

public class Codeword_Checker {

    public static void main(String[] args) {
        System.out.println("Enter codeword:");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        System.out.println("Parity: 1. Odd 2. Even");
        int choice = scan.nextInt();
        Codeword_Checker cc = new Codeword_Checker();
        cc.check(input, choice);
    }

    private boolean check(String input, int choice) {
        StringBuilder build = new StringBuilder();
        boolean hasError = false;
        String db = getBits(input, 1);
        String pb = getBits(input, 2);
        build.append(input);
        int two = 1;
        int error = 0;

        for (int i = 0; i < build.length(); i++) {
            char x;
            if (i == (two - 1)) {
                int ctr = 0;
                int p = i + 1;
                int tmp = p;

                for (int j = p - 1; j < db.length();) {
                    if (tmp != 0) {
                        if (db.charAt(j) == '1') {
                            ctr++;
                        }
                        tmp--;
                        j++;
                    } else {
                        tmp += p;
                        j += p;
                    }
                }
                if (choice == 1) {
                    x = ctr % 2 == 0 ? '1' : '0';
                } else {
                    x = ctr % 2 == 0 ? '0' : '1';
                }

                if (x != pb.charAt(i)) {
                    error += p;
                    hasError = true;
                }
                two *= 2;
            }
        }
        if (!hasError) {
            System.out.println("Valid");
            return true;
        } else {
            System.out.println("Invalid: " + error);
            return false;
        }
    }

    private String getBits(String input, int k) {
        StringBuilder tmp = new StringBuilder();
        String db = input;
        tmp.append(db);

        int two = 1;
        if (k == 1) {
            for (int i = 0; i < input.length(); i++) {
                if (i == (two - 1)) {
                    tmp.replace(i, i + 1, "?");
                    two *= 2;
                }
            }
        } else {
            for (int i = 0; i < input.length(); i++) {
                if (i == (two - 1)) {
                    two *= 2;
                } else {
                    tmp.replace(i, i + 1, "?");
                }
            }
        }
        return tmp.toString();
    }
}
