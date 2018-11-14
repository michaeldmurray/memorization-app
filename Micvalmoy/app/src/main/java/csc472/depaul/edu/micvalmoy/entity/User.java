package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/*

CREATE TABLE users(
        id INTEGER primary key AUTOINCREMENT,
        username VARCHAR(45) NOT NULL ,
        password VARCHAR(45) NOT NULL ,
        enabled INTEGER NOT NULL DEFAULT 1 --  Boolean values are stored as INTEGERs 0 (false) and 1 (true)
        );


*/


@Entity(tableName = "users")
public class User {
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private Long id;

    @ColumnInfo
    private String username;

    @ColumnInfo
    private String password;

    @ColumnInfo
    private Boolean enabled;


    /**
     * Basic getters /setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}