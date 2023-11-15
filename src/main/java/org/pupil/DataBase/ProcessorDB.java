package org.pupil.DataBase;

import java.sql.*;

public class ProcessorDB {

    public String[] getPersonById(int id) {
        String[] personParam = new String[10];
        ConnectorDB connectorDB = new ConnectorDB();
        try (ResultSet resultSet = connectorDB.getStatement().executeQuery(
                "SELECT * FROM person WHERE person_id = " + id)) {
            while (resultSet.next()) {
                personParam = new String[10];
                for (int i = 0; i < 4; i++) {
                    personParam[i] = resultSet.getString(i + 2);
                }
                String[] rating = getRating(resultSet.getInt(6));
                System.arraycopy(rating, 0, personParam, 4, rating.length);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connectorDB.disconnect();
        }
        return personParam;
    }

    public String[] getRating(int ratingId) throws SQLException {
        String[] rating = new String[6];
        ConnectorDB connectorDB = new ConnectorDB();
        try (ResultSet resultSet = connectorDB.getStatement().executeQuery(
                "SELECT * FROM rating WHERE rating_id = " + ratingId)) {
            while (resultSet.next()) {
                for (int i = 0; i < 6; i++) {
                    rating[i] = resultSet.getString(i + 1);
                }
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            connectorDB.disconnect();
        }
        return rating;
    }
}
