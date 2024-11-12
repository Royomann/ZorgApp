package myproject.registers;

import myproject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PatientRegister {

    private final List<Patient> patients;

    public PatientRegister() {
        this.patients = new ArrayList<>();
    }

    public List<Patient> getPatients() {
        return this.patients;
    }

    public void setCurrentPatientWithID(ProgramState programState) {
        if(patients.isEmpty()) {
            System.out.println("The myproject.Patient register is empty");
            System.out.format("%s\n", "-".repeat(80));
            return;
        }
        showPatients();
        System.out.print("Enter ID to select a Patient: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        sc.nextLine();

        if(programState.getCurrentPatient() != null && id == programState.getCurrentPatient().getId()) {
            System.out.println("This Patient is already selected");
            System.out.format("%s\n", "-".repeat(80));
            return;
        }

        boolean patientFound = false;
        for(Patient patient: patients) {
            if (patient.getId() == id) {
                programState.setCurrentPatient(patient);
                System.out.println("Current Patient selected: " + programState.getCurrentPatient());
                System.out.format("%s\n", "-".repeat(80));
                patientFound = true;
                break;
            }
        }

        if(!patientFound) {
            System.out.println("Patient with id: " + id + " not found");
            System.out.format("%s\n", "-".repeat(80));
        }
    }

    public void removePatientFromRegisterWithID(ProgramState programState) {
        if(patients.isEmpty()) {
            System.out.println("No Patients found");
            System.out.format("%s\n", "-".repeat(80));
            return;
        }
        for(Patient patient: patients) {
            System.out.println(patient);
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Patient ID to remove from register: ");
        int id = sc.nextInt();
        sc.nextLine();
        boolean found = false;

        if(programState.getCurrentPatient() != null && id == programState.getCurrentPatient().getId()) {
            patients.remove(programState.getCurrentPatient());
            programState.setCurrentPatient(null);
            System.out.println("Current Patient has been removed");
            System.out.format("%s\n", "-".repeat(80));
            return;
        }

        for(Patient patient: patients) {
            if (patient.getId() == id) {
                patients.remove(patient);
                System.out.println("Patient: " + patient + " has been removed");
                System.out.format("%s\n", "-".repeat(80));
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Patient ID: " + id + " is not known");
            System.out.format("%s\n", "-".repeat(80));
        }
    }

    public void addPatient(Patient... patients) {
        for(Patient patient: patients) {
            this.patients.add(patient);
        }
    }

    public void addNewPatient() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Give surname: ");
        String surname = sc.next();
        System.out.println("Give first name: ");
        String firstName = sc.next();

        LocalDate dateOfBirth = null;
        boolean nextCycle = true;

        while (nextCycle) {
            System.out.println("Give date of birth (dd-mm-yyyy): ");
            try {
                String dateStr = sc.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                dateOfBirth = LocalDate.parse(dateStr, formatter);
                nextCycle = false;
            } catch (Exception e) {
                System.out.println("Not a valid date");
                }
            }
            System.out.println("Give residence: ");
            String residence = sc.next();

            for(Patient p: patients) {
                if(p.getSurname().equals(surname) && p.getFirstName().equals(firstName)) {
                    System.out.println("Patient already exists");
                    System.out.format("%s\n", "-".repeat(80));
                    return;
                }
            }
            Patient patient = new Patient(surname, firstName, dateOfBirth, residence);
            patients.add(patient);
            System.out.println(patient + " is added to Patient register");
            System.out.format("%s\n", "-".repeat(80));
        }

    public void remove(Patient patient) {
        patients.remove(patient);
    }

    public void showPatients() {
        int count = 0;
        if (patients.isEmpty()) {
            System.out.println("The Patient register is empty");
            System.out.format("%s\n", "-".repeat(80));
        } else {
            System.out.println("PATIENTS:");
            for(Patient patient: patients) {
                System.out.println(patient);
                count++;
            }
            System.out.println("Total amount of patients: " + count);
            System.out.format("%s\n", "-".repeat(80));
        }
    }

    public boolean isEmpty() {
        return patients.isEmpty();
    }
}