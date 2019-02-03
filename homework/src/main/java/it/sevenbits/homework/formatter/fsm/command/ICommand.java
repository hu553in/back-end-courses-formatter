package it.sevenbits.homework.formatter.fsm.command;

public interface ICommand {
    void execute() throws CommandException;
}
