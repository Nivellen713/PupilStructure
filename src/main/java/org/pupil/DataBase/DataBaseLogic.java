package org.pupil.DataBase;

import java.sql.*;

public class DataBaseLogic {
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    Connection connection;

    public DataBaseLogic() throws SQLException {
        this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }

    public void commandWork() throws SQLException {
        Statement statement = connection.createStatement();
        String SQL_SELECT_LASTNAMES = "select * from family order by family desc";
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_LASTNAMES);
        while (resultSet.next()){
            System.out.println(resultSet.getString("family")
                    + " " + resultSet.getString("name")
                    + " " + resultSet.getInt("group"));
        }
    }


}
