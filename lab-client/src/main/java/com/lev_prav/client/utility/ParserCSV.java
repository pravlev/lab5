package com.lev_prav.client.utility;

import com.lev_prav.client.data.Person;
import com.lev_prav.client.exceptions.CSVException;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.HeaderColumnNameMappingStrategyBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.List;
import java.util.stream.Collectors;

public final class ParserCSV {
    private ParserCSV() {
    }

    public static void parseCollectionToCSV(String fileName, CollectionManager collectionManager) throws
            FileNotFoundException {
        try (Writer writer = new FileWriter(fileName)) {
            writer.write("timeCreate," + collectionManager.getTimeCreate() + '\n');
            HeaderColumnNameMappingStrategy<Person> strategy = new HeaderColumnNameMappingStrategyBuilder<Person>().build();
            strategy.setType(Person.class);
            StatefulBeanToCsv<Person> beanToCsv = new StatefulBeanToCsvBuilder<Person>(writer).withMappingStrategy(strategy).build();
            beanToCsv.write(collectionManager.getPersons().stream().collect(Collectors.toList()));
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            System.out.println("Couldn't save the data. Check if the file is available.");
        }
    }

    public static CollectionManager parseCSVToCollection(String fileName) throws CSVException, FileNotFoundException {
        List<Person> persons = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            if (!reader.ready()) {
                return new CollectionManager(new ArrayDeque<>(), ZonedDateTime.now());
            }
            String[] str = reader.readLine().split(",");
            if (str.length != 2) {
                new IOException();
            }
            ZonedDateTime timeCreate = ZonedDateTime.parse(str[1]);
            if (!reader.ready()) {
                return new CollectionManager(new ArrayDeque<>(), timeCreate);
            }
            persons = new CsvToBeanBuilder<Person>(reader)
                    .withType(Person.class)
                    .build()
                    .parse();
            return new CollectionManager(new ArrayDeque<Person>(persons), timeCreate);
        } catch (IOException e) {
            System.out.println("Couldn't read the data. Check if the file is available.");
        }
        return new CollectionManager(new ArrayDeque<>(), ZonedDateTime.now());
    }
}
