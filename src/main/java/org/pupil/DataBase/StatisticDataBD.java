package org.pupil.DataBase;

import org.pupil.DataGroups.IDataGroups;
import org.pupil.DataGroups.PersonAgeDataGroups;
import org.pupil.DataGroups.PersonNameDataGroup;
import org.pupil.Structures.Person;

import java.sql.*;
import java.util.LinkedList;

public class StatisticDataBD {

    private final ConnectorDB CONNECTOR_DB;

    public StatisticDataBD() {
        CONNECTOR_DB = new ConnectorDB();
    }

    public double getAverageRatingByGroup(int groupNum) {
        double averageRating = 0.0;
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT rating_id FROM person WHERE \"group\" = " + groupNum);
            LinkedList<Integer> ratingId = new LinkedList<>();
            while (resultSet.next()) {
                ratingId.add(resultSet.getInt(1));
            }
            resultSet.close();
            int count = 0;
            for (int id : ratingId) {
                resultSet = CONNECTOR_DB.getStatement().executeQuery(
                        "SELECT * FROM rating WHERE rating_id = " + id);
                while (resultSet.next()) {
                    for (int i = 2; i <= 6; i++) {
                        averageRating += resultSet.getInt(i);
                        count++;
                    }
                }
            }
            resultSet.close();
            averageRating /= count;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            CONNECTOR_DB.disconnect();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return averageRating;
    }

    public IDataGroups getExcellentOlderThan(int age) {
        IDataGroups dataGroups = new PersonAgeDataGroups();
        LinkedList<Integer> excellentPersonIdOlderThan = new LinkedList<>();
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT rating_id FROM person WHERE age >= " + age
            );
            LinkedList<Integer> ratingIdPersonsOlderThan = new LinkedList<>();
            while (resultSet.next()) {
                ratingIdPersonsOlderThan.add(resultSet.getInt(1));
            }
            resultSet.close();
            resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    """
                            SELECT * FROM rating WHERE physics = 5 AND mathematics = 5 AND rus = 5 AND literature = 5 AND geometry = 5 AND  informatics = 5
                            """
            );
            for (int id : ratingIdPersonsOlderThan) {
                if (resultSet.getInt(1) == id) {
                    excellentPersonIdOlderThan.add(resultSet.getInt(1));
                }
            }
            resultSet.close();
            ProcessorDB processorDB = new ProcessorDB();
            for (int id : excellentPersonIdOlderThan) {
                dataGroups.addPerson(new Person(processorDB.getPersonById(id)));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            CONNECTOR_DB.disconnect();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return dataGroups;
    }

    public IDataGroups getAverageRatingByLastName(String lastname) {
        IDataGroups dataGroups = new PersonNameDataGroup();
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT * FROM person WHERE family = " + lastname
            );
            LinkedList<Integer> ratingId = new LinkedList<>();
            while (resultSet.next()) {
                ratingId.add(resultSet.getInt(6));
            }
            resultSet.close();
            for (int id : ratingId) {
                resultSet = CONNECTOR_DB.getStatement().executeQuery(
                        "SELECT physics AND mathematics AND rus AND informatics AND literature AND geometry " +
                                "FROM rating WHERE rating_id = " + id
                );
                double averageRating = 0.0;
                for (int i = 1; i <= 6; i++) {
                    averageRating += resultSet.getDouble(i);
                }
                resultSet.close();
                averageRating /= 6;
                ProcessorDB processorDB = new ProcessorDB();
                Person person = new Person(processorDB.getPersonById(id));
                person.setAverageRating(averageRating);
                dataGroups.addPerson(person);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            CONNECTOR_DB.disconnect();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return dataGroups;
    }

}
