package myproject.menus;
import myproject.*;
import myproject.registers.*;
import java.util.Scanner;

public class UserMenu extends BaseMenu {

    public UserMenu(ProgramState programState, UserRegister userRegister, PatientRegister patientRegister, MedicationRegister medicationRegister) {
        super(programState, userRegister, patientRegister, medicationRegister);
    }

    @Override
    public void displayMenu() {
        var scanner = new Scanner(System.in);
        boolean nextCycle = true;
        while(nextCycle && programState.isLoggedIn()) {
            if(programState.getCurrentUser() == null) {
                nextCycle = false;
            }
            System.out.println("Current User: " + programState.getCurrentUser());

            if(programState.getCurrentPatient() == null) {
                System.out.println("Current Patient: none");
            } else {
                System.out.println("Current Patient: " + programState.getCurrentPatient());
            }
            System.out.println();
            System.out.println("1.  Select a Patient");
            System.out.println("2.  View data of current Patient");
            System.out.println("3.  Change data of current Patient");
            System.out.println("4.  Add new Patient");
            System.out.println("5.  Remove a Patient");
            System.out.println("6.  Show all Patients");
            System.out.println("7.  Add medication to list of medicine");
            System.out.println("8.  Measure lung capacity of current Patient");
            System.out.println("9.  Show BMI graph of current Patient");
            System.out.println();
            System.out.println("0.  Return to login menu");
            System.out.printf("%s\n", "-".repeat(80));
            System.out.print("enter #choice: ");

            int choice = scanner.nextInt();
            switch(MenuOptions.UserMenuOption.values()[choice]) {
                case RETURN:
                    nextCycle = false;
                    break;

                case SELECTPATIENT:
                    patientRegister.setCurrentPatientWithID(programState);
                    break;

                case PATIENTDATA:
                    if (programState.getCurrentPatient() == null) {
                        System.out.println("No patient selected");
                        System.out.format("%s\n", "-".repeat(80));
                        break;
                    }
                    programState.getCurrentPatient().displayFullPatientInfo(programState);
                    break;

                case CHANGE:
                    if(!programState.getCurrentUser().isAllowedToChangePatientInfo()) {
                        System.out.println("This User cant change Patient data");
                        System.out.format("%s\n", "-".repeat(80));
                        break;
                    }
                    if (programState.getCurrentPatient() == null) {
                        System.out.println("No patient selected");
                        System.out.format("%s\n", "-".repeat(80));
                        break;
                    }
                    PatientMenu patientMenu = new PatientMenu(programState, userRegister, patientRegister, medicationRegister);
                    patientMenu.displayMenu();
                    break;

                case ADD:
                    patientRegister.addNewPatient();
                    break;

                case REMOVE:
                    if(patientRegister.isEmpty()) {
                        System.out.println("No Patients registered");
                        System.out.format("%s\n", "-".repeat(80));
                        break;
                    }
                    patientRegister.removePatientFromRegisterWithID(programState);
                    break;

                case LIST:
                    if(patientRegister.isEmpty()) {
                        System.out.println("No Patients registered");
                        System.out.format("%s\n", "-".repeat(80));
                        break;
                    }
                    patientRegister.showPatients();
                    break;

                case ADDMEDICATION:
                    if(!programState.getCurrentUser().isAllowedToAddMedicine()) {
                        System.out.println("This User is not allowed to add medicine");
                        break;
                    }
                    medicationRegister.addMedication();
                    break;

                case LUNGCAPACITY:
                    if(!programState.getCurrentUser().isAllowedToMeasureLungCapacity()) {
                        System.out.println("This User is not allowed to measure lung capacity");
                        break;
                    }
                    if (programState.getCurrentPatient() == null) {
                        System.out.println("No patient selected");
                        System.out.format("%s\n", "-".repeat(80));
                        break;
                    }
                    programState.getCurrentPatient().setLungCapacity();
                    break;

                case BMI:
                    if (programState.getCurrentPatient() == null) {
                        System.out.println("No patient selected");
                        System.out.format("%s\n", "-".repeat(80));
                        break;
                    }
                    programState.getCurrentPatient().printGraph(programState.getCurrentPatient().getBmiData(), 50);
                    break;

                default:
                    System.out.println("Please enter a *valid* digit");
                    break;
            }
        }
    }
}
