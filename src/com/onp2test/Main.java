package com.onp2test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        //Scanner scanner = new Scanner(System.in);

        Stack<String> first = new Stack<>();
        Stack<String> second = new Stack<>();
        String string = "( ( 2 + 7 ) / 3 + ( 14 - 3 ) * 4 ) / 2";
        //String string = "7 + ( 1 + 2 ) * 4 - 3";
        //String string = "3 + 4 * 2 / ( 1 - 5 ) ^ 2";
        //String string = "12 + 3 * ( 4 + 10 / 5 ) * 3 ";
        //String string = "12 + 9";
        System.out.println("Infix: \n" + string);
        System.out.println("--------------------------------");
        String[] tab = string.split(" ");


        for (int i = 0; i < tab.length; i++) {

            char scanInToChar = tab[i].charAt(0);
            if (Character.isDigit(scanInToChar))
            {
                first.push(tab[i]);
            }

            else if (tab[i].equals(")")) {
                while (!second.lastElement().equals("(")) {
                    first.push(second.lastElement());
                    second.pop();
                }
                second.pop();
            }

            else if (tab[i].equals("(")) {
                second.push(tab[i]);
            }

            else if (second.size() == 0 || priority(tab[i]) > priority(second.lastElement())) {
                second.push(tab[i]);
            }

            else if (priority(tab[i]) <= priority(second.lastElement())) {
                while (second.size() != 0 && priority(tab[i]) <= priority(second.lastElement())) {
                    first.push(second.lastElement());
                    second.pop();
                }
                second.push(tab[i]);
            }
        }

        while (second.size() != 0) {
            String temp = second.peek();
            first.push(temp);
            second.pop();
        }
        System.out.println("First stack: " );
        printStack(first);
        System.out.println(" " );
        System.out.println("Second stack: " );
        printStack(second);


        System.out.println("----------------------------");
        String stackString = first.toString();
        //System.out.println(stackString);
        String finalString = stackString.substring(1, stackString.length() - 1);
        System.out.println("Postfix: " + finalString);
        System.out.println(" " );
        System.out.println("Start calculate:");

        String[] finalTab = finalString.split(", ");

        // Scanner scanner = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        int result = 0;

        for (int i = 0; i < finalTab.length; i++) {

            char scanInToChar = finalTab[i].charAt(0);
            if (Character.isDigit(scanInToChar)) {
                stack.push(finalTab[i]);

            }

            if (finalTab[i].equals("+")) {

                int plast = Integer.parseInt(stack.pop());
                int psecondlast = Integer.parseInt(stack.pop());
                int addresult = psecondlast + plast;
                stack.push(Integer.toString(addresult));
                printStack(stack);
            }


            if (finalTab[i].equals("-")) {

                int plast = Integer.parseInt(stack.pop());
                int psecondlast = Integer.parseInt(stack.pop());
                int addresult = psecondlast - plast;
                stack.push(Integer.toString(addresult));
                printStack(stack);
            }

            if (finalTab[i].equals("*")) {

                int plast = Integer.parseInt(stack.pop());
                int psecondlast = Integer.parseInt(stack.pop());
                int addresult = psecondlast * plast;
                stack.push(Integer.toString(addresult));
                printStack(stack);
            }


            if (finalTab[i].equals("/")) {

                int plast = Integer.parseInt(stack.pop());
                int psecondlast = Integer.parseInt(stack.pop());
                int addresult = psecondlast / plast;
                stack.push(Integer.toString(addresult));
                printStack(stack);
            }
//            if (finalTab[i].equals("^")) {
//
//                int plast = Integer.parseInt(stack.pop());
//                int psecondlast = Integer.parseInt(stack.pop());
//                int powerresult=0;
//                for (int j = 1; j <plast ; j++) {
//                   powerresult = psecondlast*psecondlast;
//                }
//                stack.push(Integer.toString(powerresult));
//                printStack(stack);
//            }


        }

        if (stack.size() == 1) {

            result = Integer.parseInt(stack.pop());
        }
        System.out.println("Current stack size is: " + stack.size());

        System.out.println("If stack is empty? " + stack.empty());
        //System.out.println("Last item of stack: " + stack.peek());
        printStack(stack);
        System.out.println("FINAL RESULT: " + result);
    }


    public static int priority(String s) {
        if (s.equals("^")) {
            return 3;
        } else if (s.equals("*") || s.equals("/") || s.equals("%")) {
            return 2;
        } else if (s.equals("+") || s.equals("-")) {
            return 1;
        }
        return 0;
    }


    public static void printStack(Stack<String> s) {
        if (s.isEmpty()) {
            System.out.println("Stack is empty!!!");
        } else {
            System.out.printf("%s, TOP\n", s);
        }

    }
}
