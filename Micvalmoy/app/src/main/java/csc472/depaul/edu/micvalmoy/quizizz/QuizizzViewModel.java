package csc472.depaul.edu.micvalmoy.quizizz;

/**
 * @author mrichards
 */

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.quizizz.QuizizzRepository;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;

public class QuizizzViewModel extends AndroidViewModel {

    @NonNull
    private QuizizzRepository repo = QuizizzRepository.getInstance();

    public QuizizzViewModel(@NonNull Application application) {
        super(application);
    }



    public LiveData<List<QuizInfo>> getQuizizzByTermLiveData(String quizizzSearchParm) {
        return repo.findQuizizzByTerm(quizizzSearchParm);
    }


    public LiveData<QuizInfo> getQuizizzByIdLiveData(String quizizz_id) {
        return repo.findQuizizzById(quizizz_id);
    }


    public LiveData<List<Quiz>> getQuizListFromQuizizzByTermLiveData(String quizizzSearchParm) {
        return repo.getQuizListFromQuizizzByTerm(quizizzSearchParm);
    }


    public LiveData<Quiz> getQuizListFromQuizizzByIdLiveData(String quizizz_id) {
        return repo.getQuizListFromQuizizzById(quizizz_id);
    }
}
