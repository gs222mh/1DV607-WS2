package view;

import controller.*;
import model.Member;
import model.Boat;
import model.Storage;
import org.json.JSONException;


public class UserInterface {
    private static final String MEMBER_PN = "Member's PersonNumber:";
    Console value = new Console();
    Member member;
    Boat boat;
    Register register = new Register();
    Remove remove = new Remove();
    Update update = new Update();
    List list;
    Storage check = new Storage();

    public UserInterface(){
        //Implement the class to call the methods
    }

    public void start() throws JSONException {

        while (true) {
            value.printAsterisk();
            System.out.println("Welcome to the yacht Club! \nChose an option below");
            value.printAsterisk();
            System.out.print("1. Register a Member \n2. Remove a Member \n3. Update Member's Information \n4. Check a Member's Information\n");
            value.printAsterisk();
            System.out.println("5. Register a Boat \n6. Remove a Boat \n7. Update Boat's Information");
            value.printAsterisk();
            System.out.println("8. Show Compact List \n9. Show Verbose List \n10. Close the program");
            value.printAsterisk();

            System.out.println("Enter the option's number:");
            String chose = value.myChoice();
            // if Statements so the user chose an option
            if (chose.equals("1")) {
                // choice 1 is to add a member
                System.out.println("Member's name (Between 2 and 40 characters):");
                value.name();
                System.out.println("Member's PersonalNumber:");
                value.pn();
                member = new Member(value.name, value.pn);
                if (!register.check(member)) {
                    register.addMember(member);
                } else
                    System.out.println("This PersonalNumber is already existed");

                // choice 2 is to remove a member

            } else if (chose.equals("2")) {
                System.out.println(MEMBER_PN);
                value.pn();
                member = new Member(value.name, value.pn);
                if (register.check(member)) {
                    remove.removeMember(member, register);
                } else
                    value.feedBackNotExisted();

                // choice 3 is to Update Member information
            } else if (chose.equals("3")) {
                System.out.println("Member's PersonalNumber:");
                value.pn();
                member = new Member(value.name, value.pn);
                if (register.check(member)) {
                    String oldPN = value.pn;
                    System.out.println("New member name (At least 2 and Max 40 characters): ");
                    value.name();
                    System.out.println("New member PersonalNumber: ");
                    value.pn();
                    member = new Member(value.name, value.pn);
                    update.updateMember(member, register, oldPN);
                } else {
                    value.feedBackNotExisted();
                }

                // choice 4 is to Check Member informaion
            } else if (chose.equals("4")) {
                System.out.println(MEMBER_PN);
                value.pn();
                member = new Member(value.name, value.pn);
                register = new Register();
                register.search(member);
                if (!register.result) {
                    value.feedBackNotExisted();
                } else
                    System.out.println(register.search(member));

                // choice 5 is to Register a boat
            } else if (chose.equals("5")) {
                System.out.println(MEMBER_PN);
                value.pn();
                member = new Member(value.name, value.pn);
                if (register.check(member)) {
                    System.out.println("Boat's type (Select an option):");
                    System.out.println("1.Sailboat \n2.Motorsailer \n3.kayak/Canoe \n4.Other");
                    value.type();
                    System.out.println("Boat's size:");
                    value.size();
                    boat = new Boat(value.type, value.size);
                    register.addBoat(boat, member);
                } else {
                    value.feedBackNotExisted();
                }
                // choice 6 is to Remove a boat
            } else if (chose.equals("6")) {
                System.out.println(MEMBER_PN);
                value.pn();
                member = new Member(value.name, value.pn);
                if (register.check(member)) {
                    remove.listBoat(member, register);
                    if (remove.size != 0) {
                        System.out.println("Chose the boat you would like to remove (write the number)");
                        value.number(remove.size);
                    } else {
                        System.out.println("This person does not have any boat");
                    }
                    if (value.num != 0 && remove.size != 0)
                        remove.removeBoat(member, register, value.num);
                } else {
                    value.feedBackNotExisted();
                }

                // choice 7 is to Update the boat
            } else if (chose.equals("7")) {
                System.out.println(MEMBER_PN);
                value.pn();
                member = new Member(value.name, value.pn);
                if (register.check(member)) {
                    remove.listBoat(member, register);
                    if (remove.size != 0) {
                        System.out.println("Chose the boat you would like to edit (write the number)");
                        value.number(remove.size);
                    } else {
                        System.out.println("This person does not have any boat");
                    }
                    if (value.num != 0 && remove.size != 0) {
                        System.out.println("1.Sailboat \n2.Motorsailer \n3.kayak/Canoe \n4.Other");
                        value.type();
                        System.out.println("Boat's new size:");
                        value.size();
                        boat = new Boat(value.type, value.size);
                        update.updateBoat(boat, member, register, value.num);
                    }
                } else {
                    value.feedBackNotExisted();
                }

                // choice 8 is List compact
            } else if (chose.equals("8")) {
                list = new List();
                list.compact(register);

                // choice 9 is to list verbose
            } else if (chose.equals("9")) {
                list = new List();
                list.verbose(register);

                // choice 10 is to close the application
            } else if (chose.equals("10")) {
                check.dbWrite(register.listList);
                break;
            } else {
                System.out.println("This is not an option. Please try again..");
            }
        }
    }
}
