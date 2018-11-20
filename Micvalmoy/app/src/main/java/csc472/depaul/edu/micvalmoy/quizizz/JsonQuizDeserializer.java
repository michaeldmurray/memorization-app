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

import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Micvalmoy;
import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Data;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Option;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Query;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizizzQuestion;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Structure;
import timber.log.Timber;

// gsonBuilder.registerTypeAdapter(QuizInfo.class, new JsonQuizInfoDeserializer());
public class JsonQuizDeserializer implements JsonDeserializer<Data> {

    Category cat;
    Question quest;
    QuizInfo quizInfo;

    Micvalmoy micvalmoy = new Micvalmoy();

    Data data = new Data();


        //QuizInfo    quizInfo =  new QuizInfo();


        //--------------------------------------------------------


        //Setup Category
        List<Category> categories = new ArrayList<>();

        //Setup Questions
        List<Question> questions = new ArrayList<>();

    @Override
    public Data deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject dataObj = json.getAsJsonObject();

        if (dataObj.has("hits") && dataObj.get("hits").isJsonArray()){

            List<Quiz> quizList = new ArrayList<>();

                JsonArray arr_hits = dataObj.get("hits").getAsJsonArray();
                for (JsonElement item : arr_hits) {
                    quizList.add(customDeserialize(item, typeOfT, context));
                }



        }else if (dataObj.has("quiz") && dataObj.get("quiz").isJsonObject()){
            Quiz quiz  = customDeserialize(json, typeOfT, context);
            data.getMicvalmoy().setQuiz(quiz);
        }

        return data;
    }

        
    public Quiz customDeserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Quiz  quiz = new Quiz();

        //QuizInfo deserializeQuizInfo = context.deserialize(json, QuizInfo.class);

        JsonObject quizInfoObj = json.getAsJsonObject();




        if (quizInfoObj.has("name") && quizInfoObj.get("name").isJsonPrimitive()){
            String name = quizInfoObj.get("name").getAsString();
            quiz.setName("name");
        }

        //micvalmoy.setName(deserializeQuizInfo.getName());
        //micvalmoy.setQuizzes_id(deserializeQuizInfo.getId());






        //--------------------------------------------------------

        //--------------------------------------------------------
        //-Subject -----------------------------------------------
         if (quizInfoObj.has("subject")) {
             JsonArray arr_subject = quizInfoObj.get("subject").getAsJsonArray();
             for (JsonElement item : arr_subject) {
                 cat = new Category();
                 cat.setName(item.getAsString());
                 categories.add(cat);
             }
         }

        //--------------------------------------------------------
        //-topics -----------------------------------------------
        if (quizInfoObj.has("topics")) {
            JsonArray arr_subject = quizInfoObj.get("topics").getAsJsonArray();
            for (JsonElement item : arr_subject) {
                cat = new Category();
                cat.setName(item.getAsString());
                categories.add(cat);
            }
        }

        //--------------------------------------------------------
        //-subtopics -----------------------------------------------
        if (quizInfoObj.has("subtopics")) {
            JsonArray arr_subject = quizInfoObj.get("subtopics").getAsJsonArray();
            for (JsonElement item : arr_subject) {
                cat = new Category();
                cat.setName(item.getAsString());
                categories.add(cat);
            }
        }




        if (quizInfoObj.has("questions")){
            JsonArray arr_questions = quizInfoObj.get("questions").getAsJsonArray();
            String  type;
            String  hint;
            String  nonce;
            String  text;

            for (JsonElement itemQ : arr_questions) {
                quest = new Question();
                JsonObject questionObj = itemQ.getAsJsonObject();


                if (questionObj.has("type") && questionObj.get("type").isJsonPrimitive()){
                    type = questionObj.get("type").getAsString();
                    quest.setType(type);
                }

            }

        }



        //------------------------------------------------------------------
        //------------------------------------------------------------------
        if (quizInfoObj.has("structure")) {
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

        }
        quiz.setQuestionList(questions);
        quiz.setCategoryList(categories);

        return quiz;
    }

}