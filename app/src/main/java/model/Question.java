package model;

import java.util.List;

public class Question {

    private String question_id, question_answer;

    public Question(String question_id, String question_answer){
        this.question_id = question_id;
        this.question_answer = question_answer;
    }
    public void setQuestion_id(String question_id) {
        this.question_id = question_id;
    }

    public void setQuestion_answer(String question_answer) {
        this.question_answer = question_answer;
    }

    public String getQuestion_id() {
        return question_id;
    }

    public String getQuestion_answer() {
        return question_answer;
    }
}
