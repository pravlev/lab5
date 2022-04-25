package com.lev_prav.client.utility;

import com.lev_prav.client.data.Country;
import com.lev_prav.client.data.Person;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CollectionManager {
    private ArrayDeque<Person> persons;
    private ZonedDateTime timeCreate;

    public CollectionManager(ArrayDeque<Person> persons, ZonedDateTime timeCreate) {
        this.timeCreate = timeCreate;
        this.persons = persons;
    }

    public int getSize() {
        return persons.size();
    }

    public ArrayDeque<Person> getPersons() {
        return persons;
    }

    public void setPersons(ArrayDeque<Person> persons) {
        this.persons = persons;
    }

    public ZonedDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(ZonedDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    public void addNewPerson(Person person) {
        person.setId(getMaxId() + 1);
        person.setCreationDate(ZonedDateTime.now());
        persons.add(person);
    }

    public void updatePerson(int id, Person person) {
        ArrayList<Person> pr = new ArrayList<>(persons);
        for (int i = 0; i < pr.size(); ++i) {
            if (pr.get(i).getId() == id) {
                pr.set(i, person);
                break;
            }
        }
        person.setId(id);
        persons = new ArrayDeque<>(pr);
    }

    public void removeById(int id) {
        ArrayList<Person> pr = new ArrayList<Person>(persons);
        for (int i = 0; i < pr.size(); ++i) {
            if (pr.get(i).getId() == id) {
                pr.remove(i);
                break;
            }
        }
        persons = new ArrayDeque<>(pr);
    }

    public void removeLower(Person pr) {
        ArrayDeque<Person> prs = new ArrayDeque<>();
        for (Person i : persons) {
            if (i.compareTo(pr) >= 0) {
                prs.add(i);
            }
        }
        persons = prs;
    }

    public void clear() {
        persons.clear();
    }

    public int getMaxId() {
        if (getSize() > 0) {
            return persons.stream().max(Comparator.comparing(Person::getId)).get().getId();
        }
        return 0;
    }

    public boolean isId(int id) {
        for (Person i : persons) {
            if (i.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean isUniquePasswordId(String passwordId) {
        for (Person i : persons) {
            if (i.getPassportID().equals(passwordId)) {
                return false;
            }
        }
        return true;
    }

    public double averageOfHeight() {
        return persons.stream().mapToDouble(Person::getHeight).average().orElse(0);
    }

    public Map<ZonedDateTime, Long> groupCountingByCreationDate() {
        return persons.stream().collect(Collectors.groupingBy(Person::getCreationDate, Collectors.counting()));
    }

    public List<Person> filerLessThanNationality(Country country) {
        return persons.stream().filter(e -> e.getNationality().compareTo(country) < 0).collect(Collectors.toList());
    }
}
