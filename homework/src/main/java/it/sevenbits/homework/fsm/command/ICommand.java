package it.sevenbits.homework.fsm.command;

public interface ICommand {
    void execute() throws CommandException;
}
