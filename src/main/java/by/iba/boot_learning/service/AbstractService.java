package by.iba.boot_learning.service;

import java.util.List;

public interface AbstractService<T> {

    void insert(T object);

    List<T> loadAllObjects();

     T findObjectById(long id);

    int getAmountOfAllObjects();
}
