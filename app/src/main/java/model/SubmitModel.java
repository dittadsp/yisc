package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SubmitModel {
    private String key, user_id,quiz_id;
    private String date_start,date_end;
    Question question;
    public SubmitModel(String key, String user_id, String quiz_id, String date_start, String date_end, Question question){
        this.key = key;
        this.user_id = user_id;
        this.quiz_id = quiz_id;
        this.date_start = date_start;
        this.date_end = date_end;
        this.question = question;
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

    public void setQuest(Question quest) {
        this.question = quest;
    }

    public Question getQuest() {
        return question;
    }

    //    @SerializedName("status")
//    @Expose
//    private Boolean status;
//    @SerializedName("message")
//    @Expose
//    private String message;
//    @SerializedName("data")
//    @Expose
//    private List<SubmitList> data = null;
//
//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean status) {
//        this.status = status;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public List<SubmitList> getData() { return data;}
//
//    public void setData(List<SubmitList> data) {
//        this.data = data;
//    }
}
