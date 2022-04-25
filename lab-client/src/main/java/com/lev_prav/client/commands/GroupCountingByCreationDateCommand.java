package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;

public class GroupCountingByCreationDateCommand extends Command {

    private CollectionManager collectionManager;
    private UserIO userIO;

    public GroupCountingByCreationDateCommand(CollectionManager collectionManager, UserIO userIO) {
        super("group_counting_by_creation_date", "сгруппировать элементы коллекции по значению поля creationDate, вывести количество элементов в каждой группе");
        this.collectionManager = collectionManager;
        this.userIO = userIO;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        collectionManager.groupCountingByCreationDate().forEach((k, v) -> userIO.writeln(k + ": " + v));
    }
}
