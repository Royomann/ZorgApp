package myproject.users;

public class Physiotherapist extends User {

    public Physiotherapist(String name) {
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
}