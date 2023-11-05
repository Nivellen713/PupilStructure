package org.pupil.Structures;

import org.pupil.DataProcessing.Parser;

import java.util.Arrays;

// Определяем как будет выглядеть каждый ученик по его основным параметрам
public class Person {

    String lastName;
    String name;
    int age;
    int group;
    String[] rating;

    public Person(String[] csvLine) {
        Parser parser = new Parser();
        this.lastName = parser.getLastNameFromCSVLine(csvLine);
        this.name = parser.getNameFromCSVLine(csvLine);
        this.age = parser.getAgeFromCSVLine(csvLine);
        this.group = parser.getGroupFromCSVLine(csvLine);
        this.rating = parser.getRatingFromCSVLine(csvLine);
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getGroup() {
        return group;
    }

    public String[] getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", name=" + name + '\'' +
                ", age=" + age +
                ", group=" + group +
                ", rating=" + Arrays.toString(rating) +
                '}';
    }
}
