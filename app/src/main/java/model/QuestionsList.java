package model;

import java.util.List;

public class QuestionsList {

    private String quiz_id;
    private String quiz_title;
    private String quiz_desc;
    private String question_text;
    private String question_id;
    private String question_type;
    private String quiz_time;
    private List<OptionList> options = null;

    public QuestionsList(String question_id,String question_text, String quiz_time, List<OptionList> options) {

        this.question_id = question_id;
        this.question_text = question_text;
        this.quiz_time = quiz_time;
        this.options = options;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public String getQuiz_title() {
        return quiz_title;
    }

    public String getQuiz_desc() {
        return quiz_desc;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public String getQuiz_time() {
        return quiz_time;
    }

    public List<OptionList> getOptions() {
        return options;
    }
}
