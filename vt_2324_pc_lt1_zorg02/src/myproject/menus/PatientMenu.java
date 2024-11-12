package myproject.menus;

import myproject.*;
import myproject.registers.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PatientMenu extends BaseMenu {

    public PatientMenu(ProgramState programState, UserRegister userRegister, PatientRegister patientRegister, MedicationRegister medicationRegister) {
        super(programState, userRegister, patientRegister, medicationRegister);
    }

    @Override
    public void displayMenu() {
        var scanner = new Scanner(System.in);
        boolean nextCycle = true;
        while(nextCycle && programState.isLoggedIn()) {

            System.out.format("===== Patient [%d] ==============================\n", programState.getCurrentPatient().getId());
            System.out.println("What would you like to change?");
            System.out.println();
            System.out.printf("%d:  First name, currently: %s\n", 1, programState.getCurrentPatient().getFirstName());
            System.out.printf("%d:  Surname, currently: %s\n", 2, programState.getCurrentPatient().getSurname());
            System.out.printf("%d:  Date of birth, currently: %s\n", 3, programState.getCurrentPatient().getDateOfBirth());
            System.out.printf("%d:  Weight, currently: %dkg\n", 4, programState.getCurrentPatient().getWeight());
            System.out.printf("%d:  Length, currently: %dcm\n", 5, programState.getCurrentPatient().getLength());
            System.out.printf("%d:  Residence, currently: %s\n", 6, programState.getCurrentPatient().getResidence());
            if(programState.getCurrentUser().isAllowedToSeeMedicationInfo()) {
                System.out.printf("%d:  Medication, currently: %s\n", 7, programState.getCurrentPatient().getPatientMedication());
            } else if(programState.getCurrentUser().isAllowedToSeeSedatingMedication()) {
                System.out.printf("%d:  Medication, currently: %s\n", 7, programState.getCurrentPatient().getPatientSedatingMedication());
            } else {
                System.out.printf("%d:  Medication, currently: this User is not allowed to see medication\n", 7);
            }
            System.out.printf("%d:  Additional info, currently: [%s]\n", 8, programState.getCurrentPatient().getAdditionalInfo());
            System.out.printf("%d:  Delete this Patient: %s\n", 9, programState.getCurrentPatient());
            System.out.println();
            System.out.printf("%d:  Return \n", 0);
            System.out.printf("%d:  Return to login menu\n", 99);
            System.out.format("%s\n", "-".repeat(80));
            System.out.print("enter #choice: ");

            int choice = scanner.nextInt();
            switch(MenuOptions.PatientMenuOption.fromValue(choice)) {
                case RETURN:
                    nextCycle = false;
                    break;

                case FIRSTNAME:
                    System.out.print("Give a new first name: ");
                    String newFirstName = scanner.next();
                    programState.getCurrentPatient().setFirstname(newFirstName);
                    break;

                case SURNAME:
                    System.out.print("Give a new surname: ");
                    String newSurname = scanner.next();
                    programState.getCurrentPatient().setSurname(newSurname);
                    break;

                case DATE:
                    Scanner sc = new Scanner(System.in);
                    boolean cycle = true;
                    LocalDate newDateOfBirth = null;

                    while(cycle) {
                        System.out.print("Give a new date of birth (dd-mm-yyyy): ");
                        try {
                            String dateStr = sc.next();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                            newDateOfBirth = LocalDate.parse(dateStr, formatter);
                            cycle = false;
                        } catch (Exception e) {
                            System.out.println("Not a valid date");
                        }
                    }
                    programState.getCurrentPatient().setDateofbirth(newDateOfBirth);
                    break;

                case WEIGHT:
                    System.out.print("Give the new weight in kg: ");
                    int weight = scanner.nextInt();
                    programState.getCurrentPatient().setWeight(weight);
                    break;

                case LENGTH:
                    System.out.print("Give the new length: ");
                    int length = scanner.nextInt();
                    programState.getCurrentPatient().setLength(length);
                    break;

                case RESIDENCE:
                    System.out.println("Give the new residence: ");
                    scanner.nextLine();
                    String residence = scanner.nextLine();
                    programState.getCurrentPatient().setResidence(residence);
                    break;

                case MEDICATION:
                    if(!programState.getCurrentUser().isAllowedToPrescribeMedication()) {
                        System.out.println("This User is not allowed to change or prescribe medication");
                        break;
                    }
                    MedicationMenu medicationMenu = new MedicationMenu(programState, userRegister, patientRegister, medicationRegister);
                    medicationMenu.displayMenu();
                    break;

                case ADDITIONAL:
                    if(!programState.getCurrentUser().isAllowedToAddAdditionalPatientInfo()) {
                        System.out.println("This User is not allowed to add additional Patient info");
                        break;
                    }
                    System.out.println("Give new additional info: ");
                    scanner.nextLine();
                    String additionalInfo = scanner.nextLine();
                    programState.getCurrentPatient().setAdditionalInfo(additionalInfo);
                    break;

                case DELETE:
                    patientRegister.remove(programState.getCurrentPatient());
                    System.out.println("Removed the current Patient");
                    System.out.format("%s\n", "-".repeat(80));
                    programState.setCurrentPatient(null);
                    nextCycle = false;
                    break;

                case RETURNTOLOGIN:
                    nextCycle = false;
                    programState.setLoggedIn(false);
                    break;

                default:
                    System.out.println("Please enter a *valid* digit");
                    System.out.format("%s\n", "-".repeat(80));
                    break;
            }
        }
    }
}
