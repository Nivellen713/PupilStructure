package org.pupil.CommandLineWork;

import org.pupil.DataProcessing.StudentService;

import java.util.Arrays;

public class ExcellentOlderCommand implements ICommand {
    @Override
    public void execute(StudentService studentService, String parameter) {
//        System.out.println(
//                Arrays.toString(studentService.getPersonAgeDataGroups().getExcellentOlderThan(
//                        Integer.parseInt(parameter))));
        System.out.println(Arrays.deepToString(
                studentService.getPersonAgeDataGroups().getExcellentOlderThanFromDB(
                        Integer.parseInt(parameter))));
    }
}
