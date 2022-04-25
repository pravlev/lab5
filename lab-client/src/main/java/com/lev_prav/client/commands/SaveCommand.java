package com.lev_prav.client.commands;

import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.exceptions.NoSuchCommandException;
import com.lev_prav.client.exceptions.ScriptException;
import com.lev_prav.client.utility.CollectionManager;
import com.lev_prav.client.utility.ParserCSV;

import java.io.FileNotFoundException;

public class SaveCommand extends Command {
    private String fileName;
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager, String fileName) {
        super("save", "сохранить коллекцию в файл");
        this.fileName = fileName;
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String argument) throws NoSuchCommandException, ScriptException, IllegalValueException, FileNotFoundException {
        if (!argument.isEmpty()) {
            throw new NoSuchCommandException();
        }
        ParserCSV.parseCollectionToCSV(fileName, collectionManager);
    }
}
