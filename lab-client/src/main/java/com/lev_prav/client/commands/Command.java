package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;

import java.io.FileNotFoundException;

/**
 * Этот класс родитель всех команд
 */

public abstract class Command {
    private final String name;
    private final String description;
    private boolean executeFlag = true;
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Выполняет команду
     * @param argument
     * @throws NoSuchCommandException
     * @throws ScriptException
     * @throws IllegalValueException
     * @throws FileNotFoundException
     */
    public abstract void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException, FileNotFoundException;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getExecuteFlag() {
        return executeFlag;
    }

    /**
     * @param executeFlag false if it is an exit command and true if it is not
     */
    public void setExecuteFlag(boolean executeFlag) {
        this.executeFlag = executeFlag;
    }
}
