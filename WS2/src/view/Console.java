package view;

import controller.Register;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Scanner;

public class Console {
    private static final String BOAT = "Boats";
    private static final String NOTOPTION = "This is not an option";
    protected String chose;
    protected String name;
    protected String pn;
    protected int type;
    protected String size;
    protected int num;

    public Console() {
        //Implement the class to call the methods
    }

    public String myChoice() {
        // Scanner for the choice
        Scanner scan = new Scanner(System.in);
        chose = scan.nextLine();
        if (check(chose)) {
            return chose;
        } else {
            return myChoice();
        }
    }

    // check the input, which has to be between 1 and 10
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
            System.out.println("This is not an option. Please try again..");

        return chk;
    }

    // input the member name
    public void name() {
        Scanner scan = new Scanner(System.in);
        name = scan.nextLine();
        // check the name length, work as long between 2 and 7
        while (true) {
            if (name.length() < 2 || name.length() > 40) {
                System.out.println("The name must be between 2 and 40 characters");
                name();
            } else {
                break;
            }
        }
    }

    // input the member personalnumber
    public void pn() {
        Scanner scan = new Scanner(System.in);
        pn = scan.nextLine();
        // fix the input to be 10 digits and only 10
        while (!checkPN(pn)) {
            System.out.println("The Personal Number must be exact 10 digits. Pleas try again");
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

    // input the boat's type
    public void type() {
        Scanner scan = new Scanner(System.in);
        type = scan.nextInt();
        //  check the boat's name length, work as long between 2 and 7
        while (true) {
            if (type < 1 || type > 4) {
                System.out.println(NOTOPTION);
                type();
            } else {
                break;
            }
        }
    }

    // to chose a boat from the boats list
    public void number(int listLength) {
        try {
            if (listLength > 0) {
                Scanner scan = new Scanner(System.in);
                num = scan.nextInt();
                while (num < 1 || num > listLength) {
                    System.out.println(NOTOPTION);
                    number(listLength);
                }
            } else
                System.out.println("There is no boats registered for this person");
        } catch (Exception e) {
            System.out.println(NOTOPTION);
            number(listLength);
        }
    }

    public void size() {
        Scanner scan = new Scanner(System.in);
        size = scan.nextLine();
    }

    public void feedBackNotExisted() {
        System.out.println("This PersonalNumber is not existed");
    }

    public void feedBackExisted() {
        System.out.println("Sorry we have already this PN");
    }


    public void printAsterisk() {
        System.out.println("***************************");
    }

    public void printNumberSign() {
        System.out.println("###########################");
    }

    public void printCompact(Register reg, int i) throws JSONException {
        System.out.println("Name: " + reg.listBeforeWrite.getJSONObject(i).get("name") + "\nID: " + reg.listBeforeWrite.getJSONObject(i).get("ID") + "\nNumber of Boats: " + reg.listBeforeWrite.getJSONObject(i).getJSONArray(BOAT).length());
    }

    public void printVerbose(Register reg, int i) throws JSONException {
        System.out.println("Name: " + reg.listBeforeWrite.getJSONObject(i).get("name") + "\nPN: " + reg.listBeforeWrite.getJSONObject(i).get("Personal_Number") + "\nID: " + reg.listBeforeWrite.getJSONObject(i).get("ID"));
        if (reg.listBeforeWrite.getJSONObject(i).getJSONArray(BOAT).length() != 0) {
            for (int j = 0; j < reg.listBeforeWrite.getJSONObject(i).getJSONArray(BOAT).length(); j++) {
                System.out.println("Boat " + (j + 1) + "\nType: " + reg.listBeforeWrite.getJSONObject(i).getJSONArray(BOAT).getJSONObject(j).get("type") + "\nSize: " + reg.listBeforeWrite.getJSONObject(i).getJSONArray(BOAT).getJSONObject(j).get("size"));
            }
        }
    }

    public void printboatList(JSONArray list, int i) throws JSONException {
        for (int j = 0; j < list.length(); j++) {
            System.out.println(i + ".\nType: " + list.getJSONObject(j).get("type") + "\nSize: " + list.getJSONObject(j).get("size"));
            i++;
        }
    }
}