package csc472.depaul.edu.micvalmoy.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.User;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(User... user);

    @Update
    public int update(User user);

    @Update
    public int updateAll(User... user);

    @Delete
    public int delete(User user);


    //-------------------------------------------------

    @Delete
    int deleteAll(User... users);


    @Query("DELETE FROM users where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM users where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM users")
    List<User> getAll();

    @Query("SELECT * FROM users")
    public LiveData<List<User>> fetchAll();


    @Query("SELECT * FROM users WHERE id =:id")
    public LiveData<User> fetchById(Long id);


    @Query("SELECT * FROM users WHERE id IN (:ids)")
    public LiveData<List<User>> fetchByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM users ORDER BY username asc")
    public LiveData<List<User>> fetchAllSortByName();


    @Query("SELECT COUNT(*) FROM users")
    public int getCount();

}