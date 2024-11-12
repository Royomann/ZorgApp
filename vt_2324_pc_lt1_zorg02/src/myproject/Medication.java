package myproject;

public class Medication {

    private final String name;
    private int defaultDosage;
    private boolean sedating;
    private boolean liquid;

    public Medication(String name, int defaultDosage, boolean sedating, boolean liquid) {
        this.name = name;
        this.defaultDosage = defaultDosage;
        this.sedating = sedating;
        this.liquid = liquid;
    }

    public boolean isLiquid() {
        return liquid;
    }

    public void setLiquid(boolean liquid) {
        this.liquid = liquid;
    }

    public boolean isSedating() {
        return sedating;
    }

    public void setSedating(boolean sedating) {
        this.sedating = sedating;
    }

    public String getName() {
        return name;
    }

    public int getDefaultDosage() {
        return defaultDosage;
    }

    public void setCustomDosage(int customDosage) {
        this.defaultDosage = customDosage;
    }

    @Override
    public String toString() {
        return name + ", dosage(" + defaultDosage + ")";
    }
}