package csc472.depaul.edu.micvalmoy.dao;

/**
 * @author mrichards
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.parceler.Parcel;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Category;


/**
 * Using Ellipsis to Accept Variable Number of Arguments (Var args)
 * https://www.quora.com/Why-does-3-dot-symbol-is-used-in-varargs-Java
 * Accepts a comma separated list of Quiz objects
 * updateAll(q1,q2)
 */



@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insert(Category category);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    List<Long> insertAll(Category... category);

    @Update
    public int update(Category category);

    @Update
    public int updateAll(Category... category);

    @Delete
    public int delete(Category category);


    //-------------------------------------------------

    @Delete
    int deleteAll(Category... categories);


    @Query("DELETE FROM categories where id=:id")
    public int deleteById(Long id);

    @Query("DELETE FROM categories where id in (:ids)")
    public int deleteByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM categories")
    List<Category> getAll();

    @Query("SELECT * FROM categories")
    public LiveData<List<Category>> fetchAll();


    @Query("SELECT * FROM categories WHERE id =:id")
    public LiveData<Category> fetchById(Long id);


    @Query("SELECT * FROM categories WHERE id IN (:ids)")
    public LiveData<List<Category>> fetchByIds(Long... ids);

    //-------------------------------------------------

    @Query("SELECT * FROM categories ORDER BY name asc")
    public LiveData<List<Category>> fetchAllSortByName();


    @Query("SELECT COUNT(*) FROM categories")
    public int getCount();

    //-------------------------------------------------
    @Query("SELECT * FROM categories WHERE name =:id COLLATE NOCASE")
    public Category getCategoryByName(String id);







}


