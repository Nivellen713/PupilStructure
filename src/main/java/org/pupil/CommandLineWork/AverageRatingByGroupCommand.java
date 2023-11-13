package org.pupil.CommandLineWork;

import org.pupil.DataProcessing.StudentService;

public class AverageRatingByGroupCommand implements ICommand {
    @Override
    public void execute(StudentService studentService, String parameter) {
//        System.out.println(
//                studentService.getClassroomDataGroups().getAverageRating(
//                        Integer.parseInt(parameter)));
        System.out.println(studentService.getClassroomDataGroups().getAverageRatingFromDB(Integer.parseInt(parameter)));
    }
}
