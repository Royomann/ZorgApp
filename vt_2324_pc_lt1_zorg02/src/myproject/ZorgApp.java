package myproject;

import myproject.users.*;
import myproject.menus.*;
import myproject.registers.*;

import java.time.LocalDate;
import java.time.Month;

public class  ZorgApp {

    
    public static void main(String[] args) {
        ZorgApp zorgapp = new ZorgApp();
        zorgapp.initializeProgram();
    }

    public void initializeProgram() {
        UserRegister userRegister = new UserRegister();
        PatientRegister patientRegister = new PatientRegister();
        MedicationRegister medicationRegister = new MedicationRegister();
        ProgramState programState = new ProgramState();

        patientRegister.addPatient(new Patient(80, 180, "Patient1", "test", LocalDate.of(1990, Month.JANUARY, 1), "1234AB, 123 Leeuwarden"));
        patientRegister.addPatient(new Patient(80, 180, "Patient2", "test", LocalDate.of(1991, Month.FEBRUARY, 1), "1234AB, 123 Amsterdam"));
        patientRegister.addPatient(new Patient(80, 180, "Patient3", "test", LocalDate.of(1992, Month.MARCH, 1), "1234AB, 123 Groningen"));
        patientRegister.addPatient(new Patient(80, 180, "Patient4", "test", LocalDate.of(1993, Month.APRIL, 1), "1234AB, 123 Rotterdam"));
        patientRegister.addPatient(new Patient(80, 180, "Patient5", "test", LocalDate.of(1994, Month.MAY, 1), "1234AB, 123 Den Haag"));

        userRegister.addUser(new GeneralPractitioner("GP"));
        userRegister.addUser(new Dentist("Dentist"));
        userRegister.addUser(new Physiotherapist("Physio"));
        userRegister.addUser(new Pharmacist("Pharmacist"));
        userRegister.addUser(new Admin("admin"));

        medicationRegister.add(new Medication("Eucalyptus olie", 10, false, true));
        medicationRegister.add(new Medication("Foliumzuur", 50, false, false));
        medicationRegister.add(new Medication("Ibuprofen", 400, false, false));
        medicationRegister.add(new Medication("Paracetamol", 400, false, false));
        medicationRegister.add(new Medication("Oxezepam", 300, true, false));

        BaseMenu loginMenu = new LoginMenu(programState, userRegister, patientRegister, medicationRegister);
        loginMenu.displayMenu();
    }
}