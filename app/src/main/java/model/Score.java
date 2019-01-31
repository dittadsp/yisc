package model;

public class Score {
    private String score,correct_answer,wrong_answer, total_question;

    public Score(String score, String correct_answer, String wrong_answer, String total_question){
        this.score = score;
        this.correct_answer = correct_answer;
        this.wrong_answer = wrong_answer;
        this.total_question = total_question;
    }

    public String getScore() {
        return score;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getWrong_answer() {
        return wrong_answer;
    }

    public String getTotal_question() {
        return total_question;
    }
}
