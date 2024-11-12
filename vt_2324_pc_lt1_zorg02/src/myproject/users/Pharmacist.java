package myproject.users;

public class Pharmacist extends User {

    public Pharmacist(String name) {
        super(name);
    }

    @Override
    public boolean isAllowedToSeeMedicationInfo() {
        return true;
    }
}