package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.utility.CollectionManager;
import com.lev_prav.client.utility.PersonFiller;

/**
 * Этот класс реализует команду add
 */
public class CommandAdd extends Command {
    private final CollectionManager collectionManager;
    private final PersonFiller personFiller;

    public CommandAdd(CollectionManager collectionManager, PersonFiller personFiller) {
        super("add", "добавить новый элемент в коллекцию(элемент нужно вводить после запуска команды)");
        this.collectionManager = collectionManager;
        this.personFiller = personFiller;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        collectionManager.addNewPerson(personFiller.fillPerson());
    }
}
