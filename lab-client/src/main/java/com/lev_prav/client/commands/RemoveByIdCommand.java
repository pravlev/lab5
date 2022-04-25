package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.utility.CollectionManager;

public class RemoveByIdCommand extends Command {
    private final CollectionManager collectionManager;

    public RemoveByIdCommand(CollectionManager collectionManager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        if (!collectionManager.isId(Integer.parseInt(argument))) {
            throw new IllegalValueException("Элемента с данным id  нет в коллекции");
        }
        collectionManager.removeById(Integer.parseInt(argument));
    }
}
