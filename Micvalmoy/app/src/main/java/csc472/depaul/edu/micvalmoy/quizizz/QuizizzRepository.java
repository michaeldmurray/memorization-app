package csc472.depaul.edu.micvalmoy.quizizz;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.List;

import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Quizizz;
import timber.log.Timber;


/*
https://stackoverflow.com/questions/51601046/should-i-make-asynctask-member-of-livedata-or-repository-class-as-replacement
http://thetechnocafe.com/understanding-livedata-in-android-architecture-components/
https://stackoverflow.com/questions/49405616/cannot-resolve-symbol-viewmodelproviders-on-appcompatactivity
 */
public class QuizizzRepository {



    // Note the use of MutableLiveData, this allows changes to be made


    private static QuizizzRepository instance;
    public static QuizizzRepository getInstance() {
        if (instance == null) {
            synchronized (QuizizzRepository.class) {
                if (instance == null) {
                    instance = new QuizizzRepository();
                }
            }
        }
        return instance;
    }

    // The getter upcasts to LiveData, this ensures that only the repository can cause a change



    public LiveData<List<QuizInfo>> findQuizizzByTerm(final String quizId) {
        final MutableLiveData<List<QuizInfo>> quizizzLiveData = new MutableLiveData<>();

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {

                QuizizzJson quizizzJson = new QuizizzJson();

                // Making a request to url and getting response

                Quizizz qz = quizizzJson.getJsonResponseBySearchTerm(quizId);


                List<QuizInfo> quizzes = qz.getQuizzes();

                Timber.d("json dataobject %s", quizzes.toString());

                //add data to be used
                quizizzLiveData.postValue(quizzes);
            }
        };


        new Thread(runnableTask).start();

        return quizizzLiveData;
    }


    public LiveData<List<QuizInfo>> findQuizizzById(final String quizId) {
        final MutableLiveData<List<QuizInfo>> quizizzLiveData = new MutableLiveData<>();

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {

           /*     QuizizzJson quizizzJson = new QuizizzJson();

                // Making a request to url and getting response

                Quizizz qz = quizizzJson.getJsonResponseById(quizId);


                List<QuizInfo> quizzes = qz.getQuizzes();

                Timber.d("json dataobject %s", quizzes.toString());

                //add data to be used
                quizizzLiveData.postValue(quizzes);*/
            }
        };


        new Thread(runnableTask).start();

        return quizizzLiveData;
    }

}