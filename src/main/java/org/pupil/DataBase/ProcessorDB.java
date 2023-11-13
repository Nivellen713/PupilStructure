package org.pupil.DataBase;

//import org.pupil.Structures.Person;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.lang.reflect.Field;
import java.sql.*;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.IntStream;

public class ProcessorDB {

    private final ConnectorDB CONNECTOR_DB;

    public ProcessorDB() {
        CONNECTOR_DB = new ConnectorDB();
    }

    public String[] getPersonById(int id) {
        String[] personParam = new String[10];
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT * FROM person WHERE person_id = " + id);
            while (resultSet.next()) {
                personParam = new String[10];
                for (int i = 0; i < 3; i++) {
                    personParam[i] = resultSet.getString(i + 1);
                }
                String[] rating = getRating(resultSet.getInt(6));
                System.arraycopy(rating, 0, personParam, 4, rating.length);
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            CONNECTOR_DB.disconnect();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return personParam;
    }

    public String[] getRating(int ratingId) throws SQLException {
        String[] rating = new String[6];
        try {
            ResultSet resultSet = CONNECTOR_DB.getStatement().executeQuery(
                    "SELECT * FROM rating WHERE rating_id = " + ratingId);
            while (resultSet.next()) {
                for (int i = 0; i < 6; i++) {
                    rating[i] = resultSet.getString(i + 1);
                }
            }
            resultSet.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            CONNECTOR_DB.disconnect();
        } finally {
            CONNECTOR_DB.disconnect();
        }
        return rating;
    }


//    public static void buildTable(Class cl) throws SQLException {
//        if (!cl.isAnnotationPresent(Table.class)) {
//            throw new RuntimeException("@Table missed");
//        }
//        Map<Class, String> map = new HashMap<>();
//        map.put(int.class, "INTEGER");
//        map.put(String.class, "TEXT");
//        // CREATE TABLE person (lastname TEXT, name TEXT, age INTEGER, group INTEGER, rating FOREIGN_KEY)
//        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");
//        stringBuilder.append(((Table) cl.getAnnotation(Table.class)).title());
//        stringBuilder.append(" (");
//        Field[] fields = cl.getDeclaredFields();
//        for (Field o : fields) {
//            if (o.isAnnotationPresent(Column.class)) {
//                stringBuilder.append(o.getName())
//                        .append(" ")
//                        .append(map.get(o.getType()))
//                        .append(", ");
//            }
//        }
//        stringBuilder.setLength(stringBuilder.length() - 2);
//        stringBuilder.append(");");
//        statement.executeUpdate(stringBuilder.toString());
//    }
//
//    public static void fillTable(Class cl) throws SQLException, IOException {
//        BufferedReader bufferedReader = new BufferedReader(
//                new FileReader("src/main/resources/students.csv"));
//        String csvLine = null;
//        int count = 0;
//        bufferedReader.readLine();
//        while ((csvLine = bufferedReader.readLine()) != null) {
//
//            StringBuilder stringBuilder = new StringBuilder();
//            // INSERT INTO person values(?,?,?,?,?)
//            stringBuilder.append("INSERT INTO ")
//                    .append(((Table) cl.getAnnotation(Table.class)).title());
//            stringBuilder.append(" (");
//
//            Field[] fields = cl.getDeclaredFields();
//            for (Field o : fields) {
//                if (o.isAnnotationPresent(Column.class)) {
//                    stringBuilder.append(o.getName())
//                            .append(", ");
//                }
//            }
//            stringBuilder.setLength(stringBuilder.length() - 2);
//            stringBuilder.append(") VALUES (");
//
//            String[] data = csvLine.split(";");
//            String lastname = data[0];
//            String name = data[1];
//            int age = Integer.parseInt(data[2]);
//            int groupName = Integer.parseInt(data[3]);
//
//            stringBuilder.append("'").append(lastname).append("', ")
//                    .append("'").append(name).append("', ")
//                    .append(age).append(", ")
//                    .append(groupName).append(", ");
//
//            stringBuilder.setLength(stringBuilder.length() - 2);
//            stringBuilder.append(");");
//
//            System.out.println(stringBuilder.toString());
//
//            statement.executeUpdate(stringBuilder.toString());
//        }
//        bufferedReader.close();
//        statement.executeBatch();
//        connection.close();
//    }
}
