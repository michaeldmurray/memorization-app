package csc472.depaul.edu.micvalmoy.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/*
-- A quiz belongs to a course
CREATE  TABLE quiz_courses (
  quiz_id INTEGER REFERENCES quizzes,
  course_id INTEGER REFERENCES courses,
  PRIMARY KEY (quiz_id,course_id)
);

 */


@Entity(
        tableName="quiz_courses",
        primaryKeys={ "quiz_id","course_id"},
        foreignKeys= {

                @ForeignKey(
                        entity = Quiz.class,
                        parentColumns = "id",
                        childColumns = "quiz_id",
                        onDelete = CASCADE),
                @ForeignKey(
                        entity = Course.class,
                        parentColumns = "id",
                        childColumns = "course_id",
                        onDelete = CASCADE)
        },
        indices={
                @Index(value="quiz_id"),
                @Index(value="course_id")
        }
)

public class QuizCourse {
        @NonNull
        @ColumnInfo(name = "course_id")
        public final Long courseId;

        @ColumnInfo(name = "quiz_id")
        @NonNull public final Long quizId;

        public QuizCourse( Long quizId,Long courseId) {
                this.courseId=courseId;
                this.quizId=quizId;
        }

        /**
         * Basic getters /setters
         */
        @NonNull
        public Long getCourseId() {
                return courseId;
        }

        @NonNull
        public Long getQuizId() {
                return quizId;
        }

        @Override
        public String toString() {
                return "QuizCourse{" +
                        "courseId=" + courseId +
                        ", quizId=" + quizId +
                        '}';
        }
}