package myproject;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Patient {

    private static int patientCountToId = 1;
    private final int id;
    private int weight;
    private int length;
    private String surname;
    private String firstName;
    private String residence;
    private LocalDate dateOfBirth;
    private String additionalInfo;
    private final List<Medication> medication;
    private double lungCapacity;
    private final List<Double> bmiData;

    public Patient(int weight, int length, String surname, String firstName, LocalDate dateOfBirth, String residence) {
        this.id = patientCountToId;
        this.weight = weight;
        this.length = length;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.residence = residence;
        this.medication = new ArrayList<>();
        this.bmiData = new ArrayList<>();
        patientCountToId++;
        setWeight(weight);
    }

    //extra constructor to make adding easier(constructor overloading)
    public Patient(String surname, String firstName, LocalDate dateOfBirth, String residence) {
        this.id = patientCountToId;
        this.surname = surname;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.residence = residence;
        this.medication = new ArrayList<>();
        this.bmiData = new ArrayList<>();
        patientCountToId++;
    }

    public int calcAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public List<Double> getBmiData() {
        return this.bmiData;
    }

    public void addBmi(double bmi) {
        bmiData.add(bmi);
    }

    public double calcBmi() {
        double length = (double) this.getLength() / 100;
        if(this.getLength() != 0 && this.getWeight() != 0) {
            return this.getWeight() / (length * length);
        } else {
            return 0;
        }
    }

    public double getLungCapacity() {
        return lungCapacity;
    }

    public void setLungCapacity() {
        System.out.print("This is a lung capacity test, please enter initial lung capacity of Patient: ");
        Scanner scanner = new Scanner(System.in);
        double initialCapacity = scanner.nextDouble();
        System.out.println("Initial capacity is: " + initialCapacity);
        System.out.println("Let the Patient breath intense for 5-10 times...");
        System.out.print("Give the second lung capacity: ");
        double secondCapacity = scanner.nextDouble();
        System.out.println("Second capacity is: " + initialCapacity);
        System.out.println("Average lung capacity of the Patient is currently: " + (initialCapacity + secondCapacity) / 2);
        this.lungCapacity = (initialCapacity + secondCapacity) / 2;
        System.out.format("%s\n", "=".repeat(80));
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }

    public String getResidence() {
        return this.residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getDateOfBirth() {
        LocalDate date = this.dateOfBirth;
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");
        String text = date.format(formatters);
        LocalDate parsedDate = LocalDate.parse(text, formatters);
        return parsedDate.format(formatters);
    }

    public void setDateofbirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return this.id;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
        addBmi(calcBmi());
    }

    public int getLength() {
        return this.length;
    }

    public void setLength(int length) {
        this.length = length;
        addBmi(calcBmi());
    }

    public List<Medication> getPatientMedication() {
        return medication;
        }

    public List<Medication> getPatientSedatingMedication() {
        return medication.stream()
                .filter(m -> m.isSedating())
                .collect(Collectors.toList());
    }

    public String getAdditionalInfo() {
        return Objects.requireNonNullElse(this.additionalInfo, "No additional information");
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public void printGraph(List<Double> bmiData, int maxWidth) {
        double maxBMI = bmiData.stream().mapToDouble(Double::doubleValue).max().orElse(1);

        System.out.println("BMI Graph of: [" + this.getId() + "] ," + this.getFirstName() + " " + this.getSurname());
        for (int i = 0; i < bmiData.size(); i++) {
            int barLength = (int) ((bmiData.get(i) / maxBMI) * maxWidth);
            String bar = new String(new char[barLength]).replace('\0', '*');
            System.out.printf("Data %d: |%s| (%.1f)\n", i + 1, bar, bmiData.get(i));
            System.out.format("%s\n", "=".repeat(80));
        }
    }

    public void displayFullPatientInfo(ProgramState programState) {
        System.out.format("===== Patient [%d] ==============================\n", id);
        System.out.format("%-17s %s\n", "Surname:", this.getSurname());
        System.out.format("%-17s %s\n", "firstName:", this.getFirstName());
        System.out.format("%-17s %s\n", "Date of birth:", this.getDateOfBirth());
        System.out.format("%-17s %s years\n", "Age:", this.calcAge());
        System.out.format("%-17s %s\n", "Residence:", this.getResidence());
        System.out.format("%-17s %dkg\n", "Weight:", this.getWeight());
        System.out.format("%-17s %dcm\n", "Length:", this.getLength());
        System.out.format("%-17s %.2f\n", "BMI:", this.calcBmi());
        System.out.format("%-17s %.2fL\n", "Lung capacity:", this.getLungCapacity());
        if(programState.getCurrentUser().isAllowedToSeeMedicationInfo()) {
            System.out.format("%-17s %s\n", "Medication:", this.getPatientMedication());
        } else if(programState.getCurrentUser().isAllowedToSeeSedatingMedication()) {
            System.out.format("%-17s %s\n", "Medication:", this.getPatientSedatingMedication());
        } else {
            System.out.println("Medication: none");
        }
        System.out.format("%-17s %s\n", "Additional info:", this.getAdditionalInfo());
        System.out.format("%s\n", "=".repeat(80));
    }

    @Override
    public String toString() {
        return String.format("[%s] %s %s %s %s", this.getId(), this.getFirstName(), this.getSurname(), this.getDateOfBirth(), this.getResidence());
    }
}