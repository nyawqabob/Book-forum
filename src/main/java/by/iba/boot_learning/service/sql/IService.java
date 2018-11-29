package by.iba.boot_learning.service.sql;

import by.iba.boot_learning.entity.InsertResult;

import java.util.List;

public interface IService<T> {

    InsertResult insert(T object);

    List<T> loadAllObjects();

     T findObjectById(long id);

    int getAmountOfAllObjects();
}
