package myproject.menus;

public class MenuOptions {

    public enum LoginMenuOption {
        STOP("Stop"),
        GENERALPRACTITIONER("GeneralPractitioner"),
        DENTIST("Dentist"),
        PHYSIOTHERAPIST("Physiotherapist"),
        PHARMACIST("Pharmacist"),
        ADMIN("Admin");

        private final String userType;

        LoginMenuOption(String userType) {
            this.userType = userType;
        }

        public String getUserType() {
            return userType;
        }
    }

    public enum UserMenuOption {
        RETURN,
        SELECTPATIENT,
        PATIENTDATA,
        CHANGE,
        ADD,
        REMOVE,
        LIST,
        ADDMEDICATION,
        LUNGCAPACITY,
        BMI;
    }

    public enum PatientMenuOption {
        RETURN(0),
        RETURNTOLOGIN(99),
        FIRSTNAME(1),
        SURNAME(2),
        DATE(3),
        WEIGHT(4),
        LENGTH(5),
        RESIDENCE(6),
        MEDICATION(7),
        ADDITIONAL(8),
        DELETE(9);

        private final int value;

        public static PatientMenuOption fromValue(int value) {
            for(PatientMenuOption option: values()) {
                if(option.getValue() == value) {
                    return option;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + value);
        }

        PatientMenuOption() {
            this.value = ordinal();
        }

        PatientMenuOption(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum MedicationMenuOption {
        RETURN,
        CHANGEDOSAGE,
        REMOVE,
        PRESCRIBE;
    }

    public enum AdminMenuOption {
        STOP,
        USERS,
        ADD,
        CHANGE,
        DELETE
    }
}