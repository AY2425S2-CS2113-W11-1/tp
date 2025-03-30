package ui;

import exceptions.IncorrectAddInstrumentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import parser.commandParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private commandParser cmdparser;

    @BeforeEach
    public void setUp() {
        cmdparser = new commandParser();
    }

    @Test
    void parse_validInput_parsedCorrectly() {
        String validInput = "Guitar|PAC112VM|2000";
        String[] intendedOutput = {"Guitar", "PAC112VM", "2000"};
        try {
            String[] output = cmdparser.separate(validInput);
            assertArrayEquals(output, intendedOutput);
        } catch (IncorrectAddInstrumentException e) {
            throw new RuntimeException("Failed to parse input", e);
        }
    }

    @Test
    void parse_invalidInstrument_errorMessage() {
        String invalidInput = "Guitar|1999";

        try {
            cmdparser.separate(invalidInput);
            fail("Expected incorrectAddInstrumentException to be thrown");
        } catch (IncorrectAddInstrumentException e) {
            assertEquals("Input doesn't look right: " +
                    "Input format is invalid: missing fields-> add [Instrument]|[Model]|[Year]", e.getMessage());
        }
    }

    @Test
    void parse_invalidYear_errorMessage() {
        String invalidInput = "Guitar|PAC112VM|hehe";

        try {
            cmdparser.separate(invalidInput);
            fail("Expected IOException to be thrown");
        } catch (IncorrectAddInstrumentException e) {
            assertEquals("Input doesn't look right: Input year is invalid-> " +
                    "add [Instrument]|[Model]|[Year]", e.getMessage());
        }
    }
}
