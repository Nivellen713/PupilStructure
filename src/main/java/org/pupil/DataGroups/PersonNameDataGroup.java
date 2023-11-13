package org.pupil.DataGroups;

import org.pupil.DataBase.StatisticDataBD;
import org.pupil.Structures.Person;

// Класс-наследник для хранения данных по имени
public class PersonNameDataGroup implements IDataGroups {
    private final int SIZE = 1000;
    private final int LETTERS_AMOUNT = 33; // количество букв (от А до Я)
    Person[][] persons = new Person[LETTERS_AMOUNT][SIZE];

    @Override
    public void addPerson(Person person) {
        int lastNameIndex = parseKeyToIndex(person.getLastName().charAt(0));
        int firstNullIndex = getFirstNullIndex(persons[lastNameIndex]);
        if (firstNullIndex == persons[lastNameIndex].length) {
            persons[lastNameIndex] = increaseArray(persons[lastNameIndex]);
        }
        persons[lastNameIndex][firstNullIndex] = person;
    }

    @Override
    public int parseKeyToIndex(int key) {
        return key - 1040; // (int) 'А' = 1040
    }

    @Override
    public Person[][] getPersons() {
        return new Person[0][];
    }

    public Person[] getByLastName(String lastName) {
        int firstLetterIndex = parseKeyToIndex(lastName.charAt(0));
        Person[] lastNamePersons = new Person[SIZE];
        int lastNullIndex = 0;
        for (int i = 0; i < persons[firstLetterIndex].length; i++) {
            if (persons[firstLetterIndex][i] != null && persons[firstLetterIndex][i].getLastName().equals(lastName)) {
                lastNamePersons[lastNullIndex] = persons[firstLetterIndex][i];
                lastNullIndex++;
            }
        }
        return cutNullElements(lastNamePersons);
    }

    public Person[][] getAverageRatingByLastNameFromDB(String lastname){
        StatisticDataBD statisticDataBD = new StatisticDataBD();
        return statisticDataBD.getAverageRatingByLastName(lastname).getPersons();
    }

    private Person[] cutNullElements(Person[] people) {
        int firstNullIndex = getFirstNullIndex(people);
        Person[] peopleWithoutNulls = new Person[firstNullIndex];
        System.arraycopy(people, 0, peopleWithoutNulls, 0, peopleWithoutNulls.length);
        return peopleWithoutNulls;
    }
}