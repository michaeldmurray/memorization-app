package csc472.depaul.edu.micvalmoy;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.repository.QuizRepository;

public class QuizViewModel extends AndroidViewModel {

    @NonNull
    private QuizRepository repo;

    @NonNull
    private LiveData<String> myLiveData;

    public QuizViewModel(@NonNull Application application) {
        super(application);

        repo = QuizRepository.getInstance(application);


    }

    @NonNull
    public LiveData<List<Quiz>> getQuizzes() {
        return repo.getQuizzes();
    }

    public LiveData<Long> insertQuiz(final Quiz quiz) {
        return repo.insertQuiz(quiz);
    }
    public void saveQuiz(final Quiz quiz) {
      repo.saveQuiz(quiz);
    }


    public LiveData<List<Category>> getCategories() {
        return repo.getCategories();
    }
}
