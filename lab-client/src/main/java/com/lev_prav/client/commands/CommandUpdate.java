package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.utility.CollectionManager;
import com.lev_prav.client.utility.PersonFiller;
/**
 * Этот класс реализует команду update
 */
public class CommandUpdate extends Command {
    private final CollectionManager collectionManager;
    private final PersonFiller personFiller;

    public CommandUpdate(CollectionManager collectionManager, PersonFiller personFiller) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному(элемент нужно вводить после запуска команды)");
        this.collectionManager = collectionManager;
        this.personFiller = personFiller;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        if (!collectionManager.isId(Integer.parseInt(argument))) {
            throw new IllegalValueException("Элемента с данным id  нет в коллекции");
        }

        collectionManager.updatePerson(Integer.parseInt(argument), personFiller.fillPerson());
    }
}
