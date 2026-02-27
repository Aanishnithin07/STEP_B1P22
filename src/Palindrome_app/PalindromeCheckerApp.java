package Palindrome_app;

import java.util.*;

/**
 * =====================================================
 * MAIN CLASS - PalindromeCheckerApp
 * =====================================================
 *
 * All palindrome validation techniques combined.
 * Now accepts input from user.
 *
 * @author Aanish Nithin A
 * @version 9.0
 */

public class PalindromeCheckerApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("     PALINDROME CHECKER     ");
        System.out.println("====================================");

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.println("-------------------------------------");

        System.out.println("UC2 - Half Loop            : " + checkHalfLoop(input));
        System.out.println("UC3 - Reverse String       : " + checkReverse(input));
        System.out.println("UC4 - Two Pointer          : " + checkTwoPointer(input));
        System.out.println("UC5 - Stack                : " + checkStack(input));
        System.out.println("UC6 - Queue + Stack        : " + checkQueueStack(input));
        System.out.println("UC7 - Deque Optimized      : " + checkDeque(input));
        System.out.println("UC8 - Linked List          : " + checkLinkedList(input));
        System.out.println("UC9 - Recursive            : " + checkRecursive(input, 0, input.length() - 1));
        System.out.println("UC10 - Case & Space Ignore : " + checkIgnoreCaseAndSpace(input));
        PalindromeChecker checker = new PalindromeChecker(input);
        System.out.println("UC11 - OOP Service         : " + checker.checkPalindrome());

        scanner.close();
    }

    // UC2 - Half Loop
    public static boolean checkHalfLoop(String input) {
        for (int i = 0; i < input.length() / 2; i++) {
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // UC3 - Reverse String
    public static boolean checkReverse(String input) {
        StringBuilder reversed = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            reversed.append(input.charAt(i));
        }
        return input.contentEquals(reversed);
    }

    // UC4 - Two Pointer
    public static boolean checkTwoPointer(String input) {
        char[] chars = input.toCharArray();
        int start = 0;
        int end = chars.length - 1;

        while (start < end) {
            if (chars[start] != chars[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // UC5 - Stack
    public static boolean checkStack(String input) {
        Stack<Character> stack = new Stack<>();
        for (char c : input.toCharArray()) {
            stack.push(c);
        }

        for (char c : input.toCharArray()) {
            if (c != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    // UC6 - Queue + Stack
    public static boolean checkQueueStack(String input) {
        Queue<Character> queue = new LinkedList<>();
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray()) {
            queue.add(c);
            stack.push(c);
        }

        while (!queue.isEmpty()) {
            if (queue.remove() != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    // UC7 - Deque
    public static boolean checkDeque(String input) {
        Deque<Character> deque = new LinkedList<>();

        for (char c : input.toCharArray()) {
            deque.addLast(c);
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    // =====================================================
    // UC11 - Object-Oriented Palindrome Service
    // =====================================================
    /**
     * Encapsulates palindrome logic following Single Responsibility Principle.
     * Internally uses a Stack to verify palindrome.
     */
    static class PalindromeChecker {
        private final String input; // Encapsulated field

        // Constructor - accepts the string to check
        public PalindromeChecker(String input) {
            this.input = input;
        }

        // Public method - single responsibility: check palindrome
        public boolean checkPalindrome() {
            Stack<Character> stack = new Stack<>();
            char[] chars = input.toCharArray();

            // Push all characters onto the stack
            for (char c : chars) {
                stack.push(c);
            }

            // Pop from stack (reversed order) and compare with original
            for (char c : chars) {
                if (c != stack.pop()) {
                    return false;
                }
            }
            return true;
        }
    }

    // =====================================================
    // UC10 - Case-Insensitive & Space-Ignored Palindrome Checker
    // =====================================================
    public static boolean checkIgnoreCaseAndSpace(String input) {
        // Step 1: Normalize - remove all non-alphanumeric characters and convert to lowercase
        String normalized = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        // Step 2: Two pointer comparison on normalized string
        int start = 0;
        int end = normalized.length() - 1;

        while (start < end) {
            if (normalized.charAt(start) != normalized.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    // =====================================================
    // UC9 - Recursive Palindrome Checker
    // =====================================================
    public static boolean checkRecursive(String input, int start, int end) {
        // Base condition: if start >= end, all characters matched
        if (start >= end) {
            return true;
        }
        // If characters at start and end don't match, not a palindrome
        if (input.charAt(start) != input.charAt(end)) {
            return false;
        }
        // Recursive call: move start forward and end backward
        return checkRecursive(input, start + 1, end - 1);
    }

    // =====================================================
    // UC8 - Linked List Based Palindrome Checker
    // =====================================================
    // Inner Node class for singly linked list
    static class Node {
        char data;
        Node next;

        Node(char data) {
            this.data = data;
            this.next = null;
        }
    }

    public static boolean checkLinkedList(String input) {
        if (input == null || input.length() == 0) return true;

        // Step 1: Build the singly linked list from the string
        Node head = null;
        Node tail = null;
        for (char c : input.toCharArray()) {
            Node newNode = new Node(c);
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        // Step 2: Find the middle using Fast & Slow pointer technique
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;       // moves 1 step
            fast = fast.next.next;  // moves 2 steps
        }
        // 'slow' is now at the start of the second half

        // Step 3: In-place reversal of the second half
        Node prev = null;
        Node current = slow;
        while (current != null) {
            Node nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }
        Node reversedHead = prev; // head of reversed second half

        // Step 4: Compare first half and reversed second half
        Node left = head;
        Node right = reversedHead;
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }
}