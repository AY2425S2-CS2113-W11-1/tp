package instrument;

import exceptions.NegativeUsageException;

public abstract class Instrument {

    public String name;

    public String model;

    public int year;

    private boolean isRented = false;

    private int usage = 0;

    public abstract String playInstrument();


    public void rent() {
        isRented = true;
    }

    public void unrent() {
        isRented = false;
    }

    public boolean isRented() {
        return isRented;
    }

    public String toString() {
        return name + " | " + (isRented ? "X" : "O");
    }

    public String toFileEntry() {
        return name + " | " + model + " | " + year + " | " + (isRented ? "X" : "O") + " | " + usage;
    }

    public int getUsage() {
        return usage;
    }

    public void setUsage(int usage) throws NegativeUsageException {
        if (usage<0){
            throw new NegativeUsageException("Usage set is "+usage+". ");
        }

        this.usage = usage;
    }

    public void incrementUsage(){
        usage++;
    }
}
