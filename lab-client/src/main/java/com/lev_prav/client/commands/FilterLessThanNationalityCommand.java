package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;
import com.lev_prav.client.utility.PersonFiller;

public class FilterLessThanNationalityCommand extends Command {
    private final CollectionManager collectionManager;
    private final PersonFiller personFiller;
    private final UserIO userIO;

    public FilterLessThanNationalityCommand(CollectionManager collectionManager, UserIO userIO, PersonFiller personFiller) {
        super("filter_less_than_nationality", "вывести элементы, значение поля nationality которых меньше заданного");
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.personFiller = personFiller;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        collectionManager.filerLessThanNationality(personFiller.fillNationality()).forEach(userIO::writeln);
    }
}
