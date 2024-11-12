package myproject;
import myproject.users.*;

public class ProgramState {

    private User currentUser;
    private Patient currentPatient;
    private boolean isLoggedIn = true;

    public ProgramState() {
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public Patient getCurrentPatient() {
        return this.currentPatient;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public void reset() {
        this.currentPatient = null;
        this.currentUser = null;
    }
}
