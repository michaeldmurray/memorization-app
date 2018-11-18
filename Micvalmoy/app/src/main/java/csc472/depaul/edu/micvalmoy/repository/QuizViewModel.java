package csc472.depaul.edu.micvalmoy.repository;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import csc472.depaul.edu.micvalmoy.db.AppDatabase;
import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Course;
import csc472.depaul.edu.micvalmoy.entity.Exam;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.entity.User;
import csc472.depaul.edu.micvalmoy.repository.QuizRepository;

public class QuizViewModel extends AndroidViewModel {

    private CategoryRepository categoryRepository;
    private CourseRepository courseRepository;
    private UserRepository userRepository;
/*
    private QuestionRepository questionRepository;
    private QuestionAnswerOptionRepository questionAnswerOptionRepository;
*/

    private QuizRepository quizRepository;
/*    private QuizCategoryRepository quizCategoryRepository;
    private QuizCourseRepository quizCourseRepository;
    private QuizQuestionRepository quizQuestionRepository;*/
    private UserAnswerRepository userAnswerRepository;
    private ExamRepository examRepository;



    AppDatabase appDatabase;

    public QuizViewModel(@NonNull Application application) {
        super(application);

        this.appDatabase = AppDatabase.getDatabase(application);

        categoryRepository               = CategoryRepository.getInstance(application,appDatabase);
        courseRepository                 = CourseRepository.getInstance(application,appDatabase);
        userRepository                   = UserRepository.getInstance(application,appDatabase);


        quizRepository                   = QuizRepository.getInstance(application,appDatabase);
/*        quizCategoryRepository         = QuizCategoryRepository.getInstance(application,appDatabase);
        quizCourseRepository             = QuizCourseRepository.getInstance(application,appDatabase);
        quizQuestionRepository           = QuizQuestionRepository.getInstance(application,appDatabase);

        questionRepository               = QuestionRepository.getInstance(application,appDatabase);
        questionAnswerOptionRepository   = QuestionAnswerOptionRepository.getInstance(application,appDatabase);
        */

        examRepository                   = ExamRepository.getInstance(application,appDatabase);
        userAnswerRepository             = UserAnswerRepository.getInstance(application,appDatabase);



    }




    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //User table
    //-------------------------------------------------------------
    public LiveData<List<User>> fetchAllUsers() {
        return userRepository.fetchAll();
    }

    public LiveData<User> fetchUser(Long userId) {
        return userRepository.fetchById(userId);
    }

    public LiveData<Long> insertUser(User user) {
        return userRepository.insert(user);
    }

    public LiveData<List<Long>> insertAllUsers(User user) {
        return userRepository.insertAll(user);
    }

    public LiveData<Integer> updateUser(User user) {
        return userRepository.updateAll(user);
    }

    public LiveData<Integer> deleteUser(User user) {
        return userRepository.delete(user);
    }

    public LiveData<Integer> deleteAllUsers() {
        return userRepository.deleteAll();
    }






    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //Quiz table
    //-------------------------------------------------------------
    public LiveData<List<Quiz>> fetchAllQuizzes() {
        return quizRepository.fetchAll();
    }

    public LiveData<Quiz> fetchQuiz(Long quizId) {
        return quizRepository.fetchById(quizId);
    }

    public LiveData<Long> insertQuiz(Quiz quiz) {
        return quizRepository.insert(quiz);
    }

    public LiveData<List<Long>> insertAllQuizzes(Quiz quiz) {
        return quizRepository.insertAll(quiz);
    }

    public LiveData<Integer> updateQuiz(Quiz quiz) {
        return quizRepository.updateAll(quiz);
    }

    public LiveData<Integer> deleteQuiz(Quiz quiz) {
        return quizRepository.delete(quiz);
    }

    public LiveData<Integer> deleteAllQuizzes() {
        return quizRepository.deleteAll();
    }


    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //Category table
    //-------------------------------------------------------------
    public LiveData<List<Category>> fetchAllCategories() {
        return categoryRepository.fetchAll();
    }

    public LiveData<Category> fetchCategory(Long categoryId) {
        return categoryRepository.fetchById(categoryId);
    }

    public LiveData<Long> insertCategory(Category category) {
        return categoryRepository.insert(category);
    }

    public LiveData<List<Long>> insertAllCategories(Category category) {
        return categoryRepository.insertAll(category);
    }

    public LiveData<Integer> updateCategory(Category category) {
        return categoryRepository.updateAll(category);
    }

    public LiveData<Integer> deleteCategory(Category category) {
        return categoryRepository.delete(category);
    }

    public LiveData<Integer> deleteAllCategories() {
        return categoryRepository.deleteAll();
    }


    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //Course table
    //-------------------------------------------------------------

    public LiveData<List<Course>> fetchAllCourses() {
        return courseRepository.fetchAll();
    }

    public LiveData<Course> fetchCourse(Long courseId) {
        return courseRepository.fetchById(courseId);
    }

    public LiveData<Long> insertCourse(Course course) {
        return courseRepository.insert(course);
    }

    public LiveData<List<Long>> insertAllCourses(Course course) {
        return courseRepository.insertAll(course);
    }

    public LiveData<Integer> updateCourse(Course course) {
        return courseRepository.updateAll(course);
    }

    public LiveData<Integer> deleteCourse(Course course) {
        return courseRepository.delete(course);
    }

    public LiveData<Integer> deleteAllCourses() {
        return courseRepository.deleteAll();
    }


    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //-------------------------------------------------------------
    //Exam table
    //-------------------------------------------------------------
    public LiveData<List<Exam>> fetchAllExams() {
        return examRepository.fetchAll();
    }

    public LiveData<Exam> fetchExam(Long examId) {
        return examRepository.fetchById(examId);
    }

    public LiveData<Long> insertExam(Exam exam) {
        return examRepository.insert(exam);
    }

    public LiveData<List<Long>> insertAllExams(Exam exam) {
        return examRepository.insertAll(exam);
    }

    public LiveData<Integer> updateExam(Exam exam) {
        return examRepository.updateAll(exam);
    }

    public LiveData<Integer> deleteExam(Exam exam) {
        return examRepository.delete(exam);
    }

    public LiveData<Integer> deleteAllExams() {
        return examRepository.deleteAll();
    }

}
