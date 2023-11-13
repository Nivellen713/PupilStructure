package org.pupil.DataGroups;

import org.pupil.DataBase.StatisticDataBD;
import org.pupil.Structures.Person;

// Класс-наследник для хранения данных по возрасту
public class PersonAgeDataGroups implements IDataGroups {
    private final int SIZE = 1000;
    private final int AGES_AMOUNT = 13;  // количество возрастов (от 5 до 17 включительно)
    Person[][] persons = new Person[AGES_AMOUNT][SIZE];

    @Override
    public void addPerson(Person person) {
        int ageIndex = parseKeyToIndex(person.getAge());
        int firstNullIndex = getFirstNullIndex(persons[ageIndex]);
        if (firstNullIndex == persons[ageIndex].length) {
            persons[ageIndex] = increaseArray(persons[ageIndex]);
        }
        persons[ageIndex][firstNullIndex] = person;
    }

    @Override
    public int parseKeyToIndex(int key) {
        return key - 5;
    }

    @Override
    public Person[][] getPersons() {
        return persons;
    }

    private Person[][] getPersonOlderThan(int age) {
        int ageIndex = parseKeyToIndex(age);
        int amountAgesGroups = AGES_AMOUNT - ageIndex;    // Количество возрастов от искомого до максимального
        Person[][] personsOlderThan = new Person[amountAgesGroups][SIZE];
        System.arraycopy(persons, ageIndex, personsOlderThan, 0, personsOlderThan.length);
        return personsOlderThan;
    }

    public Person[] getExcellentOlderThan(int age) {
        Person[][] olderAgePersons = getPersonOlderThan(age);
        Person[] excellentPersons = new Person[SIZE];
        int firstNullIndex = 0;
        for (Person[] olderAgePerson : olderAgePersons) {
            for (Person agePerson : olderAgePerson) {
                if (agePerson != null && isExcellentRating(agePerson.getRating())) {
                    excellentPersons[firstNullIndex] = agePerson;
                    firstNullIndex++;
                }
            }
        }
        return cutNullElements(excellentPersons);
    }

    public Person[][] getExcellentOlderThanFromDB(int age){
        StatisticDataBD statisticDataBD = new StatisticDataBD();
        return statisticDataBD.getExcellentOlderThan(age).getPersons();
    }

    private Person[] cutNullElements(Person[] people) {
        int firstNullIndex = getFirstNullIndex(people);
        Person[] peopleWithoutNulls = new Person[firstNullIndex];
        System.arraycopy(people, 0, peopleWithoutNulls, 0, peopleWithoutNulls.length);
        return peopleWithoutNulls;
    }

    private boolean isExcellentRating(String[] rating) {
        int count = 0;
        for (String str : rating) {
            if (Integer.parseInt(str) == 5) {
                count++;
            }
        }
        return count == rating.length;
    }
}
