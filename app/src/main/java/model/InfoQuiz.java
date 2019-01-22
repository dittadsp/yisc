package model;

public class InfoQuiz {

    String quiz_id, quiz_title, quiz_desc,  quiz_start_date, quiz_end_date,quiz_status;

    public InfoQuiz(String quiz_id, String quiz_title, String quiz_desc, String quiz_start_date, String quiz_end_date, String quiz_status){
        this.quiz_id = quiz_id;
        this.quiz_title = quiz_title;
        this.quiz_desc = quiz_desc;
        this.quiz_start_date = quiz_start_date;
        this.quiz_end_date = quiz_end_date;
        this.quiz_status = quiz_status;
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

    public String getQuiz_start_date() {
        return quiz_start_date;
    }

    public String getQuiz_end_date() {
        return quiz_end_date;
    }

    public String getQuiz_status() {
        return quiz_status;
    }
}
