package org.pupil.DataGroups;

import org.pupil.Structures.Person;

// Класс-наследник для хранения данных по имени
public class PersonNameDataGroup implements IDataGroups {
    private final int SIZE = 1000;
    private final int LETTERS_AMOUNT = 33; // количество букв (от А до Я)
    Person[][] persons = new Person[LETTERS_AMOUNT][SIZE];

    @Override
    public void addPerson(Person person) {
        int ageIndex = parseKeyToIndex(person.getLastName().charAt(0));
        int firstNullIndex = getFirstNullIndex(persons[ageIndex]);
        if (firstNullIndex == persons[ageIndex].length) {
            persons[ageIndex] = increaseArray(persons[ageIndex]);
        }
        persons[ageIndex][firstNullIndex] = person;
    }

    @Override
    public int parseKeyToIndex(int key) {
        return key - 1040; // (int) 'А' = 1040
    }

    @Override
    public Person getPerson(String lastname, String name, int group) {
        Person person = null;
        char firstLetterCode = name.charAt(0);
        for (int i = 0; i < persons[firstLetterCode].length; i++) {
            if (persons[firstLetterCode][i].getLastName().equals(lastname)
                    && persons[firstLetterCode][i].getName().equals(name)) {
                person = persons[firstLetterCode][i];
            }
        }
        return person;
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

    private Person[] cutNullElements(Person[] people) {
        int firstNullIndex = getFirstNullIndex(people);
        Person[] peopleWithoutNulls = new Person[firstNullIndex];
        System.arraycopy(people, 0, peopleWithoutNulls, 0, peopleWithoutNulls.length);
        return peopleWithoutNulls;
    }
}