/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package q1;

/**
 *
 * @author A
 */import java.util.Scanner;
public class Q1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Original Phrase: ");
        String original = sc.nextLine();
        sc.close();
        int digitSum = 0;
        for (int i = 0; i < original.length(); i++) {
            char ch = original.charAt(i);
            if (Character.isDigit(ch)) {
                digitSum += (ch - '0');
            }
        }
        int shiftKey = digitSum % 26;
        if (shiftKey == 0) {
            shiftKey = 13;
        }
        String symbolSeq = "!@#$%";
        StringBuilder encodedBeforeReverse = new StringBuilder();
        for (int i = 0; i < original.length(); i++) {
            char c = original.charAt(i);

            if (Character.isLowerCase(c)) {
                int offset = c - 'a';
                offset = (offset + shiftKey) % 26;
                char newChar = (char) ('a' + offset);
                encodedBeforeReverse.append(newChar);

            } else if (Character.isUpperCase(c)) {
                if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                    encodedBeforeReverse.append('*');
                    encodedBeforeReverse.append(Character.toLowerCase(c));
                } else {
                    int offset = c - 'A';
                    offset = (offset + 1) % 26;
                    char newChar = (char) ('a' + offset);
                    encodedBeforeReverse.append(newChar);
                }

            } else if (Character.isDigit(c)) {
                int d = c - '0';
                int newDigit = (d * d) % 10;
                encodedBeforeReverse.append(newDigit);

            } else if (c == ' ') {
                encodedBeforeReverse.append('.');

            } else {
                int pos = symbolSeq.indexOf(c);
                if (pos != -1) {
                    int newPos = (pos + 2) % symbolSeq.length();
                    encodedBeforeReverse.append(symbolSeq.charAt(newPos));
                } else {
                    encodedBeforeReverse.append(c);
                }
            }
        }
        String beforeReverseStr = encodedBeforeReverse.toString();
        String finalPassword = encodedBeforeReverse.reverse().toString();
        System.out.println("Shift Key: " + shiftKey);
        System.out.println("Before Reversal: " + beforeReverseStr);
        System.out.println("Encoded Password: " + finalPassword);
    }
}
