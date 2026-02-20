package Palindrome_app;

import java.util.*;

public class PalindromeCheckerApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("======================================");
        System.out.println(" Welcome to Palindrome Checker App ");
        System.out.println(" Version 1.0 ");
        System.out.println("======================================");
        System.out.println("Objective: Validate whether a given string is a palindrome under different conditions.");

        while (true) {
            System.out.println("\nChoose a Use Case to run:");
            System.out.println("1. UC1 - Welcome Message");
            System.out.println("2. UC2 - Hardcoded Palindrome");
            System.out.println("3. UC3 - Palindrome using String Reverse");
            System.out.println("4. UC4 - Palindrome using Character Array");
            System.out.println("5. UC5 - Palindrome using Stack");
            System.out.println("6. UC6 - Palindrome using Queue + Stack");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    uc1();
                    break;
                case 2:
                    uc2();
                    break;
                case 3:
                    uc3(scanner);
                    break;
                case 4:
                    uc4(scanner);
                    break;
                case 5:
                    uc5(scanner);
                    break;
                case 6:
                    uc6(scanner);
                    break;
                case 0:
                    System.out.println("Exiting Palindrome Checker App. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    // UC1: Welcome Message
    public static void uc1() {
        System.out.println("\n[UC1] Application Entry & Welcome Message");
        System.out.println("Palindrome Checker App - Version 1.0");
    }

    // UC2: Hardcoded Palindrome
    public static void uc2() {
        System.out.println("\n[UC2] Hardcoded Palindrome Check");
        String word = "madam";
        System.out.println(word + (isPalindrome(word) ? " is a palindrome." : " is not a palindrome."));
    }

    // UC3: Palindrome using String Reverse
    public static void uc3(Scanner scanner) {
        System.out.println("\n[UC3] Palindrome Check Using String Reverse");
        System.out.print("Enter a word: ");
        String word = scanner.nextLine();
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed += word.charAt(i);
        }
        System.out.println(word + (word.equals(reversed) ? " is a palindrome." : " is not a palindrome."));
    }

    // UC4: Character Array Based Palindrome
    public static void uc4(Scanner scanner) {
        System.out.println("\n[UC4] Palindrome Check Using Character Array");
        System.out.print("Enter a word: ");
        String word = scanner.nextLine();
        char[] chars = word.toCharArray();
        int i = 0, j = chars.length - 1;
        boolean isPalindrome = true;
        while (i < j) {
            if (chars[i] != chars[j]) {
                isPalindrome = false;
                break;
            }
            i++; j--;
        }
        System.out.println(word + (isPalindrome ? " is a palindrome." : " is not a palindrome."));
    }

    // UC5: Stack-Based Palindrome
    public static void uc5(Scanner scanner) {
        System.out.println("\n[UC5] Palindrome Check Using Stack");
        System.out.print("Enter a word: ");
        String word = scanner.nextLine();
        Stack<Character> stack = new Stack<>();
        for (char c : word.toCharArray()) stack.push(c);
        String reversed = "";
        while (!stack.isEmpty()) reversed += stack.pop();
        System.out.println(word + (word.equals(reversed) ? " is a palindrome." : " is not a palindrome."));
    }

    // UC6: Queue + Stack Based Palindrome
    public static void uc6(Scanner scanner) {
        System.out.println("\n[UC6] Palindrome Check Using Queue + Stack");
        System.out.print("Enter a word: ");
        String word = scanner.nextLine();
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();
        for (char c : word.toCharArray()) {
            queue.add(c);
            stack.push(c);
        }
        boolean isPalindrome = true;
        while (!queue.isEmpty()) {
            if (!queue.remove().equals(stack.pop())) {
                isPalindrome = false;
                break;
            }
        }
        System.out.println(word + (isPalindrome ? " is a palindrome." : " is not a palindrome."));
    }


    public static boolean isPalindrome(String str) {
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) return false;
            i++; j--;
        }
        return true;
    }
}