package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;

/**
 * Этот класс реализует команду average_of_height
 */
public class AverageOfHeightCommand extends Command {
    private CollectionManager collectionManager;
    private UserIO userIO;

    public AverageOfHeightCommand(CollectionManager collectionManager, UserIO userIO) {
        super("average_of_height", "вывести среднее значение поля height для всех элементов коллекции");
        this.collectionManager = collectionManager;

        this.userIO = userIO;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        userIO.writeln(collectionManager.averageOfHeight());
    }
}
