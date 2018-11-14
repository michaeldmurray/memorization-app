package csc472.depaul.edu.micvalmoy.quizizz;

//import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
///------------------------------
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.tools.HttpHandler;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Option;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Query;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Quizizz;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Structure;
import timber.log.Timber;

class StructureDeserializer implements JsonDeserializer<Structure> {

    @Override
    public Structure deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject structureObj = json.getAsJsonObject();


        Structure struct =  new Structure();
        Timber.d("running Structure deserializer");

        // Delegate the deserialization to the context
        Query query = context.deserialize(structureObj.get("query"), Query.class);

        List<Integer> answerList = new ArrayList<>();
        List<Option> options = new ArrayList<Option>();

        //JsonObject structureObj = json.getAsJsonObject()
        if (structureObj.has("answer")) {
            if(structureObj.get("answer").isJsonPrimitive()){
                Integer ans = structureObj.get("answer").getAsInt();
                answerList.add(ans);

                Timber.d("Json -  \"answer\" with INTEGER type found");
            }else if (structureObj.get("answer").isJsonArray()){
                Timber.d("Json - \"answer\" with_ARRAY type found $s" , structureObj.toString());

                JsonArray arr_ans = structureObj.get("answer").getAsJsonArray();
                for (JsonElement anArr : arr_ans) {
                    Integer ans =  anArr.getAsInt();
                    answerList.add(ans);
                }
            }
        }else{
            Timber.d("Json - no answer found %s" , structureObj.toString());
        }

        if (structureObj.has("options")) {
            if(structureObj.get("options").isJsonPrimitive()){

                Timber.d("Json -  \"options\" not in the right format %s", structureObj.toString());
            }

            if (structureObj.get("options").isJsonArray()) {
                JsonArray arr_options = structureObj.get("options").getAsJsonArray();
                for (JsonElement optElem : arr_options) {
                    Option option =  context.deserialize(optElem, Option.class);
                    options.add(option);
                }
            }
        }
        struct.setAnswer(answerList);
        struct.setOptions(options);
        struct.setQuery(query);

        return struct;
    }


}

public class QuizizzJson {

    private String TAG = QuizizzJson.class.getSimpleName();

    public Quizizz parseQuizizzJson(String jsonStr) {

        // DATA as root
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Structure.class, new StructureDeserializer());

        Gson gson = gsonBuilder.create();
        Quizizz quizizz = gson.fromJson(jsonStr, Quizizz.class);

        Timber.d("Quizziz GSON parsed java object: %s" , quizizz.toString());

        return quizizz;
    }

    public Quizizz getJsonResponse (String searchParameter) {
        Quizizz qz = null;

        //The Url of the json content, the search parameter is a part of the url
        String url = "https://quizizz.com/api/kilim1/search?from=0&sortKey=_score&filterList={%22grade_type.aggs%22%3A[]%2C%22occupation%22%3A[%22teacher_school%22%2C%22teacher_university%22%2C%22other%22%2C%22teacher%22]%2C%22cloned%22%3A[false]%2C%22subjects.aggs%22%3A[]}&queryId=5bc37369f2292f001b141f31-1539574984208&source=MainHeader&page=QuizPage&query=" + searchParameter;


        HttpHandler sh = new HttpHandler();

        //** connect to the quizizz website, and download the json content
        String jsonStr = sh.makeServiceCall(url);

        Timber.d("Response from url: %s", jsonStr);
        if (jsonStr != null) {
            try {
                qz = parseQuizizzJson(jsonStr);
            } catch (Exception e) {
                Timber.e(e,"Json parsing error");
            }
        } else {
            Timber.d("Couldn't get json from server.");
        }
        return qz;
    }
}


