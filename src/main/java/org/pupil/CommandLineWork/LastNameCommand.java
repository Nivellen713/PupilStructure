package org.pupil.CommandLineWork;

import org.pupil.DataProcessing.StudentService;

import java.util.Arrays;

public class LastNameCommand implements ICommand {
    @Override
    public void execute(StudentService studentService, String parameter) {
        System.out.println(
                Arrays.toString(studentService.getPersonNameDataGroup().getByLastName(parameter)));

    }
}
