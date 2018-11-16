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

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long>  insertAll(List<User> users);

    @Update
    public int update(User user);

    @Update
    public int updateAll(User... user);

    @Delete
    public void delete(User user);

    @Query("DELETE FROM users where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM users where id in (:ids)")
    public int deleteByIds(Long... ids);

    @Query("SELECT * FROM users WHERE id =:id")
    public LiveData<User> fetchById(Long id);


    @Query("SELECT * FROM users WHERE id IN (:ids)")
    public LiveData<User> fetchByIds(Long... ids);


    @Query("SELECT * FROM users")
    public LiveData<List<User>> fetchAll();

    @Query("SELECT COUNT(*) FROM users")
    public int getCount();

    //------------------------------
    @Query("SELECT * FROM users ORDER BY username asc")
    public LiveData<List<User>> fetchAllSortByUserName();

}