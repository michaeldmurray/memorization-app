package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;

/*


CREATE TABLE questions (
    id INTEGER primary key AUTOINCREMENT,
    text VARCHAR(1000) NOT NULL,
    hint VARCHAR(800),
    type VARCHAR(10) NOT NULL,  -- multiple, truefalse, termdef
    nonce VARCHAR(100),
    createdAt date not null,
    updatedAt date not null
);



 */


@Entity(tableName = "questions")
public class Question{
    
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    Long id;

    @NonNull
    @ColumnInfo
    private String text;

    @ColumnInfo
    private String hint;

    @NonNull
    @ColumnInfo
    private String type;

    @ColumnInfo
    private String nonce;

    @ColumnInfo
    private Date createdAt;

    @ColumnInfo
    private Date updatedAt;


    /**
     * Basic getters /setters
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
