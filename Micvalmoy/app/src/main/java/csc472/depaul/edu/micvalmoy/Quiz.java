package csc472.depaul.edu.micvalmoy;

import java.lang.*;
import java.util.ArrayList;
import java.util.List;

class Question{
    private Integer id;
    private Integer sortIndex;
    private Boolean enabled = true;
    private String text;
    private String hint;
    private ArrayList topics;
    private ArrayList subject;
    private String createdAt;
    private String updated;
    private String nonce;
    private String type;

    private List<QuestionOption> questionOptions = new ArrayList<>();
    private List<QuestionOption> answers = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public ArrayList getTopics() {
        return topics;
    }

    public void setTopics(ArrayList topics) {
        this.topics = topics;
    }

    public ArrayList getSubject() {
        return subject;
    }

    public void setSubject(ArrayList subject) {
        this.subject = subject;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<QuestionOption> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOption> questionOptions) {
        this.questionOptions = questionOptions;
    }

    public List<QuestionOption> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionOption> answers) {
        this.answers = answers;
    }
}
class QuestionOption{
    Integer id;
    String type;
    String text;
}

public class Quiz {
    private Integer classId;
    private Integer quizId;
    private String name;

    private List<Question> questions = new ArrayList<>();

    /**
     *
     * Return the question that is a specific index in the question list
     * @param index  The index to find in the list
     * @return question
     */
    public Question getQuestion(Integer index) {
        return this.questions.get(index);
    }

    /**
     * Add a question to the list of questions
     * @param q the question to add to the list
     */
    public void setQuestion(Question q) {
        this.questions.add(q);
    }

    /**
     * Get a question from the question list by its unique id number
     * @param id the unique id of the question
     * @return Question
     */
    public Question getQuestionByID(Integer id) {

        for (Question q : questions) {
            if(q.getId().equals(id)){
                return q;
            }
        }
        return null;
    }
    
    public Question getQuestionByNonce(String nonce) {

        for (Question q : questions) {
            if(q.getNonce().equals(nonce)){
                return q;
            }
        }
        return null;
    }



    /**
     * Basic getters /setters
     */
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}