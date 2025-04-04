package commands.instrument;

import commands.Command;
import finance.FinanceManager;
import instrument.InstrumentList;
import instrument.Instrument;
import ui.Ui;
import user.UserUtils;
import exceptions.instrument.IncorrectReturnInstructionException;

import java.time.LocalDate;

public class ReturnCommand extends Command {
    public ReturnCommand(String command) {
        super(command);
    }

    @Override
    public void execute(InstrumentList instrumentList, Ui ui, UserUtils userUtils, FinanceManager financeManager) {
        try {
            int number = Integer.parseInt(this.name);
            instrumentList.returnInstrument(number);
            Instrument instrument = instrumentList.getInstrument(Integer.parseInt(this.name));
            if (instrument != null && instrument.isOverDue()) {
                financeManager.overduePayment(instrument, LocalDate.now());
            }
            ui.printInstrumentList(instrumentList.getList());
        } catch (Exception | AssertionError e) {
            throw new IncorrectReturnInstructionException(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
