package com.lev_prav.client.data;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double height; //Значение поля должно быть больше 0
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private String passportID; //Строка не может быть пустой, Значение этого поля должно быть уникальным, Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person(String name, Coordinates coordinates, double height, LocalDateTime birthday, String passportID, Country nationality,
                  Location location) {
        this.name = name;
        this.coordinates = coordinates;
        this.height = height;
        this.birthday = birthday;
        this.passportID = passportID;
        this.nationality = nationality;
        this.location = location;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getPassportID() {
        return passportID;
    }

    public void setPassportID(String passportID) {
        this.passportID = passportID;
    }

    public Country getNationality() {
        return nationality;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return id == person.id && Double.compare(person.height, height) == 0 && name.equals(person.name) && coordinates.equals(person.coordinates) && creationDate.equals(person.creationDate) && birthday.equals(person.birthday) && passportID.equals(person.passportID) && nationality == person.nationality && location.equals(person.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, height, birthday, passportID, nationality, location);
    }

    @Override
    public String toString() {
        return "Person{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", coordinates=" + coordinates
                + ", creationDate=" + creationDate
                + ", height=" + height
                + ", birthday=" + birthday
                + ", passportID='" + passportID + '\''
                + ", nationality=" + nationality
                + ", location=" + location
                + '}';
    }

    @Override
    public int compareTo(Person o) {
        return Double.compare(height, o.height);
    }
}