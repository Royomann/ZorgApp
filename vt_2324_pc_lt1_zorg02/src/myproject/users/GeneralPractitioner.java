package myproject.users;

public class GeneralPractitioner extends User {

    public GeneralPractitioner(String name) {
        super(name);
    }

    @Override
    public boolean isAllowedToMeasureLungCapacity() {
        return true;
    }

    @Override
    public boolean isAllowedToAddAdditionalPatientInfo() {
        return true;
    }

    @Override
    public boolean isAllowedToChangePatientInfo() {
        return true;
    }

    @Override
    public boolean isAllowedToPrescribeMedication() {
        return true;
    }

    @Override
    public boolean isAllowedToAddMedicine() {
        return true;
    }

    @Override
    public boolean isAllowedToSeeMedicationInfo() {
        return true;
    }
}