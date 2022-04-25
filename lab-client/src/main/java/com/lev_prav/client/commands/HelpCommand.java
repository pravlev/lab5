package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.userio.UserIO;
import java.util.HashMap;

public class HelpCommand extends Command {
    private HashMap<String, Command> commands;
    private UserIO userIO;

    public HelpCommand(UserIO userIO, HashMap<String, Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.userIO = userIO;
        this.commands = commands;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        userIO.writeln("Список доступных команд:");
        commands.forEach((k, v) -> userIO.writeln(k + ":" + v.getDescription()));
    }
}
