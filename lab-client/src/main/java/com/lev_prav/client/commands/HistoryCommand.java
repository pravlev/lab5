package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;

import java.util.ArrayDeque;
import java.util.Queue;

public class HistoryCommand extends Command {
    private final int numberOfElements = 10;
    private final UserIO userIO;
    private final Queue<Command> history = new ArrayDeque<>();

    public HistoryCommand(UserIO userIO) {
        super("history", "вывести последние 10 команд (без их аргументов)");
        this.userIO = userIO;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        history.forEach(e -> userIO.writeln(e.getName()));
    }

    public void add(Command command) {
        if (history.size() == numberOfElements) {
            history.poll();
        }
        history.add(command);
    }
}
