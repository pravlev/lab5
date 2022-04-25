package com.lev_prav.client.utility;

import com.lev_prav.client.data.Coordinates;
import com.lev_prav.client.data.Country;
import com.lev_prav.client.data.Location;
import com.lev_prav.client.data.Person;
import com.lev_prav.client.exceptions.CSVException;
import com.lev_prav.client.userio.UserIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Scanner;

public final class ParserCSV {
    private static final String HEADING = "id;name;coordinateX;coordinateY;creationDate;height;birthday;passwordId;nationality;locationX;locationY;locationZ;locationName";
    private static final int LENGTH = 13;

    private ParserCSV() {
    }

    public static StringBuilder personToStringCSV(Person person) {
        StringBuilder st = new StringBuilder();
        st.append(person.getId()).append(';');
        st.append(person.getName()).append(';');
        st.append(person.getCoordinates().getX()).append(';');
        st.append(person.getCoordinates().getY()).append(';');
        st.append(person.getCreationDate()).append(';');
        st.append(person.getHeight()).append(';');
        st.append(person.getBirthday()).append(';');
        if (person.getPassportID() == null) {
            st.append("null;");
        } else {
            st.append(person.getPassportID()).append(';');
        }
        if (person.getNationality() == null) {
            st.append("null;");
        } else {
            st.append(person.getNationality()).append(';');
        }
        st.append(person.getLocation().getX()).append(';');
        st.append(person.getLocation().getY()).append(';');
        st.append(person.getLocation().getZ()).append(';');
        st.append(person.getLocation().getName());
        return st;
    }

    public static void parseCollectionToCSV(String fileName, CollectionManager collectionManager) throws FileNotFoundException {
        File file = new File(fileName);
        UserIO userIO = new UserIO(new Scanner(System.in), new PrintWriter(file));
        userIO.writeln("timeCreate;" + collectionManager.getTimeCreate());
        userIO.writeln(HEADING);
        for (Person i : collectionManager.getPersons()) {
            userIO.writeln(personToStringCSV(i));
        }
    }

    public static CollectionManager parseCSVToCollection(String fileName) throws CSVException, FileNotFoundException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        if (file.exists() && file.length() > 0) {
            String[] str = scanner.nextLine().split(";");
            if (str.length != 2 || !str[0].equals("timeCreate") || !scanner.nextLine().equals(HEADING)) {
                throw new CSVException();
            }
            ZonedDateTime timeCreate = ZonedDateTime.parse(str[1]);
            ArrayDeque<Person> persons = new ArrayDeque<>();
            while (scanner.hasNextLine()) {
                String[] st = scanner.nextLine().split(";");
                if (st.length != LENGTH) {
                    throw new CSVException();
                }
                int i = 0;
                Person person = new Person();
                person.setId(Integer.parseInt(st[i++]));
                person.setName(st[i++]);
                person.setCoordinates(new Coordinates(Long.parseLong(st[i++]), Float.parseFloat(st[i++])));
                person.setCreationDate(ZonedDateTime.parse(st[i++]));
                person.setHeight(Double.parseDouble(st[i++]));
                person.setBirthday(LocalDateTime.parse(st[i++]));
                if (!st[i].equals("null")) {
                    person.setPassportID(st[i]);
                }
                if (!st[++i].equals("null")) {
                    person.setNationality(Country.valueOf(st[i]));
                }
                person.setLocation(new Location(Double.parseDouble(st[++i]), Integer.parseInt(st[++i]), Float.parseFloat(st[++i]), st[++i]));

                persons.add(person);
            }
            return new CollectionManager(persons, timeCreate);
        } else {
            return new CollectionManager(new ArrayDeque<Person>(), ZonedDateTime.now());
        }
    }
}
