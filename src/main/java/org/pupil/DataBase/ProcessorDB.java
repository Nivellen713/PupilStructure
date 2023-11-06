package org.pupil.DataBase;

import org.pupil.Structures.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ProcessorDB {

    public static void main(String[] args) {
        try {
            connect();
            buildTable(Person.class);
            fillTable(Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            disconnect();
        }
    }

    private static Connection connection;
    private static Statement statement;

    public static void buildTable(Class cl) throws SQLException {
        if (!cl.isAnnotationPresent(Table.class)) {
            throw new RuntimeException("@Table missed");
        }
        Map<Class, String> map = new HashMap<>();
        map.put(int.class, "INTEGER");
        map.put(String.class, "TEXT");
        // CREATE TABLE person (lastname TEXT, name TEXT, age INTEGER, group INTEGER, rating FOREIGN_KEY)
        StringBuilder stringBuilder = new StringBuilder("CREATE TABLE ");
        stringBuilder.append(((Table) cl.getAnnotation(Table.class)).title());
        stringBuilder.append(" (");
        Field[] fields = cl.getDeclaredFields();
        for (Field o : fields) {
            if (o.isAnnotationPresent(Column.class)) {
                stringBuilder.append(o.getName())
                        .append(" ")
                        .append(map.get(o.getType()))
                        .append(", ");
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(");");
        statement.executeUpdate(stringBuilder.toString());
    }

    public static void fillTable(Class cl) throws SQLException, IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("src/main/resources/students.csv"));
        String csvLine = null;
        int count = 0;
        bufferedReader.readLine();
        while ((csvLine = bufferedReader.readLine()) != null) {

            StringBuilder stringBuilder = new StringBuilder();
            // INSERT INTO person values(?,?,?,?,?)
            stringBuilder.append("INSERT INTO ")
                    .append(((Table) cl.getAnnotation(Table.class)).title());
            stringBuilder.append(" (");

            Field[] fields = cl.getDeclaredFields();
            for (Field o : fields) {
                if (o.isAnnotationPresent(Column.class)) {
                    stringBuilder.append(o.getName())
                            .append(", ");
                }
            }
            stringBuilder.setLength(stringBuilder.length() - 2);
            stringBuilder.append(") VALUES (");

            String[] data = csvLine.split(";");
            String lastname = data[0];
            String name = data[1];
            int age = Integer.parseInt(data[2]);
            int groupName = Integer.parseInt(data[3]);

            stringBuilder.append("'").append(lastname).append("', ")
                    .append("'").append(name).append("', ")
                    .append(age).append(", ")
                    .append(groupName).append(", ");

            stringBuilder.setLength(stringBuilder.length() - 2);
            stringBuilder.append(");");

            System.out.println(stringBuilder.toString());

            statement.executeUpdate(stringBuilder.toString());
        }
        bufferedReader.close();
        statement.executeBatch();
        connection.close();
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/pupilStructure",
                    "postgres", "postgres");
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
