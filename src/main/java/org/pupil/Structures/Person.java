package org.pupil.Structures;

import org.pupil.DataBase.Column;
import org.pupil.DataBase.Table;
import org.pupil.DataProcessing.Parser;

import java.util.Arrays;

// Определяем как будет выглядеть каждый ученик по его основным параметрам
@Table(title = "person")
public class Person {

    @Column
    String lastName;
    @Column
    String name;
    @Column
    int age;
    @Column
    int groupName;
    //@Column
    String[] rating;

    double averageRating;

    public Person(String[] csvLine) {
        Parser parser = new Parser();
        this.lastName = parser.getLastNameFromCSVLine(csvLine);
        this.name = parser.getNameFromCSVLine(csvLine);
        this.age = parser.getAgeFromCSVLine(csvLine);
        this.groupName = parser.getGroupFromCSVLine(csvLine);
        this.rating = parser.getRatingFromCSVLine(csvLine);
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getGroupName() {
        return groupName;
    }

    public String[] getRating() {
        return rating;
    }

    public String getName() {
        return name;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", name=" + name + '\'' +
                ", age=" + age +
                ", group=" + groupName +
                ", rating=" + Arrays.toString(rating) +
                '}';
    }
}
