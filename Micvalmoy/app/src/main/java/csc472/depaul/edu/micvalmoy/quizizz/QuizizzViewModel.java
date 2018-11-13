package csc472.depaul.edu.micvalmoy.quizizz;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.ArrayList;

import csc472.depaul.edu.micvalmoy.quizizz.QuizizzRepository;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;

public class QuizizzViewModel extends AndroidViewModel {

    @NonNull
    private QuizizzRepository repo = QuizizzRepository.getInstance();

    @NonNull
    private LiveData<String> myLiveData;

    public QuizizzViewModel(@NonNull Application application) {
        super(application);
    }

    @NonNull
    public LiveData<ArrayList<QuizInfo>> getQuizizzByTermLiveData() {
        return repo.getQuizizzByTermLiveData();
    }

    public LiveData<ArrayList<QuizInfo>> findQuizizzByTerm(String quizizzSearchParm) {
        return repo.findQuizizzByTerm(quizizzSearchParm);
    }
}
