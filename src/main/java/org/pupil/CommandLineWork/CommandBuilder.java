package org.pupil.CommandLineWork;

import org.pupil.DataProcessing.StudentService;
import org.pupil.DataProcessing.DataLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandBuilder {
    StudentService studentService = new StudentService(new DataLoader());
    AverageRatingCommand averageRatingCommand = new AverageRatingCommand();
    ExcellentOlderCommand excellentOlderCommand = new ExcellentOlderCommand();
    LastNameCommand lastNameCommand = new LastNameCommand();

    public void inputUserCommand() {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            userInputHandler(bufferedReader.readLine().trim());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void userInputHandler(String userInput) {
        String[] userInputParameters = userInput.split("\\u0020");
        switch (userInputParameters[0]) {
            case "ar" -> getAverageRatingByGroup(userInputParameters[1]);
            case "eo" -> getExcellentOlder(userInputParameters[1]);
            case "ln" -> getByLastName(userInputParameters[1]);
            case "help" -> getHelp();
            default -> System.out.println("Command not found. Use help command");
        }
    }

    private void getAverageRatingByGroup(String parameter) {
        averageRatingCommand.execute(studentService, parameter);
    }

    private void getExcellentOlder(String parameter) {
        excellentOlderCommand.execute(studentService, parameter);
    }

    private void getByLastName(String parameter) {
        lastNameCommand.execute(studentService, parameter);
    }

    private void getHelp() {
        System.out.println("""
                ar groupNum - возвращает среднюю оценку (ar - average rating) учеников "groupNum" группы
                eo age - возвращает отличников старше (eo - excellent older) возраста "age"
                ln lastName - возвращает учеников с фамилией (ln -lastname) "lastName"
                """);
    }
}
