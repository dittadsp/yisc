package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SubmitModel {
    private String key, user_id,quiz_id;
    private String date_start,date_end;
    ArrayList<Question> questions;
    public SubmitModel(String key, String user_id, String quiz_id, String date_start, String date_end, ArrayList<Question> questions){
        this.key = key;
        this.user_id = user_id;
        this.quiz_id = quiz_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.questions = questions;
    }

    public String getQuiz_id() {
        return quiz_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getKey() {
        return key;
    }

    public String getStart_date() {
        return date_start;
    }

    public String getStart_end() {
        return date_end;
    }

    public void setQuest(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

}
