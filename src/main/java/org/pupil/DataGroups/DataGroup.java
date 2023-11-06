package org.pupil.DataGroups;

import org.pupil.Structures.Person;

class DataGroup<T> {
    private final IGroupCriterion<T> groupCriterion;

    public DataGroup(IGroupCriterion<T> groupCriterion) {
        this.groupCriterion = groupCriterion;
    }

    public void addPerson(Person person) {
        var key = groupCriterion.getKey(person);
    }
}
