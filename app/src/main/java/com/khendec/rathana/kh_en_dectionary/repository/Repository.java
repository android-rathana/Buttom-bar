package com.khendec.rathana.kh_en_dectionary.repository;

import java.util.List;

/**
 * Created by ratha on 06-Sep-17.
 */

public interface Repository<T> {

    List<T> getAll();
    T getOne(T t);
    void add(T t);
    boolean update(T oldT,T newT);
    boolean delete(T t);

}
