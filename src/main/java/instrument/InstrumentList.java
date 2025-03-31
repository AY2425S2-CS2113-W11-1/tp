package instrument;

import java.util.ArrayList;

import exceptions.NegativeUsageException;
import ui.Ui;
import exceptions.EmptyDescriptionException;

public class InstrumentList {
    private static final Integer currYEAR =  2025; // Current


    private ArrayList<Instrument> instruments;
    private int numberOfInstruments;

    private Ui ui;

    public InstrumentList() {
        this.instruments = new ArrayList<>();
        this.numberOfInstruments = 0;
        ui = new Ui();
    }

    public void addInstrument(String[] userInput) {
        assert userInput.length > 0;
        String instrument = userInput[0];
        String model = userInput[1];
        int year = Integer.parseInt(userInput[2]);
        int usage = Integer.parseInt(userInput[3]);

        assert year >= 1600 && year <= currYEAR : "Invalid year: " + year;
        assert usage >= 0 && usage <= 100 : "Invalid usage: " + usage;

        if (instrument.isBlank() || model.isBlank()) {
            throw new EmptyDescriptionException("instrument or model is empty");
        }

        try {
            switch (instrument) {
            case "Flute":
                this.instruments.add(new Flute(instrument, model, year));
                this.numberOfInstruments++;
                break;
            case "Piano":
                this.instruments.add(new Piano(instrument, model, year));
                this.numberOfInstruments++;
                break;
            case "Guitar":
                this.instruments.add(new Guitar(instrument, model, year));
                this.numberOfInstruments++;
                break;
            default:
                System.out.println("invalid instrument");
                return;
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
            return;
        }

        try{
            instruments.get(numberOfInstruments - 1).setUsage(usage);
        } catch (NegativeUsageException e){
            System.out.println(e.getMessage());
        }


    }

    public void deleteInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments to delete");
            return;
        }

        try {
            System.out.println("Deleting instrument: " + instruments.get(number - 1));
            instruments.remove(number - 1);
            numberOfInstruments--;
            System.out.println("Now you have " + numberOfInstruments + " instruments");
        } catch (Exception e) {
            System.out.println("Error in deleting instrument: " + (number));
        }
    }

    public void reserveInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments available for reservation");
            return;
        }
        Instrument instToRent = instruments.get(number - 1);
        //        System.out.println("Would you like to reserve " + instToRent + "? [Y/N]");
        //        String userInput = ui.readUserInput();
        //
        //        if (userInput.equals("Y")) {
        System.out.println("Reserving instrument: " + instToRent);
        instToRent.rent();
        instToRent.incrementUsage();
        //        } else {
        //            System.out.println("Reserve cancelled");
        //        }
    }

    public void returnInstrument(int number) {
        assert number > 0 && number <= numberOfInstruments : "Instrument number out of bounds: " + number;
        if (this.instruments.isEmpty()) {
            System.out.println("No instruments to return");
            return;
        }
        Instrument instToUnrent = instruments.get(number - 1);
        //        System.out.println("Would you like to return " + instToUnrent + "? [Y/N]");
        //        String userInput = ui.readUserInput();
        //
        //        if (userInput.equals("Y")) {
        System.out.println("Returning instrument: " + instToUnrent);
        instToUnrent.unrent();
        //        } else {
        //            System.out.println("Return cancelled");
        //        }
    }

    public ArrayList<Instrument> getList() {
        return this.instruments;
    }

}
