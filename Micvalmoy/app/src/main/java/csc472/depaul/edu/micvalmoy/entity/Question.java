package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;
import java.time.OffsetDateTime;

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
    private OffsetDateTime createdAt;

    @ColumnInfo
    private OffsetDateTime updatedAt;


    public Question() {
        OffsetDateTime date = OffsetDateTime.now();
        this.createdAt = date;
        this.updatedAt = date;
    }

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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", hint='" + hint + '\'' +
                ", type='" + type + '\'' +
                ", nonce='" + nonce + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
