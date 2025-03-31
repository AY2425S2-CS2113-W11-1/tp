package exceptions;

public class IncorrectAddInstrumentException extends EmptyDescriptionException {
    public IncorrectAddInstrumentException(String message) {
        super(message + "-> add [Instrument]|[Model]|[Year]");
    }
}
