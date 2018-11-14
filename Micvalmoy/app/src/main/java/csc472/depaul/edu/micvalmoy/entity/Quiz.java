package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/*


CREATE TABLE quizzes (
id INTEGER primary key AUTOINCREMENT,
  name VARCHAR(325) NOT NULL
);

 */

@Entity(tableName = "quizzes")
public class Quiz {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private Long id;

    @ColumnInfo
    private String name;

    @ColumnInfo
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Basic getters /setters
     */


    
    //------------------------------------------

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}