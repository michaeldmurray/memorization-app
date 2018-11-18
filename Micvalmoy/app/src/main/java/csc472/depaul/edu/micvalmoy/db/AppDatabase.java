package csc472.depaul.edu.micvalmoy.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;


import csc472.depaul.edu.micvalmoy.dao.CategoryDao;
import csc472.depaul.edu.micvalmoy.dao.CourseDao;
import csc472.depaul.edu.micvalmoy.dao.ExamDao;
import csc472.depaul.edu.micvalmoy.dao.QuestionAnswerOptionDao;
import csc472.depaul.edu.micvalmoy.dao.QuestionDao;
import csc472.depaul.edu.micvalmoy.dao.QuizCategoryDao;
import csc472.depaul.edu.micvalmoy.dao.QuizCourseDao;
import csc472.depaul.edu.micvalmoy.dao.QuizDao;
import csc472.depaul.edu.micvalmoy.dao.UserAnswerDao;
import csc472.depaul.edu.micvalmoy.dao.UserDao;
import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Course;
import csc472.depaul.edu.micvalmoy.entity.Exam;
import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.entity.QuizCategory;
import csc472.depaul.edu.micvalmoy.entity.QuizCourse;
import csc472.depaul.edu.micvalmoy.entity.User;
import csc472.depaul.edu.micvalmoy.entity.UserAnswer;
import csc472.depaul.edu.micvalmoy.tools.Converters;

/**
 * bump version number if your schema changes
 *
 *  To change the schema for any tables created later, bump up the version number.
 *  The version number should always be incremented (and never downgraded) to avoid conflicts with older database versions.
 *  Making schema changes will update the definitions in the app/schemas directory
 *
 *  https://guides.codepath.com/android/Room-Guide
 *
 *  https://www.captechconsulting.com/blogs/android-architecture-components-room-persistence-library
 *  https://developer.android.com/reference/android/arch/persistence/room/Relation
 *  https://commonsware.com/AndroidArch/previews/mn-relations-in-room
 */


@Database(entities={
        Category.class,
        Course.class,
        Exam.class,
        Question.class,
        QuestionAnswerOption.class,
        Quiz.class,
        QuizCategory.class,
        QuizCourse.class,
        User.class,
        UserAnswer.class
}, version=1)

@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    // Database name to be used
    public static final String DATABASE_NAME = "AppDatabase";
    private static AppDatabase INSTANCE;



    public abstract CategoryDao CategoryDao();
    public abstract CourseDao CourseDao();


    public abstract QuizDao QuizDao();
    public abstract QuizCategoryDao QuizCategoryDao();
    public abstract QuizCourseDao QuizCourseDao();

    public abstract QuestionDao QuestionDao();
    public abstract QuestionAnswerOptionDao QuestionAnswerOptionDao();

    public abstract UserDao UserDao();
    public abstract UserAnswerDao UserAnswerDao();
    public abstract ExamDao ExamDao();




    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {

                        // Create an in-memory database
                        INSTANCE = Room.inMemoryDatabaseBuilder(
                                context.getApplicationContext(),
                                AppDatabase.class)
                                .build();

                        // Create a file storage based database
                        /*
                        INSTANCE = Room.databaseBuilder(
                                context.getApplicationContext(),
                                AppDatabase.class, DATABASE_NAME)
                                .build();

                                */

                }
            }
        }
        return INSTANCE;
    }
}