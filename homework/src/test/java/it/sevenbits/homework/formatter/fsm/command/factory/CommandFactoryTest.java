package it.sevenbits.homework.formatter.fsm.command.factory;

import it.sevenbits.homework.formatter.fsm.command.args.CommandArgs;
import it.sevenbits.homework.formatter.fsm.command.ICommand;
import it.sevenbits.homework.formatter.fsm.command.StayIdleCommand;
import it.sevenbits.homework.fsm.state.State;
import it.sevenbits.homework.lexer.token.Token;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommandFactoryTest {
    private ICommandFactory commandFactory;

    @Before
    public void setUp() {
        commandFactory = new CommandFactory(new CommandArgs());
    }

    @Test (expected = CommandFactoryException.class)
    public void shouldThrowExceptionAtFirst() throws CommandFactoryException {
        commandFactory.getCommand(null, null);
    }

    @Test
    public void shouldReturnStayIdleCommand() throws CommandFactoryException {
        final ICommand command = commandFactory.getCommand(
                new State("UNKNOWN_STATE"),
                new Token("UNKNOWN_TOKEN", "")
        );

        Assert.assertEquals(
                command.getClass(),
                StayIdleCommand.class
        );
    }
}
