package com.lev_prav.client;

import com.lev_prav.client.exceptions.CSVException;
import com.lev_prav.client.userio.UserIO;
import com.lev_prav.client.utility.CollectionManager;
import com.lev_prav.client.utility.CommandManager;
import com.lev_prav.client.utility.ConsoleManager;
import com.lev_prav.client.utility.ParserCSV;
import com.lev_prav.client.utility.PersonFiller;
import com.lev_prav.client.utility.PersonReader;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public final class Client {
    private Client() {
        throw new UnsupportedOperationException("This is an utility class and can not be instantiated");
    }

    public static void main(String[] args) {
        if (args.length >= 1) {
            try {
                CollectionManager collectionManager = ParserCSV.parseCSVToCollection(args[0]);
                UserIO userIO = new UserIO(new Scanner(System.in), new PrintWriter(System.out));
                PersonReader personReader = new PersonReader(userIO, collectionManager);
                PersonFiller personFiller = new PersonFiller(personReader, userIO, collectionManager);
                CommandManager commandManager = new CommandManager(collectionManager, userIO, personFiller, args[0]);
                ConsoleManager consoleManager = new ConsoleManager(commandManager, userIO);
                consoleManager.start();
            } catch (CSVException e) {
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            }
        } else {
            System.out.println("enter filename in command line arguments");
        }
    }
}
