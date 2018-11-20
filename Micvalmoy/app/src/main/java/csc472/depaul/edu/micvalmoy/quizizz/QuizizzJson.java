package csc472.depaul.edu.micvalmoy.quizizz;

/**
 * @author mrichards
 */

//import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
///------------------------------


import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import csc472.depaul.edu.micvalmoy.entity.Category;
import csc472.depaul.edu.micvalmoy.entity.Question;
import csc472.depaul.edu.micvalmoy.entity.QuestionAnswerOption;
import csc472.depaul.edu.micvalmoy.entity.Quiz;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Option;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizInfo;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.QuizizzQuestion;
import csc472.depaul.edu.micvalmoy.tools.Converters;
import csc472.depaul.edu.micvalmoy.tools.HttpHandler;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Quizizz;
import csc472.depaul.edu.micvalmoy.quizizz.jsonObj.Structure;
import timber.log.Timber;

/**
 * https://quizizz.com/api/main/search?query=php
 * https://quizizz.com/quiz/5762b0f0e75c06a70b08c172
 */

public class QuizizzJson {

    private String TAG = QuizizzJson.class.getSimpleName();
    String searchTermUrl = "https://quizizz.com/api/kilim1/search?from=0&sortKey=_score&filterList={%22grade_type.aggs%22%3A[]%2C%22occupation%22%3A[%22teacher_school%22%2C%22teacher_university%22%2C%22other%22%2C%22teacher%22]%2C%22cloned%22%3A[false]%2C%22subjects.aggs%22%3A[]}&queryId=5bc37369f2292f001b141f31-1539574984208&source=MainHeader&page=QuizPage&query=";

    String searchIdUrl ="https://quizizz.com/quiz/";


    public Quizizz parseQuizizzJson(String jsonStr) {

        // DATA as root
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Structure.class, new JsonStructureDeserializer())
                .excludeFieldsWithoutExposeAnnotation().create();

        Gson gson = gsonBuilder.create();
        Quizizz quizizz = gson.fromJson(jsonStr, Quizizz.class);

        Timber.d("---------------------------------------------------------------------------------------------");
        Timber.d("GSON parsed the JSON string");
        Timber.d("Quizizz object Created :\t\t %s" , quizizz);
        Timber.d("---------------------------------------------------------------------------------------------");

        return quizizz;
    }
    public Quizizz getJsonResponseBySearchTerm(String searchParameter) {
        return getJsonResponse (searchParameter,searchTermUrl);
    }
    public Quizizz getJsonResponseById(String searchParameter) {
        return getJsonResponse (searchParameter,searchIdUrl);
    }


    public Quizizz getJsonResponse (String searchParameter, String websiteUrl) {
        Quizizz qz = null;

        String url =  websiteUrl + searchParameter;

        //The Url of the json content, the search parameter is a part of the url


        HttpHandler sh = new HttpHandler();

        //** connect to the quizizz website, and download the json content
        String jsonStr = sh.makeServiceCall(url);


        Timber.d("---------------------------------------------------------------------------------------------");
        Timber.d("Request made to url: %s", url);
        Timber.d("URL Response | JSON string :\t\t %s", jsonStr);
        Timber.d("---------------------------------------------------------------------------------------------");
        if (jsonStr != null) {
            try {
                qz = parseQuizizzJson(jsonStr);
            } catch (Exception e) {
                Timber.e(e,"Json parsing error %s",url);
            }
        } else {
            Timber.d("Couldn't get json from server%s",url);
        }
        return qz;
    }






    public Quiz convertQuizInfoToQuiz(QuizInfo quizInfo) {


        //-------------------------------------------------------------
        /*
        QuizInfo{
            name='PHP QUIZIZZ',
            id='5762b4a2e75c06a70b08cb9c',
            category  = [],
            subjects  = [Other],
            topics    = [],
            subtopics = [],
            questions =[
                QuizizzQuestion {
                    type = 'MCQ',
                        structure = Structure {
                            type = 'null',
                                options = [
                                    Option {
                                        type = 'text',
                                            hasMath = false,
                                            media = [],
                                            text = 'Theft Alerts'
                                    },
                                    Option {
                                        type = 'text',
                                            hasMath = false,
                                            media = [],
                                            text = 'Backup videos and applications'
                                    },
                                    Option {
                                        type = 'text',
                                            hasMath = false,
                                            media = [],
                                            text = 'Wipe and restore'
                                    },
                                    Option {
                                        type = 'text',
                                            hasMath = false,
                                            media = [],
                                            text = 'Anti-virus'
                                    }
                                ],
                                query = Query {
                                    text = 'Lookout Mobile Security does all the following EXCEPT:',
                                        type = 'text',
                                        hasMath = false
                                },
                                answer = [1]
                        },
                        createdAt = '2016-06-16T14:43:07.257Z',
                        id = '5762bafbe75c06a70b08cf76'
                }
            ]
        }

       */

        //-------------------------------------------------------------
        Quiz quiz = new Quiz();
        quiz.setName(quizInfo.getName());
        quiz.setQuizzizz_id(quizInfo.getId());

        //-------------------------------------------------------------



        //-------------------------------------------------------------;
        List<Category> categoryList = new ArrayList<>();

        List<String> catItems;
        catItems = quizInfo.getSubjects();
        if(catItems.size()>0){
            for(String item : catItems){
                Category cat = new Category();
                cat.setName(item);

                categoryList.add(cat);
            }
        }

        catItems = quizInfo.getTopics();
        if(catItems.size()>0){
            for(String item : catItems){
                Category cat = new Category();
                cat.setName(item);
                categoryList.add(cat);
            }
        }

        catItems = quizInfo.getSubtopics();
        if(catItems.size()>0){
            for(String item : catItems){
                Category cat = new Category();
                cat.setName(item);
                categoryList.add(cat);
            }
        }

        //-------------------------------------------------------------





        List<Question> questionList = new ArrayList<>();

        for(QuizizzQuestion quest  :  quizInfo.getQuestions()){

            //Question
            Question question = new Question();
            question.setType(quest.getType());
            question.setEnabled(true);
            question.setNonce(quest.getId());

            //DateFormat UTC : "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            String quest_createdAt      = quest.getCreatedAt();
            OffsetDateTime createdDate  = Converters.toOffsetDateTime(quest_createdAt);
            OffsetDateTime updatedDate  = Instant.now().atOffset( ZoneOffset.UTC );

            question.setCreatedAt(createdDate);
            question.setUpdatedAt(updatedDate);


            Structure quest_structure   = quest.getStructure();
            question.setText(quest_structure.getQuery().getText());

            List<QuestionAnswerOption>   correctAnswers = new ArrayList<>();
            List<QuestionAnswerOption>   choices = new ArrayList<>();

            List<Option>  quest_options  = quest_structure.getOptions();
            List<Integer> quest_answers  = quest_structure.getAnswer();

            Integer index = 0;
            for(Option opt : quest_options){
                QuestionAnswerOption questionOption = new QuestionAnswerOption();

                questionOption.setText(opt.getText());
                questionOption.setAnswer(false);

                if(quest_answers.contains(index)){
                    questionOption.setAnswer(true);
                    correctAnswers.add(questionOption);
                }

                choices.add(questionOption);
                index++;
            }

            question.setOptions(choices);
            question.setCorrectAnswers(correctAnswers);
            questionList.add(question);
        }

        //Quizizz does not have courses
        //quiz.setCourseList();

        quiz.setCategoryList(categoryList);
        quiz.setQuestionList(questionList);


        return quiz;
    }

    /**
     *
     * List<QuizInfo> parseQuizizzJson(jsonStr).getQuizInfoList()
     *
     */
    public List<Quiz> convertQuizInfoListToQuizList(List<QuizInfo> quizInfoList) {

        List<Quiz> quizList = new ArrayList<>();

        if (quizInfoList != null) {
            Quiz quiz = new Quiz();

            for(QuizInfo quizIfo  : quizInfoList){
                quizList.add(convertQuizInfoToQuiz(quizIfo));
            }
        }
        return quizList;
    }
}


