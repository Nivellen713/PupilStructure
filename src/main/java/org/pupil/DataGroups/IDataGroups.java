package org.pupil.DataGroups;

import org.pupil.Structures.Person;

public interface IDataGroups {
    void addPerson(Person person);

    int parseKeyToIndex(int key);

    default int getFirstNullIndex(Person[] persons) {
        int i = persons.length - 1;
        int firstNullIndex = i;
        while (persons[i] == null && i > 0) {
            firstNullIndex = i;
            i--;
        }
        return firstNullIndex;
    }

    default Person[] increaseArray(Person[] arrayToIncrease) {
        int size = 1000;
        Person[] bufPersons = arrayToIncrease;
        arrayToIncrease = new Person[bufPersons.length + size];
        System.arraycopy(bufPersons, 0, arrayToIncrease, 0, bufPersons.length);
        return arrayToIncrease;
    }

}
