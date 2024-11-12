package myproject.registers;

import myproject.users.*;
import myproject.*;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class UserRegister {

    private final List<User> users;

    public UserRegister() {
        this.users = new ArrayList<>();
    }

    public void addUser(User... users) {
        for(User user: users) {
            this.users.add(user);
        }
    }

    public List<User> getUserRegister() {
        return users;
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void addNewUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("What type of User?");
        String userType = sc.next();
        addNewUserWithType(userType);
    }

    public void addNewUserWithType(String userType) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Enter ID: ");
            int id = sc.nextInt();
            boolean idExists = false;
            for(User user: users) {
                if(user.getUserId() == id) {
                    System.out.println("User ID already exists");
                    idExists = true;
                    break;
                }
            }
            if(!idExists) {
                System.out.println("Enter Username: ");
                String userName = sc.next();
                User newUser = null;
                switch(userType) {

                    case "GeneralPractitioner":
                        newUser = new GeneralPractitioner(userName);
                        break;

                    case "Dentist":
                        newUser = new Dentist(userName);
                        break;

                    case "Physiotherapist":
                        newUser = new Physiotherapist(userName);
                        break;

                    case "Pharmacist":
                        newUser = new Pharmacist(userName);
                        break;

                    default:
                        System.out.println("Error");
                        break;
                }
                users.add(newUser);
                System.out.println("User added: " + newUser);
                break;
            }
        }
    }

    public void removeUserFromRegisterWithID(ProgramState programState) {
        if(users.isEmpty()) {
            System.out.println("No Users found");
            return;
        }

        for(User user: users) {
            System.out.println(user);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter User ID to remove from register: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        if(programState.getCurrentUser() != null && id == programState.getCurrentUser().getUserId()) {
            users.remove(programState.getCurrentUser());
            programState.setCurrentUser(null);
            System.out.println("Current User has been removed");
            System.out.format("%s\n", "-".repeat(80));
            return;
        }
        for(User user: users) {
            if(user.getUserId() == id) {
                users.remove(user);
                System.out.println("User: " + user + " has been removed");
                System.out.format("%s\n", "-".repeat(80));
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("User ID: " + id + " is not found");
            System.out.format("%s\n", "-".repeat(80));
        }
    }

    public void showUsers() {
        int count = 0;
        if (users.isEmpty()) {
            System.out.println("No users found");
            System.out.format("%s\n", "-".repeat(80));
        } else {
            System.out.println("Users:");
            for(User user: users) {
                System.out.println(user);
                count++;
            }
            System.out.println("Total amount of Users: " + count);
            System.out.format("%s\n", "-".repeat(80));
        }
    }

    public void showUsersByUserType(String userType) {
        if(containsUserType(userType)) {
            System.out.println(userType + "s: ");
        }
        for(User user: users) {
            if(user.getUserType().equals(userType)) {
                System.out.println(user);
            }
        }
    }

    public User selectUserByTypeWithID(String userType, ProgramState programState) {
        try {
            System.out.print("Enter ID to select a User: ");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt();
            sc.nextLine();

            if(programState.getCurrentUser() != null && id == programState.getCurrentUser().getUserId()) {
                System.out.println("This User is already selected");
                System.out.format("%s\n", "-".repeat(80));
                return programState.getCurrentUser();
            }
            for(User user: users) {
                if (user.getUserType().equals(userType) && user.getUserId() == id) {
                    System.out.println("Current User selected: " + user);
                    System.out.format("%s\n", "-".repeat(80));
                    return user;
                }
            }
            System.out.println("User with ID " + id + " not found");
            System.out.format("%s\n", "-".repeat(80));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid ID.");
            System.out.format("%s\n", "-".repeat(80));
        }
        return null;
    }

    public boolean containsUserType(String userType) {
        for(User user: users) {
            if(user.getUserType().equals(userType)) {
                return true;
            }
        }
        return false;
    }
}