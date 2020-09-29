package View;

import Model.Control.*;
import Model.Member;
import Model.Boat;
import org.json.JSONException;

import java.io.IOException;

public class UserInterface {
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    Console Value = new Console();
    Member member;
    Boat boat;
    Register register;
    Remove remove;
    Update update;
    List list;
    DBControl check = new DBControl();

    public void start() throws IOException, JSONException {

        while (true) {
            System.out.println(blue + "***************************");
            System.out.println(blue + "Welcome to the yacht Club! \nChose an option below");
            System.out.println(blue + "***************************");

            System.out.print(blue + "1. Register a Member \t2. Remove a Member \t\t\t\t\t3. Show Compact List \n4. Show Verbose List\t5. Change a Member's Information \t6. Check a Member's Information");
            System.out.println(blue + "\n7. Register a Boat \t\t8. Remove a Boat \t\t\t\t\t9. Change a Boat's Information \n\t\t\t\t\t\t10. Close the program");
            System.out.println(blue + "***************************");

            System.out.println(yellow + "Enter the option's number:");
            String chose = Value.myChoice();

            if (chose.equals("1")) {
                System.out.println(yellow + "Member's name (Atl east 2 and Max 7 characters):");
                Value.name();
                System.out.println(yellow + "Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (!check.check(member)) {
                    register = new Register(member);
                } else
                    System.out.println(red + "Our DB has already this PN.");


            } else if (chose.equals("2")) {
                System.out.println(yellow + "Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (check.check(member)) {
                    remove = new Remove(member);
                } else
                    System.out.println(red + "Our DB has not this PN.");
            } else if (chose.equals("3")) {
                list = new List();
                list.compact();
            } else if (chose.equals("4")) {
                list = new List();
                list.verbose();
            } else if (chose.equals("5")) {
                System.out.println(yellow + "Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (check.check(member)) {
                    String oldPN = Value.pn;
                    System.out.println(green + "New member name (Atl east 2 and Max 7 characters): ");
                    Value.name();
                    System.out.println(green + "New member Person number: ");
                    Value.pn();
                    member = new Member(Value.name, Value.pn);
                    update = new Update(member, oldPN);
                } else {
                    System.out.println(red + "Sorry we did not find this Person Number in our DB");
                }
            } else if (chose.equals("6")) {
                System.out.println(yellow + "Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                register = new Register();
                register.search(member);
                if (!register.result) {
                    System.out.println(red + "Sorry we did not find this Person Number in our DB");
                }
            } else if (chose.equals("7")) {
                System.out.println(yellow + "Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (check.check(member)) {
                    System.out.println(yellow + "Boats's type (Atl east 2 and Max 7 characters):");
                    Value.type();
                    System.out.println(yellow + "Boat's size:");
                    Value.size();
                    boat = new Boat(Value.type, Value.size);
                    register = new Register(boat, member);
                } else {
                    System.out.println(red + "Sorry we did not find this Person Number in our DB");
                }
            } else if (chose.equals("8")) {
                System.out.println(yellow + "Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (check.check(member)) {
                    remove = new Remove();
                    remove.listBoat(member);
                    if (remove.size != 0) {
                        System.out.println(yellow + "Which boat do you want to remove (write the number)");
                        Value.number(remove.size);
                    }
                    if (Value.num != 0 && remove.size != 0)
                        remove.removeBoat(member, Value.num);
                } else {
                    System.out.println(red + "Sorry we did not find this Person Number in our DB");
                }
            } else if (chose.equals("9")) {
                System.out.println(yellow + "Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (check.check(member)) {
                    remove = new Remove();
                    remove.listBoat(member);
                    if (remove.size != 0) {
                        System.out.println(yellow + "Which boat do you want to edit (write the number)");
                        Value.number(remove.size);
                    }
                    if (Value.num != 0 && remove.size != 0) {
                        System.out.println(green + "Boats's new type (Atl east 2 and Max 7 characters):");
                        Value.type();
                        System.out.println(green + "Boat's new size:");
                        Value.size();
                        boat = new Boat(Value.type, Value.size);
                        update = new Update(boat, member, Value.num);
                    }
                } else {
                    System.out.println(red + "Sorry we did not find this Person Number in our DB");
                }
            } else if (chose.equals("10")) {
                break;
            } else {
                System.out.println(red + "This is NOT an option. Please try again..");
            }
        }
    }
}
