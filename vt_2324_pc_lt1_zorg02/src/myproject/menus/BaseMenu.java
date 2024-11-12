package myproject.menus;
import myproject.*;
import myproject.registers.*;

public abstract class BaseMenu {

    protected MedicationRegister medicationRegister;
    protected ProgramState programState;
    protected UserRegister userRegister;
    protected PatientRegister patientRegister;

    public BaseMenu(ProgramState programState, UserRegister userRegister, PatientRegister patientRegister, MedicationRegister medicationRegister) {
        this.programState = programState;
        this.userRegister = userRegister;
        this.patientRegister = patientRegister;
        this.medicationRegister = medicationRegister;
    }

    public abstract void displayMenu();
}

