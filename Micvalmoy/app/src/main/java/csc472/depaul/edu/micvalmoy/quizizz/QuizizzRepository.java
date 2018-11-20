package csc472.depaul.edu.micvalmoy.quizizz;

/**
 * @author mrichards
 */

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Quizizz;
import timber.log.Timber;

/**
 * @author mrichards
 */

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



    public LiveData<List<QuizInfo>> findQuizizzByTerm(final String searchTerm) {
        final MutableLiveData<List<QuizInfo>> quizizzLiveData = new MutableLiveData<>();

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {

                QuizizzJson quizizzJson = new QuizizzJson();

                // Making a request to url and getting response

                Quizizz quizizz = quizizzJson.getJsonResponseBySearchTerm(searchTerm);


                List<QuizInfo> quizInfoList = quizizz.getQuizInfoList();

                List<Quiz> quizList = quizizzJson.convertQuizInfoListToQuizList(quizInfoList);

                Timber.d("---------------------------------------------------------------------------------------------");
                Timber.d("findQuizizzByTerm() got Quizizz object from JSON :\t\t %s", quizizz);
                Timber.d("quizizz.getQuizInfoList()  :\t\t %s", quizInfoList);
                Timber.d("\"----------Quiz List :------------:\t\t %s", quizList);
                Timber.d("---------------------------------------------------------------------------------------------");


                //add data to be used
                quizizzLiveData.postValue(quizInfoList);
            }
        };


        new Thread(runnableTask).start();

        return quizizzLiveData;
    }

    /**
     * Get a single quiz from the quizzizz.com website
     * @param quizzizz_id
     * @return
     */
    public LiveData<QuizInfo> findQuizizzById(final String quizzizz_id) {
        final MutableLiveData<QuizInfo> quizizzLiveData = new MutableLiveData<>();

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {

                QuizizzJson quizizzJson = new QuizizzJson();

                // Making a request to url and getting response

                Quizizz quizizz = quizizzJson.getJsonResponseById(quizzizz_id);

                QuizInfo quizInfo = quizizz.getQuizInfo();
                Quiz quiz = quizizzJson.convertQuizInfoToQuiz(quizInfo);


                Timber.d("---------------------------------------------------------------------------------------------");
                Timber.d("findQuizizzById() got Quizizz object from JSON :\t\t %s", quizizz);
                Timber.d("quizizz.getQuizInfo()  :\t\t %s", quizInfo);
                Timber.d("----------Quiz:------------\t\t %s", quiz);
                Timber.d("---------------------------------------------------------------------------------------------");



                //add data to be used
                quizizzLiveData.postValue(quizInfo);
            }
        };

        new Thread(runnableTask).start();

        return quizizzLiveData;
    }




    //--------------------------------------------------------------------------------------
    public LiveData<List<Quiz>> getQuizListFromQuizizzByTerm(final String searchTerm) {
        final MutableLiveData<List<Quiz>> quizLiveData = new MutableLiveData<>();

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {

                QuizizzJson quizizzJson = new QuizizzJson();

                // Making a request to url and getting response

                Quizizz quizizz = quizizzJson.getJsonResponseBySearchTerm(searchTerm);


                List<QuizInfo> quizInfoList = quizizz.getQuizInfoList();

                List<Quiz> quizList = quizizzJson.convertQuizInfoListToQuizList(quizInfoList);

                Timber.d("---------------------------------------------------------------------------------------------");
                Timber.d("findQuizizzByTerm() got Quizizz object from JSON :\t\t %s", quizizz);
                Timber.d("quizizz.getQuizInfoList()  :\t\t %s", quizInfoList);
                Timber.d("\"----------Quiz List :------------:\t\t %s", quizList);
                Timber.d("---------------------------------------------------------------------------------------------");


                //add data to be used
                quizLiveData.postValue(quizList);
            }
        };


        new Thread(runnableTask).start();

        return quizLiveData;
    }


    /**
     * Get data from quizzizz website, return the same format that can be saved in our database
     * @param quizzizz_id
     * @return
     */
    public LiveData<Quiz> getQuizListFromQuizizzById(final String quizzizz_id) {
        final MutableLiveData<Quiz> quizLiveData = new MutableLiveData<>();

        Runnable runnableTask = new Runnable() {
            @Override
            public void run() {

                QuizizzJson quizizzJson = new QuizizzJson();

                // Making a request to url and getting response

                Quizizz quizizz = quizizzJson.getJsonResponseById(quizzizz_id);

                QuizInfo quizInfo = quizizz.getQuizInfo();



                Quiz quiz = quizizzJson.convertQuizInfoToQuiz(quizInfo);


                Timber.d("---------------------------------------------------------------------------------------------");
                Timber.d("findQuizizzById() got Quizizz object from JSON :\t\t %s", quizizz);
                Timber.d("quizizz.getQuizInfo()  :\t\t %s", quizInfo);
                Timber.d("----------Quiz:------------\t\t %s", quiz);
                Timber.d("---------------------------------------------------------------------------------------------");



                //add data to be used
                quizLiveData.postValue(quiz);
            }
        };

        new Thread(runnableTask).start();

        return quizLiveData;
    }



}