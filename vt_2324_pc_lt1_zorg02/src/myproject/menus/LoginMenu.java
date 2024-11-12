package myproject.menus;
import myproject.*;
import myproject.registers.*;
import java.util.Scanner;

public class LoginMenu extends BaseMenu {

    public LoginMenu(ProgramState programState, UserRegister userRegister, PatientRegister patientRegister, MedicationRegister medicationRegister){
        super(programState, userRegister, patientRegister, medicationRegister);
    }

    @Override
    public void displayMenu() {

        var scanner = new Scanner(System.in);
        boolean nextCycle = true;
        while(nextCycle) {

            programState.reset();
            programState.setLoggedIn(false);

            System.out.println("Welcome to ZorgApp, please select option: ");
            System.out.println();
            System.out.println("1. Log in as General Practitioner");
            System.out.println("2. Log in as Dentist");
            System.out.println("3. Log in as Physiotherapist");
            System.out.println("4. Log in as Pharmacist");
            System.out.println("5. Log in as Admin");
            System.out.println();
            System.out.println("0. STOP");
            System.out.printf("%s\n", "-".repeat(80));
            System.out.print("enter #choice: ");

            int choice;
            try {
                choice = scanner.nextInt();
                if (choice < 0 || choice >= MenuOptions.LoginMenuOption.values().length) {
                    throw new IllegalArgumentException("Choice out of bounds");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, please enter a valid choice.");
                scanner.nextLine();
                continue;
            }
            switch(MenuOptions.LoginMenuOption.values()[choice]) {
                case STOP -> nextCycle = false;
                case GENERALPRACTITIONER -> loginUserByType("GeneralPractitioner");
                case DENTIST -> loginUserByType("Dentist");
                case PHYSIOTHERAPIST -> loginUserByType("Physiotherapist");
                case PHARMACIST -> loginUserByType("Pharmacist");
                case ADMIN -> {
                    AdminMenu adminMenu = new AdminMenu(programState, userRegister, patientRegister, medicationRegister);
                    adminMenu.displayMenu();
                }
            }
        }
        System.out.println("Exiting ZorgApp. Thank you for using our system.");
    }

    public void loginUserByType(String userType) {
        if(userRegister.containsUserType(userType)) {
            System.out.println("Please select a " + userType);
            userRegister.showUsersByUserType(userType);
            programState.setCurrentUser(userRegister.selectUserByTypeWithID(userType, programState));
            if(programState.getCurrentUser() == null) {
                System.out.println("Invalid " + userType + ", please try again.");
                System.out.format("%s\n", "-".repeat(80));
                return;
            }
        } else {
            System.out.println("No " + userType + "s registered, please register.");
            userRegister.addNewUserWithType(userType);
            return;
        }
        System.out.println("Logged in as " + userType + ": " + programState.getCurrentUser());
        programState.setLoggedIn(true);
        UserMenu userMenu = new UserMenu(programState, userRegister, patientRegister, medicationRegister);
        userMenu.displayMenu();
    }
}