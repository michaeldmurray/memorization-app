package csc472.depaul.edu.micvalmoy.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

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
@Entity(
        tableName="questions",
        foreignKeys={
                @ForeignKey(
                        entity=Quiz.class,
                        parentColumns="id",
                        childColumns="quiz_id",
                        onDelete=CASCADE
                )
        },
        indices={
                @Index(value="quiz_id")
        }
)
public class Question{
    @ColumnInfo
    @PrimaryKey(autoGenerate=true)
    private Long id;

    @NonNull
    @ColumnInfo(name = "quiz_id")
    private Long quizId;

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

    @ColumnInfo(name = "sort_index")
    private Integer sortIndex;

    @NonNull
    private boolean enabled;

    public Question() {
        OffsetDateTime date = OffsetDateTime.now();
        this.createdAt = date;
        this.updatedAt = date;
    }

    @Ignore
    public List<QuestionAnswerOption> options = new ArrayList<>();

    @Ignore
    public List<QuestionAnswerOption> correctAnswers = new ArrayList<>();


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
    public Long getQuizId() {
        return quizId;
    }

    public void setQuizId(@NonNull Long quizId) {
        this.quizId = quizId;
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

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    @NonNull
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(@NonNull boolean enabled) {
        this.enabled = enabled;
    }

    public List<QuestionAnswerOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionAnswerOption> options) {
        this.options = options;
    }

    public List<QuestionAnswerOption> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<QuestionAnswerOption> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    @Override
    public String toString() {
        return "Question{" +
                " id=" + id +
                ", quizId=" + quizId +
                ", text='" + text + '\'' +
                ", hint='" + hint + '\'' +
                ", type='" + type + '\'' +
                ", nonce='" + nonce + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", sortIndex=" + sortIndex +
                ", enabled=" + enabled +
                ", options=" + options.toString() +
                ", correctAnswers=" + correctAnswers.toString() +
                '}';
    }
}
