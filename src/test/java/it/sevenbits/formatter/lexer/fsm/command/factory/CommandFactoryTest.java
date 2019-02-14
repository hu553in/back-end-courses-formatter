package it.sevenbits.formatter.lexer.fsm.command.factory;

import it.sevenbits.formatter.lexer.fsm.command.args.CommandArgs;
import org.junit.Before;
import org.junit.Test;

public class CommandFactoryTest {
    private ICommandFactory commandFactory;

    @Before
    public void setUp() {
        commandFactory = new CommandFactory(new CommandArgs());
    }

    @Test (expected = CommandFactoryException.class)
    public void shouldThrowException() throws CommandFactoryException {
        commandFactory.getCommand(null, 'c');
    }
}
