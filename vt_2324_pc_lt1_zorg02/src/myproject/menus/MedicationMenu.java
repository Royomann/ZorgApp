package myproject.menus;

import myproject.*;
import myproject.registers.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MedicationMenu extends BaseMenu {

    public MedicationMenu(ProgramState programState, UserRegister userRegister, PatientRegister patientRegister, MedicationRegister medicationRegister) {
        super(programState, userRegister, patientRegister, medicationRegister);
    }

    @Override
    public void displayMenu() {

        Scanner scanner = new Scanner(System.in);
        boolean nextCycle = true;
        while(nextCycle && programState.isLoggedIn()) {
            System.out.format("===== Patient [%d] ==============================\n", programState.getCurrentPatient().getId());

            if(programState.getCurrentPatient().getPatientMedication().isEmpty()) {
                System.out.println("Current medication: none");
            } else {
                System.out.println("Current medication: " + programState.getCurrentPatient().getPatientMedication());
            }
            System.out.println();
            if(programState.getCurrentPatient().getPatientMedication().isEmpty()) {
                System.out.println("1. Change dosage of prescribed medication: none");
            } else {
                System.out.println("1. Change dosage of prescribed medication: " + programState.getCurrentPatient().getPatientMedication());
            }

            System.out.println("2. Stop a medicine");
            System.out.println("3. Prescribe medication");
            System.out.println();
            System.out.println("0:  Return ");
            System.out.format("%s\n", "-".repeat(80));
            System.out.print("enter #choice: ");

            int choice = scanner.nextInt();
            switch(MenuOptions.MedicationMenuOption.values()[choice]) {
                case RETURN:
                    nextCycle = false;
                    break;

                case CHANGEDOSAGE:
                    if(programState.getCurrentPatient().getPatientMedication().isEmpty()) {
                        System.out.println("No medication known for patient");
                        break;
                    }
                    int indexToChangeDosage = 1;
                    for(Medication medication: programState.getCurrentPatient().getPatientMedication()) {
                        System.out.println(indexToChangeDosage + ". " + medication);
                        indexToChangeDosage++;
                    }
                    System.out.print("Enter number of medicine to change dosage: ");
                    int medicationToChangeDosage;
                    try {
                        medicationToChangeDosage = scanner.nextInt();
                        if(medicationToChangeDosage > programState.getCurrentPatient().getPatientMedication().size() || medicationToChangeDosage < 1) {
                            System.out.println("Not a valid number");
                            break;
                        }
                        scanner.nextLine();
                        System.out.println("Changing dosage of medicine: " + programState.getCurrentPatient().getPatientMedication().get(medicationToChangeDosage - 1));
                        System.out.print("Enter new dosage: ");
                        int newDosage = scanner.nextInt();

                        programState.getCurrentPatient().getPatientMedication().get(medicationToChangeDosage - 1).setCustomDosage(newDosage); // swapping old dosage with new dosage
                        System.out.println("Successfully changed dosage");
                    } catch (InputMismatchException e) {
                        System.out.println("Please enter a valid number");
                        scanner.nextLine();
                    }
                    break;

                case REMOVE:
                    if(programState.getCurrentPatient().getPatientMedication().isEmpty()) {
                        System.out.println("No medication known for patient");
                        break;
                    }
                    System.out.println("Current medication: ");
                    int i = 1;
                    for(Medication medication: programState.getCurrentPatient().getPatientMedication()) {
                        System.out.println(i + ". " + medication);
                        i++;
                    }
                    System.out.print("Enter number to remove medicine: ");
                    int medicationToRemove = scanner.nextInt();
                    scanner.nextLine();
                    if(programState.getCurrentPatient().getPatientMedication().size() < medicationToRemove) {
                        System.out.println("Not a valid number");
                        break;
                    }
                    programState.getCurrentPatient().getPatientMedication().remove(medicationToRemove - 1);
                    System.out.println("Successfully removed medicine");
                    break;

                case PRESCRIBE:
                    System.out.println("Available medication of AV-list: ");
                    medicationRegister.displayMedicationRegister();
                    System.out.print("Enter #choice: ");
                    int medicationIndex = scanner.nextInt();
                    scanner.nextLine();
                    Medication existingMedication = medicationRegister.getMedicationRegister().get(medicationIndex - 1);
                    Medication newMedication = new Medication(existingMedication.getName(), existingMedication.getDefaultDosage(), existingMedication.isSedating(), existingMedication.isLiquid());
                    newMedication.setSedating(existingMedication.isSedating());
                    newMedication.setLiquid(existingMedication.isLiquid());

                    boolean alreadyPrescribed = false;
                    for(Medication medication: programState.getCurrentPatient().getPatientMedication()) {
                        if(medication.getName().equals(newMedication.getName())) {
                            System.out.println("This medication is already prescribed");
                            alreadyPrescribed = true;
                            break;
                            }
                        }
                        if(!alreadyPrescribed) {
                            programState.getCurrentPatient().getPatientMedication().add(newMedication);
                        }
                        break;

                        default:
                            System.out.println("Please enter a *valid* digit");
                            System.out.format("%s\n", "-".repeat(80));
                            break;
                    }
                }
            }
        }
