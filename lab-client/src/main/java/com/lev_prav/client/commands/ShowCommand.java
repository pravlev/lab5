package com.lev_prav.client.commands;

import com.lev_prav.client.data.Person;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;

import java.util.ArrayDeque;

public class ShowCommand extends Command {
    private final UserIO userIO;
    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager, UserIO userIO) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.userIO = userIO;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        ArrayDeque<Person> persons = collectionManager.getPersons();
        if (persons.size() != 0) {
            userIO.writeln("Все элементы коллекции: ");
            for (Person p : persons) {
                userIO.writeln(p);
            }
        } else {
            userIO.writeln("collection is empty");
        }
    }
}
