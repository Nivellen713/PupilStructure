package org.pupil;

import org.pupil.CommandLineWork.CommandBuilder;
import org.pupil.DataProcessing.DataLoader;

public class Main {
    // ar groupNum - возвращает среднюю оценку (ar - average rating) учеников "groupNum" группы
    // eo age - возвращает отличников старше (eo - excellent older) возраста "age"
    // ln lastName - возвращает учеников с фамилией (ln -lastname) "lastName"
    // arln lastname - возвращает среднюю оценку учеников по фамилии "lastName"
    public static void main(String[] args) {
        //new DataLoader().fillDataBaseFromCSV(); // выполняется один раз, чтобы заполнить таблицу данными
        CommandBuilder commandBuilder = new CommandBuilder();
        commandBuilder.inputUserCommand();
    }
}