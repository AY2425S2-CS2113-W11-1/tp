package parser;

import exceptions.incorrectAddInstrumentException;

public class commandParser {
    public static String[] separateNMY(String input) throws incorrectAddInstrumentException {
        if (input == null || input.isEmpty()) {
            throw new incorrectAddInstrumentException("Input is Empty");
        }
        String[] split = input.split("\\|");
        if (split.length != 4) {
            throw new incorrectAddInstrumentException("Input instrument is invalid");
        }
        try {
            Integer.parseInt(split[2]);
            Integer.parseInt(split[3]);
            return split;
        } catch (NumberFormatException e) {
            throw new incorrectAddInstrumentException("Input year is invalid");
        }
    }
}
