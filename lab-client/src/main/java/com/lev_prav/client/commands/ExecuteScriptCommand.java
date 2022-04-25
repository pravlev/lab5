package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;

public class ExecuteScriptCommand extends Command {
    private UserIO userIO;

    public ExecuteScriptCommand(UserIO userIO) {
        super("execute_script", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме");
        this.userIO = userIO;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        userIO.startReadScript(argument);
    }
}
