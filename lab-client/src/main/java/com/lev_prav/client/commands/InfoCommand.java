package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;

public class InfoCommand extends Command {
    private final UserIO userIO;
    private final CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager, UserIO userIO) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции");
        this.userIO = userIO;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        userIO.writeln("Информация о коллекции: ");
        userIO.writeln("тип: " + collectionManager.getPersons().getClass().getName());
        userIO.writeln("дата создания: " + collectionManager.getTimeCreate());
    }
}
