package com.lev_prav.client.utility;

import com.lev_prav.client.commands.Command;
import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;

import java.io.FileNotFoundException;
import java.util.Locale;

public class ConsoleManager {
    private final CommandManager commandManager;
    private final UserIO userIO;

    public ConsoleManager(CommandManager commandManager, UserIO userIO) {
        this.commandManager = commandManager;
        this.userIO = userIO;
    }

    /**
     * starts read commands and execute it while it is not an exit command
     */
    public void start() throws FileNotFoundException {
        boolean executeFlag = true;
        while (executeFlag) {
            String input = userIO.readline();
            String inputCommand = input.split(" ")[0].toLowerCase(Locale.ROOT);
            String argument = "";
            boolean completed = false;
            if (input.split(" ").length > 1) {
                argument = input.replaceFirst(inputCommand + " ", "");
            }
            if (commandManager.getCommands().containsKey(inputCommand)) {
                Command command = commandManager.getCommands().get(inputCommand);
                try {
                    command.execute(argument);
                    executeFlag = command.getExecuteFlag();
                    commandManager.addToHistory(command);
                    userIO.writeln("completed");
                } catch (ScriptException e) {
                    userIO.finishReadScript();
                    userIO.writeln(e.getMessage());
                } catch (NoSuchCommandException | IllegalValueException e) {
                    if (userIO.isScriptMode()) {
                        userIO.finishReadScript();
                    }
                    userIO.writeln(e.getMessage());
                } catch (NumberFormatException e) {
                    if (userIO.isScriptMode()) {
                        userIO.finishReadScript();
                    }
                    userIO.writeln("type a number please");
                }
            } else {
                userIO.writeln("No such command. Type \"help\" to get all commands with their names and descriptions");
            }
        }
    }
}
