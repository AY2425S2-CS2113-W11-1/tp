package commands;

import instrument.InstrumentList;
import parser.CommandParser;
import ui.Ui;

public class ExtendCommand extends Command {
    private CommandParser parser;

    public ExtendCommand(String command) {
        super(command);
        parser = new CommandParser();
    }

    // TODO add features to prevent invalid date/overdue from the start
    @Override
    public void execute(InstrumentList instrumentList, Ui ui) {
        try {
            String[] userInput = parser.splits(this.name);
            int indice = Integer.parseInt(userInput[0]);
            try {
                String[] parts = this.name.split("to: ", 3);
                String to = parts[1];
                instrumentList.extendInstrumentTo(indice, to);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


            ui.printInstrumentList(instrumentList.getList());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
