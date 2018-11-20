package csc472.depaul.edu.micvalmoy.quizizz;

/**
 * @author mrichards
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Option;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Query;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Structure;
import timber.log.Timber;

public class JsonStructureDeserializer implements JsonDeserializer<Structure> {

    @Override
    public Structure deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject structureObj = json.getAsJsonObject();


        Structure struct =  new Structure();
        Timber.d("running Structure deserializer");

        // Delegate the deserialization to the context
        Query query = context.deserialize(structureObj.get("query"), Query.class);

        List<Integer> answerList = new ArrayList<>();
        List<Option> options = new ArrayList<Option>();


        if (structureObj.has("answer")) {
            if(structureObj.get("answer").isJsonPrimitive()){
                Integer ans = structureObj.get("answer").getAsInt();
                answerList.add(ans);

                Timber.d("Json -  \"answer\" with INTEGER type found");
            }else if (structureObj.get("answer").isJsonArray()){
                Timber.d("Json - \"answer\" with_ARRAY type found %s", structureObj.toString());

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