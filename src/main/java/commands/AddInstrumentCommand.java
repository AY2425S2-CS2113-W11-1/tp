package commands;

import exceptions.EmptyDescriptionException;
import exceptions.IncorrectAddInstrumentException;
import exceptions.NegativeUsageException;
import instrument.Instrument;
import instrument.InstrumentList;
import instrument.Flute;
import instrument.Guitar;
import instrument.Piano;
import parser.CommandParser;

import ui.Ui;

public class AddInstrumentCommand extends Command {
    private CommandParser cmdparser;

    public AddInstrumentCommand(String command) {
        super(command);
        cmdparser = new CommandParser();
    }

    public void parse(InstrumentList instrumentList, Ui ui) throws IncorrectAddInstrumentException {
        String[] userInput = cmdparser.separate(this.name.trim());

        String instrument = cmdparser.instrumentName(userInput);
        String model = cmdparser.modelName(userInput);
        int year = cmdparser.instrumentYear(userInput);
        boolean isRented =  cmdparser.isRented(userInput);
        boolean isOverdue = cmdparser.isOverdue(userInput);
        String rentedFrom = cmdparser.rentedFrom(userInput);
        String rentedTo = cmdparser.rentedTo(userInput);

        int usage = 0;
        try{
            usage = cmdparser.usage(userInput);
        } catch(IncorrectAddInstrumentException e){
            System.out.println(e.getMessage());
        }

        Instrument newInstrument = null;

        // TODO: abstract this into hashmap
        try {
            switch (instrument) {
            case "Flute":
                newInstrument = instrumentList.addInstrument(new Flute(instrument, model, year,
                        isRented, isOverdue, rentedFrom, rentedTo));
                break;
            case "Piano":
                newInstrument = instrumentList.addInstrument(new Piano(instrument, model, year,
                        isRented, isOverdue, rentedFrom, rentedTo));
                break;
            case "Guitar":
                newInstrument = instrumentList.addInstrument(new Guitar(instrument, model, year,
                        isRented, isOverdue, rentedFrom, rentedTo));
                break;
            default:
                System.out.println("invalid instrument");
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
            ui.printInstrumentList(instrumentList.getList());
            return;
        }

        try{
            if (newInstrument != null) {
                newInstrument.setUsage(usage);
            }
        } catch (NegativeUsageException e){
            System.out.println(e.getMessage());
        }

        ui.printInstrumentList(instrumentList.getList());

    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui) throws IncorrectAddInstrumentException {
        String[] userInput = cmdparser.separate(this.name.trim());

        String instrument = cmdparser.instrumentName(userInput);
        String model = cmdparser.modelName(userInput);
        int year = cmdparser.instrumentYear(userInput);
        boolean isRented =  cmdparser.isRented(userInput);

        // TODO: abstract this into hashmap
        try {
            switch (instrument) {
            case "Flute":
                instrumentList.addInstrument(new Flute(instrument, model, year));
                break;
            case "Piano":
                instrumentList.addInstrument(new Piano(instrument, model, year));
                break;
            case "Guitar":
                instrumentList.addInstrument(new Guitar(instrument, model, year));
                break;
            default:
                System.out.println("invalid instrument");
            }
        } catch (EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
        ui.printInstrumentList(instrumentList.getList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
