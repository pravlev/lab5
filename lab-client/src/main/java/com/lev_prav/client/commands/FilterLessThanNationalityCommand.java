package com.lev_prav.client.commands;

import com.lev_prav.client.data.Country;
import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;

import java.util.Locale;

public class FilterLessThanNationalityCommand extends Command {
    private final CollectionManager collectionManager;
    private final UserIO userIO;

    public FilterLessThanNationalityCommand(CollectionManager collectionManager, UserIO userIO) {
        super("filter_less_than_nationality", "вывести элементы, значение поля nationality которых меньше заданного");
        this.collectionManager = collectionManager;
        this.userIO = userIO;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException {
        if (argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        Country country = null;
        try {
            country = Country.valueOf(argument.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new IllegalValueException("Chose anything from list: UNITED_KINGDOM,GERMANY,SPAIN,INDIA,THAILAND");
        }

        collectionManager.filerLessThanNationality(country).forEach(userIO::writeln);
    }
}
