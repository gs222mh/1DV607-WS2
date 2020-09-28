package View;

import Control.*;
import Model.Member;
import Model.Boat;
import org.json.JSONException;

import java.io.IOException;

public class UserInterface {
    Console Value = new Console();
    Member member;
    Boat boat;
    Register register;
    Remove remove;
    List list;
    DBControl check = new DBControl();

    public void start() throws IOException, JSONException {

        while (true) {
            System.out.println("***************************");
            System.out.println("Welcome to the yacht Club! \nChose an option below");
            System.out.println("***************************");

            System.out.println("1. Register a Member \n2. Remove a Member \n3. Show Compact List \n4. Show Verbose List");
            System.out.println("5. Change a Member's Information \n6. Check a Member's Information");

            System.out.println("7. Register a Boat \n8. Remove a Boat \n9. Change a Boat's Information \n10. Close the program");
            System.out.println("***************************");

            System.out.println("Enter the option's number:");
            String chose = Value.myChoice();

            if (chose.equals("1")) {
                System.out.println("Member's name:");
                Value.name();
                System.out.println("Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (!check.check(member)) {
                    register = new Register(member);
                } else
                    System.out.println("Our DB has already this PN.");
            } else if (chose.equals("2")) {
                System.out.println("Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (check.check(member)) {
                    remove = new Remove(member);
                } else
                    System.out.println("Our DB has not this PN.");
            } else if (chose.equals("3")) {
                list = new List();
                list.compact();
            } else if (chose.equals("4")) {
                list = new List();
                list.verbose();
            } else if (chose.equals("5")) {

            } else if (chose.equals("6")) {
                System.out.println("Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                register = new Register();
                register.search(member);
                if (!register.result) {
                    System.out.println("We did not find this Member in our DB");
                }
            } else if (chose.equals("7")) {
                System.out.println("Member's PersonNumber:");
                Value.pn();
                member = new Member(Value.name, Value.pn);
                if (check.check(member)) {
                    System.out.println("Boats's type:");
                    Value.type();
                    System.out.println("Boat's size:");
                    Value.size();
                    boat = new Boat(Value.type, Value.size);
                    register = new Register(boat, member);
                } else {
                    System.out.println("Sorry we did not find this Person Number in our DB");
                }
            } else if (chose.equals("8")) {

            } else if (chose.equals("9")) {

            } else if (chose.equals("10")) {
                break;
            } else {
                System.out.println("This is NOT an option. Please try again..");
            }
        }
    }
}
