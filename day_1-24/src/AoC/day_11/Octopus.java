package AoC.day_11;

public class Octopus {

    private int energyLevel;

    public Octopus(int energyLevel) {
        this.energyLevel = energyLevel;
    }

    public void pop() {
        energyLevel++;
    }

    public void resetEnergyLevel() {
        energyLevel = 0;
    }

    public int getEnergyLevel() {
        return energyLevel;
    }
}
