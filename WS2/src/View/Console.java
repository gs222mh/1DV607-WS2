package View;

import java.util.Scanner;

public class Console {
    protected String chose;
    protected String name;
    protected String pn;
    protected String type;
    protected String size;

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
            System.out.println("This is NOT an option. Please try again..");

        return chk;
    }

    public void name() {
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
    }

    public void pn() {
        Scanner scan = new Scanner(System.in);
        pn = scan.nextLine();
        while (!checkPN(pn)) {
            System.out.println("Person NUmber must be 10 digits. Pleas try again");
            pn();
        }
    }


    public boolean checkPN(String chose) {
        boolean letter = true;
        boolean digit = false;
        if (chose.length() == 10) {
            digit = true;
        }
       for (int i = 0; i < pn.length() ; i++){
           if (Character.isAlphabetic(pn.charAt(i))){
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
    }

    public void size() {
        Scanner scan = new Scanner(System.in);
        size = scan.nextLine();
    }
}