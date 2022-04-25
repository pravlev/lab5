package com.lev_prav.client.utility;

import com.lev_prav.client.data.Country;
import com.lev_prav.client.exceptions.IllegalValueException;
import com.lev_prav.client.userio.UserIO;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class PersonReader {
    private static final long MAXX = 177L;
    private static final float MINY = -635;
    private final UserIO userIO;
    private final CollectionManager collectionManager;

    public PersonReader(UserIO userIO, CollectionManager collectionManager) {
        this.userIO = userIO;
        this.collectionManager = collectionManager;
    }

    public String readNotNullString() throws IllegalValueException {
        String string;
        string = userIO.readline();
        if (string.isEmpty()) {
            throw new IllegalValueException("This field cannot be null");
        }
        return string;
    }

    public String readNotEmptyString() throws IllegalValueException {
        String string;
        string = readNotNullString();
        if (string.trim().isEmpty()) {
            throw new IllegalValueException("This field cannot be empty");
        }
        return string;
    }

    public Long readCoordinateX() throws IllegalValueException, NumberFormatException {
        long x = Long.parseLong(userIO.readline());
        if (x > MAXX) {
            throw new IllegalValueException("Value must be less than " + MAXX);
        }
        return x;
    }

    public Float readCoordinateY() throws IllegalValueException, NumberFormatException {
        float y = Float.parseFloat(userIO.readline());
        if (y <= MINY) {
            throw new IllegalValueException("Value must be greater than " + MINY);
        }
        return y;
    }

    public double readHeight() throws IllegalValueException, NumberFormatException {
        double height = Double.parseDouble(userIO.readline());
        double minHeight = 0;
        if (height <= minHeight) {
            throw new IllegalValueException("Value must be greater than " + minHeight);
        }
        return height;
    }

    public LocalDateTime readBirthday() throws IllegalValueException {
        LocalDateTime birthday;
        try {
            birthday = LocalDateTime.parse(userIO.readline());
        } catch (DateTimeParseException e) {
            throw new IllegalValueException("The date must be in the format: 2007-12-03T10:15:30");
        }
        return birthday;
    }

    public String readPassportId() throws IllegalValueException {
        String read = userIO.readline();
        if (read.isEmpty()) {
            return null;
        }
        if (read.trim().isEmpty()) {
            throw new IllegalValueException("The field can be either null or not empty");
        }
        if (!collectionManager.isUniquePasswordId(read)) {
            throw new IllegalValueException("This field must be unique");
        }
        return read;
    }

    public Country readCountry() throws IllegalArgumentException {
        Country country = null;
        String stringCountry = userIO.readline();
        if (!stringCountry.isEmpty()) {
            country = Country.valueOf(stringCountry.toUpperCase(Locale.ROOT));
        }
        return country;
    }

    public Double readLocationX() throws NumberFormatException {
        return Double.parseDouble(userIO.readline());
    }

    public Integer readLocationY() throws NumberFormatException {
        return Integer.parseInt(userIO.readline());
    }

    public Float readLocationZ() throws NumberFormatException {
        return Float.parseFloat(userIO.readline());
    }

}