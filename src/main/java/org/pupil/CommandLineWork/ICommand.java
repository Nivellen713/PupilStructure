package org.pupil.CommandLineWork;

import org.pupil.DataProcessing.StudentService;
public interface ICommand {
    void execute(StudentService studentService, String parameter);

}
