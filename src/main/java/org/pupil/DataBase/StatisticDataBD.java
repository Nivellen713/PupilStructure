package org.pupil.DataBase;

import org.pupil.DataGroups.IDataGroups;
import org.pupil.DataGroups.PersonAgeDataGroups;
import org.pupil.Structures.Person;

import java.sql.*;
import java.util.LinkedList;

public class StatisticDataBD {

    private final ConnectorDB CONNECTOR_DB;

    public StatisticDataBD() {
        CONNECTOR_DB = new ConnectorDB();
    }

    public double getAverageRatingByGroup(int groupNum) {
        double average = 0.0;
        int count = 0;
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT (physics, mathematics, rus, literature, geometry, informatics)/6 as avg " +
                            "FROM rating " +
                            "JOIN person ON person.rating_id = rating.rating_id " +
                            "WHERE \"group\" = " + groupNum);

            while (resultSet.next()) {
                average += resultSet.getDouble("avg");
                count++;
            }
            resultSet.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return average / count;
    }

    public IDataGroups getExcellentOlderThan(int age) {
        IDataGroups dataGroups = new PersonAgeDataGroups();
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT person_id " +
                            "FROM person " +
                            "JOIN rating ON person.rating_id = rating.rating_id " +
                            "WHERE age>= " + age);
            // Используется, чтобы позже забрать все необходимые данные из базы для создания объекта Person
            LinkedList<Integer> excellentPersonIdOlderThan = new LinkedList<>();
            while (resultSet.next()) {
                excellentPersonIdOlderThan.add(resultSet.getInt("person_id"));
            }
            resultSet.close();
            ProcessorDB processorDB = new ProcessorDB();
            for (int id : excellentPersonIdOlderThan) {
                dataGroups.addPerson(new Person(processorDB.getPersonById(id)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return dataGroups;
    }

    public IDataGroups getAverageRatingByLastName(String lastname) {
        IDataGroups dataGroups = new PersonAgeDataGroups();
        LinkedList<Double> averageMarks = new LinkedList<>();
        LinkedList<Integer> personId = new LinkedList<>();
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT person_id FROM person " +
                            "UNION SELECT (physics, mathematics, rus, literature, geometry, informatics)/6 as avg " +
                            "FROM rating " +
                            "JOIN person ON person.rating_id = rating.rating_id " +
                            "WHERE family= " + lastname);
            while (resultSet.next()) {
                averageMarks.add(resultSet.getDouble("avg"));
                personId.add(resultSet.getInt("person_id"));
            }
            resultSet.close();
            ProcessorDB processorDB = new ProcessorDB();
            for (int i = 0; i < personId.size(); i++) {
                Person person = new Person(processorDB.getPersonById(personId.get(i)));
                person.setAverageRating(averageMarks.get(i));
                dataGroups.addPerson(person);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return dataGroups;
    }

}
