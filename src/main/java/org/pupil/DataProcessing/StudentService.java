package org.pupil.DataProcessing;

import org.pupil.DataGroups.ClassroomDataGroups;
import org.pupil.DataGroups.PersonAgeDataGroups;
import org.pupil.DataGroups.PersonNameDataGroup;

public class StudentService {
    PersonAgeDataGroups personAgeDataGroups = new PersonAgeDataGroups();
    ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
    PersonNameDataGroup personNameDataGroup = new PersonNameDataGroup();

    public StudentService(IDataLoader dataLoader) {
        dataLoader.addAllPersonsFromDataBase(personAgeDataGroups);
        dataLoader.addAllPersonsFromDataBase(classroomDataGroups);
        dataLoader.addAllPersonsFromDataBase(personNameDataGroup);

//        dataLoader.addAllPersonFromCSV(personAgeDataGroups);
//        dataLoader.addAllPersonFromCSV(classroomDataGroups);
//        dataLoader.addAllPersonFromCSV(personNameDataGroup);
    }

    public PersonAgeDataGroups getPersonAgeDataGroups() {
        return personAgeDataGroups;
    }

    public ClassroomDataGroups getClassroomDataGroups() {
        return classroomDataGroups;
    }

    public PersonNameDataGroup getPersonNameDataGroup() {
        return personNameDataGroup;
    }
}
