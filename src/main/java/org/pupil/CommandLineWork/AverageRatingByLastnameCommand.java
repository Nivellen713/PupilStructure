package org.pupil.CommandLineWork;

import org.pupil.DataProcessing.StudentService;

import java.util.Arrays;

public class AverageRatingByLastnameCommand implements ICommand{
    @Override
    public void execute(StudentService studentService, String parameter) {
        System.out.println(Arrays.toString(
                studentService.getPersonNameDataGroup().getAverageRatingByLastNameFromDB(parameter)));
    }
}
