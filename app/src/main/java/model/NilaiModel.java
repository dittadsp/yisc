package model;

public class NilaiModel {

    String participant_quiz,participant_total,participant_quizid,participant_memberid,participant_startdate,participant_enddate;
    String participant_score,participant_name;
    public NilaiModel(String participant_quiz,String participant_name,String participant_start_date,String participant_end_date,String quizid,String memberid,String score){
        this.participant_quiz = participant_quiz;
        this.participant_name = participant_name;
        this.participant_startdate = participant_start_date;
        this.participant_enddate = participant_end_date;
        this.participant_quizid = quizid;
        this.participant_memberid = memberid;
        this.participant_score = score;
    }

    public String getParticipant_quiz() {
        return participant_quiz;
    }

    public String getParticipant_name() {
        return participant_name;
    }

    public String getParticipant_startdate() {
        return participant_startdate;
    }

    public String getParticipant_enddate() {
        return participant_enddate;
    }

    public String getParticipant_quizid() {
        return participant_quizid;
    }

    public String getParticipant_memberid() {
        return participant_memberid;
    }

    public String getParticipant_score() {
        return participant_score;
    }
}

