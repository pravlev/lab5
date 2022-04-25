package com.lev_prav.client.commands;

import com.lev_prav.client.data.Person;
import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.utility.CollectionManager;
import com.lev_prav.client.utility.PersonFiller;

public class RemoveLowerCommand extends Command {
    private CollectionManager collectionManager;
    private PersonFiller personFiller;

    public RemoveLowerCommand(CollectionManager collectionManager, PersonFiller personFiller) {
        super("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        this.collectionManager = collectionManager;
        this.personFiller = personFiller;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        Person person = personFiller.fillPerson();
        collectionManager.removeLower(person);
    }
}
