package csc472.depaul.edu.micvalmoy.quizizz;

//import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
///------------------------------


import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.tools.HttpHandler;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Option;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Query;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Quizizz;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Structure;
import timber.log.Timber;


public class QuizizzJson {

    private String TAG = QuizizzJson.class.getSimpleName();
    String searchTermUrl = "https://quizizz.com/api/kilim1/search?from=0&sortKey=_score&filterList={%22grade_type.aggs%22%3A[]%2C%22occupation%22%3A[%22teacher_school%22%2C%22teacher_university%22%2C%22other%22%2C%22teacher%22]%2C%22cloned%22%3A" +
            "[false]%2C%22subjects.aggs%22%3A[]}&queryId=5bc37369f2292f001b141f31-1539574984208&source=MainHeader&page=QuizPage&query=";

    String searchIdUrl ="https://quizizz.com/quiz/";


    public Quizizz parseQuizizzJson(String jsonStr) {

        // DATA as root
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Structure.class, new JsonStructureDeserializer());

        Gson gson = gsonBuilder.create();
        Quizizz quizizz = gson.fromJson(jsonStr, Quizizz.class);

        Timber.d("Quizziz GSON parsed java object: %s" , quizizz.toString());

        return quizizz;
    }
    public Quizizz getJsonResponseBySearchTerm(String searchParameter) {
        Quizizz qz = null;

        String url =  searchTermUrl + searchParameter;

        //The Url of the json content, the search parameter is a part of the url


        HttpHandler sh = new HttpHandler();

        //** connect to the quizizz website, and download the json content
        String jsonStr = sh.makeServiceCall(url);

        Timber.d("Response from url: %s", jsonStr);
        if (jsonStr != null) {
            try {
                // DATA as root
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Structure.class, new JsonStructureDeserializer());

                Gson gson = gsonBuilder.create();
                qz  = gson.fromJson(jsonStr, Quizizz.class);

                Timber.d("Quizziz GSON parsed java object: %s" , qz.toString());

            } catch (Exception e) {
                Timber.e(e,"Json parsing error");
            }
        } else {
            Timber.d("Couldn't get json from server.");
        }
        return qz;
    }




    public Quizizz getJsonResponseById(String searchParameter) {

        Quizizz qz = null;

        String url =  searchIdUrl + searchParameter;

        //The Url of the json content, the search parameter is a part of the url


        HttpHandler sh = new HttpHandler();

        //** connect to the quizizz website, and download the json content
        String jsonStr = sh.makeServiceCall(url);

        Timber.d("Response from url: %s", jsonStr);
        if (jsonStr != null) {
            try {
                // DATA as root
                GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(Structure.class, new JsonStructureDeserializer());

             /*   Gson gson = gsonBuilder.create();
                qz  = gson.fromJson(jsonStr, QuizInfo.class);

                Timber.d("Quizziz GSON parsed java object: %s" , qz.toString());
*/
            } catch (Exception e) {
                Timber.e(e,"Json parsing error");
            }
        } else {
            Timber.d("Couldn't get json from server.");
        }
        return qz;
    }
}


