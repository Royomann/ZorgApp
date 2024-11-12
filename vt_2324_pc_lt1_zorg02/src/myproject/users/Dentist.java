package myproject.users;

public class Dentist extends User {

    public Dentist(String name) {
        super(name);
    }

    @Override
    public boolean isAllowedToSeeSedatingMedication() {
        return true;
    }

    @Override
    public boolean isAllowedToSeeDentistInfo() {
        return true;
    }

    @Override
    public boolean isAllowedToAddAdditionalPatientInfo() {
        return true;
    }
}