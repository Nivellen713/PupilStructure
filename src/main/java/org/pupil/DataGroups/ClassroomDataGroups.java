package org.pupil.DataGroups;

import org.pupil.Structures.Person;

// Класс-наследник для хранения данных по учебным группам
public class ClassroomDataGroups implements IDataGroups {
    private final int SIZE = 1000;
    private final int GROUPS_AMOUNT = 12;     // количество классов (от 1 до 12 включительно)
    Person[][] persons = new Person[GROUPS_AMOUNT][SIZE];

    @Override
    public void addPerson(Person person) {
        int groupIndex = parseKeyToIndex(person.getGroup());
        int firstNullIndex = getFirstNullIndex(persons[groupIndex]);
        if (firstNullIndex == persons[groupIndex].length) {
            persons[groupIndex] = increaseArray(persons[groupIndex]);
        }
        persons[groupIndex][firstNullIndex] = person;
    }

    //     вычисление средней оценки группы учеников
    public double getAverageRating(int groupNum) {
        double ratingSum = 0;
        double ratingAmount = 0;
        for (Person person : persons[parseKeyToIndex(groupNum)]) {
            if (person != null) {
                String[] rating = person.getRating();
                for (String markStr : rating) {
                    double markInt = Double.parseDouble(markStr);
                    ratingSum += markInt;
                    ratingAmount++;
                }
            }
        }
        if (ratingAmount == 0) {
            ratingAmount++;
        }
        return ratingSum / ratingAmount;
    }

    public Person getPerson(String lastname, String name, int group) {
        Person person = null;
        for (int i = 0; i < persons[group].length; i++) {
            if (persons[group][i].getLastName().equals(lastname)
                    && persons[group][i].getName().equals(name)) {
                person = persons[group][i];
            }
        }
        return person;
    }

    @Override
    public int parseKeyToIndex(int key) {
        return key - 1;
    }
}

