package csc472.depaul.edu.micvalmoy.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface Repository<T> {

    public LiveData<T> fetchById(Long id);

    public LiveData<List<T>> fetchAll();


    public LiveData<Long> insert(final T t);

    public LiveData<List<Long>> insertAll(final T... entities);


    public LiveData<Integer> update(final T t);


    public LiveData<Integer> updateAll(final T... entities);


    public LiveData<Integer> delete(final T t);


    public LiveData<Integer> deleteById(final Long Id);


    public LiveData<Integer> deleteAll();

}
