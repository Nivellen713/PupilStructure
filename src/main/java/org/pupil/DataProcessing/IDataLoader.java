package org.pupil.DataProcessing;

import org.pupil.DataGroups.IDataGroups;

public interface IDataLoader {
    void addAllPersonFromCSV(IDataGroups dataGroups);
    void addAllPersonsFromDataBase(IDataGroups dataGroups);
}
