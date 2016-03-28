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
        boolean flag = false;
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
                    flag = true;
                }
                two *= 2;
            }
        }
        if (flag) {
            System.out.println("Invalid: " + error);
        } else {
            System.out.println("Valid");
        }
        return flag;
    }

    private String getBits(String input, int i) {
        StringBuilder tmp = new StringBuilder();
        String db = input;
        tmp.append(db);

        int two = 1;
        if (i == 1) {
            for (int j = 0; j < input.length(); j++) {
                if ((two - 1) == j) {
                    tmp.replace(i, i + 1, "_");
                    two *= 2;
                }
            }
        } else {
            for (int j = 0; j < input.length(); j++) {
                if ((two - 1) == j) {
                    two *= 2;
                } else {
                    tmp.replace(j, j + 1, "_");
                }
            }
        }
        return tmp.toString();
    }
}
