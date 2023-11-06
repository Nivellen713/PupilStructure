package org.pupil.DataGroups;

import org.pupil.Structures.Person;

@FunctionalInterface
public interface IGroupCriterion<T> {
    int getKey(Person person);
}

