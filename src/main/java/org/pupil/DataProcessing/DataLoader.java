package org.pupil.DataProcessing;

import org.pupil.DataBase.ConnectorDB;
import org.pupil.DataBase.ProcessorDB;
import org.pupil.Structures.Person;
import org.pupil.DataGroups.IDataGroups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

// класс для заполнения списков данными (чтение из csv файла)
public class DataLoader implements IDataLoader {

    private final String DATA_FILE_PATH = "src/main/resources/data/students.csv";

    // добавляем всех учеников из файла без доп критериев
    public void addAllPersonFromCSV(IDataGroups dataGroup) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            reader.readLine(); // пропускаем первую строку названий столбцов
            while ((line = reader.readLine()) != null) {
                String[] csvLine = line.split(";");
                dataGroup.addPerson(new Person(csvLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // заполняем базу данными из csv файла
    public void fillDataBaseFromCSV() {
        fillPersonTable();
        fillRatingTable();
    }

    private void fillPersonTable() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            reader.readLine();
            ConnectorDB connectorDB = new ConnectorDB();
            while ((line = reader.readLine()) != null) {
                String stringFormat = makeFillPersonRequest(line);
                connectorDB.getStatement().executeUpdate(stringFormat);
            }
            connectorDB.disconnect();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static String makeFillPersonRequest(String line) {
        String[] csvLine = line.split(";");
        Person person = new Person(csvLine);
        return String.format(
                "INSERT INTO person(family, name, age, \"group\") " +
                        "VALUES ('%s', '%s', %d, %d)",
                person.getLastName(),
                person.getName(),
                person.getAge(),
                person.getGroupName());
    }

    private void fillRatingTable() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE_PATH))) {
            String line;
            reader.readLine();
            ConnectorDB connectorDB = new ConnectorDB();
            while ((line = reader.readLine()) != null) {
                String stringFormat = makeFillRatingRequest(line);
                connectorDB.getStatement().executeUpdate(stringFormat);
            }
            connectorDB.disconnect();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static String makeFillRatingRequest(String line) {
        String[] csvLine = line.split(";");
        Person person = new Person(csvLine);
        String[] rating = person.getRating();
        return String.format(
                "INSERT INTO rating(physics, mathematics, rus, literature, geometry, informatics) " +
                        "VALUES (%x, %x, %x, %x, %x, %x)",
                Integer.parseInt(rating[0]),
                Integer.parseInt(rating[1]),
                Integer.parseInt(rating[2]),
                Integer.parseInt(rating[3]),
                Integer.parseInt(rating[4]),
                Integer.parseInt(rating[5]));
    }

    public void addAllPersonsFromDataBase(IDataGroups dataGroup) {
        ProcessorDB processorDB = new ProcessorDB();
        for (int i = 1; i < 10_000; i++) {
            dataGroup.addPerson(new Person(processorDB.getPersonById(i)));
        }
    }

}
