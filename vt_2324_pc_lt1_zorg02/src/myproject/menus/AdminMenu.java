package myproject.menus;

import myproject.*;
import myproject.registers.*;

import java.util.Scanner;

public class AdminMenu extends BaseMenu {

    public AdminMenu(ProgramState programState, UserRegister userRegister, PatientRegister patientRegister, MedicationRegister medicationRegister) {
        super(programState, userRegister, patientRegister, medicationRegister);
    }

    @Override
    public void displayMenu() {
        var scanner = new Scanner(System.in);

        boolean nextCycle = true;
        while(nextCycle) {
            System.out.println("Currently logged in as Admin");
            System.out.format("%d:  STOP\n", 0);
            System.out.format("%d:  Show Users\n", 1);
            System.out.format("%d:  Add a User\n", 2);
            System.out.format("%d:  Change a User\n", 3);
            System.out.format("%d:  Delete a User\n", 4);
            System.out.print("enter #choice: ");

            int choice = scanner.nextInt();
            switch (MenuOptions.AdminMenuOption.values()[choice]) {
                case STOP -> {
                    nextCycle = false;
                    System.out.println("Logged out as Admin");
                    System.out.format("%s\n", "-".repeat(80));
                }
                case USERS -> userRegister.showUsers();
                case ADD -> userRegister.addNewUser();
                case CHANGE -> System.out.println("Under construction...");
                case DELETE -> userRegister.removeUserFromRegisterWithID(programState);
                default -> System.out.println("Please enter a *valid* digit");
            }
        }
    }
}
