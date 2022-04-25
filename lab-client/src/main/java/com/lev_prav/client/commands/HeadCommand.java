package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;

public class HeadCommand extends Command {

    private CollectionManager collectionManager;
    private UserIO userIO;

    public HeadCommand(CollectionManager collectionManager, UserIO userIO) {
        super("head", "вывести первый элемент коллекции");
        this.collectionManager = collectionManager;
        this.userIO = userIO;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        userIO.writeln(collectionManager.getPersons().getFirst());
    }
}
