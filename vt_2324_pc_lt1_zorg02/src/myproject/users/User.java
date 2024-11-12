package myproject.users;

public abstract class User {

    private final String userName;
    private final String userType;
    private final int userId;
    private static int userCount = 1;

    public User(String name) {
        this.userName = name;
        this.userId = userCount;
        this.userType = this.getClass().getSimpleName();
        userCount++;
    }

    public boolean isAllowedToMeasureLungCapacity() {
        return false;
    }

    public boolean isAllowedToSeeDentistInfo() {
        return false;
    }

    public boolean isAllowedToSeeSedatingMedication() {
        return false;
    }

    public boolean isAllowedToSeeMedicationInfo() {
        return false;
    }

    public boolean isAllowedToAddAdditionalPatientInfo() {
        return false;
    }

    public boolean isAllowedToPrescribeMedication() {
        return false;
    }

    public boolean isAllowedToChangePatientInfo() {
        return false;
    }

    public boolean isAllowedToAddMedicine() {
        return false;
    }

    public boolean isAllowedToDeleteUsers() {
        return false;
    }

    public String getUserType() {
        return userType;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return userId;
    }

@Override
    public String toString() {
        return this.userType + ", " + "[" + this.getUserId() + "] " + this.getUserName();
    }
}