package myproject.registers;

import myproject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedicationRegister {

    private final List<Medication> medicationRegister;

    public MedicationRegister() {
        this.medicationRegister = new ArrayList<>();
    }

    public void add(Medication medication) {
        medicationRegister.add(medication);
    }

    public void addMedication() {
        System.out.println("Current medication list: ");
        displayMedicationRegister();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding new medication");
        System.out.print("Enter a new medicine name: ");
        String name = scanner.next();
        System.out.print("Enter the default dosage: ");
        int dosage = scanner.nextInt();
        System.out.println("Is this medication liquid?");
        System.out.println("1. yes");
        System.out.println("2. no");
        int choice = scanner.nextInt();
        System.out.println("Is this medication sedating?");
        System.out.println("1. yes");
        System.out.println("2. no");
        int choiceSedating = scanner.nextInt();
        Medication medication = new Medication(name, dosage, false, false);
        if(choice == 1) {
            medication.setLiquid(true);
        }
        if(choiceSedating == 1) {
            medication.setSedating(true);
        }
        medicationRegister.add(medication);
        System.out.println(medication + " added!");
        System.out.format("%s\n", "-".repeat(80));
    }

    public List<Medication> getMedicationRegister() {
        return medicationRegister;
    }

    public void displayMedicationRegister() {
        int index = 1;
        for(Medication medication: medicationRegister) {
            System.out.println(index + ". " + medication);
            index++;
        }
    }
}