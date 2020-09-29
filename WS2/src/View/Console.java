package View;

import Model.Control.Remove;

import java.util.Scanner;

public class Console {
    protected String chose;
    protected String name;
    protected String pn;
    protected String type;
    protected String size;
    protected int num;
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";

    public Console() {
    }

    public String myChoice() {
        Scanner scan = new Scanner(System.in);
        chose = scan.nextLine();
        if (check(chose)) {
            return chose;
        } else {
            return myChoice();
        }
    }

    public boolean check(String chose) {
        int length = chose.length();
        boolean chk = true;
        for (int i = 0; i < length; i++) {
            if ((!Character.isLetter(chose.charAt(i)))) {
                chk = true;
            } else if (Character.isAlphabetic(chose.charAt(i))) {
                chk = false;
            } else {
                chk = false;
            }
        }
        if (!chk)
            System.out.println(red + "This is NOT an option. Please try again..");

        return chk;
    }

    public void name() {
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        while (true) {
            if (name.length() < 2 || name.length() > 7) {
                System.out.println(red + "The name should be between 2 and 7 characters");
                name();
            } else {
                break;
            }
        }
    }

    public void pn() {
        Scanner scan = new Scanner(System.in);
        pn = scan.nextLine();
        while (!checkPN(pn)) {
            System.out.println(red + "Person NUmber must be 10 digits. Pleas try again");
            pn();
        }
    }


    public boolean checkPN(String chose) {
        boolean letter = true;
        boolean digit = false;
        if (chose.length() == 10) {
            digit = true;
        }
        for (int i = 0; i < pn.length(); i++) {
            if (Character.isAlphabetic(pn.charAt(i))) {
                letter = false;
                break;
            }
        }
        if (letter && digit)
            return true;
        else
            return false;
    }

    public void type() {
        Scanner scan = new Scanner(System.in);
        type = scan.nextLine();
        while (true) {
            if (type.length() < 2 || type.length() > 7) {
                System.out.println(red + "The type should be between 2 and 7 characters");
                type();
            } else {
                break;
            }
        }
    }

    public void number(int listLength) {
        if (listLength > 0) {
            Scanner scan = new Scanner(System.in);
            num = scan.nextInt();
            while (num < 1 || num > listLength) {
                System.out.println(red + "This is not an option");
                number(listLength);
            }
        } else
            System.out.println(red + "This member does not has any boat.");
    }

    public void size() {
        Scanner scan = new Scanner(System.in);
        size = scan.nextLine();
    }
}