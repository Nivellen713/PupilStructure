package org.pupil.DataProcessing;

import org.pupil.Structures.Person;
import org.pupil.DataGroups.IDataGroups;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// класс для заполнения списков данными (чтение из csv файла)
public class DataLoader implements IDataLoader {

    private final String DATA_FILE_PATH = "src/main/resources/students.csv";


    // добавляем всех учеников из файла без доп критериев
    public void addAllPerson(IDataGroups dataGroup) {
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

}
