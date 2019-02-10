package model;

public class Score {
    private int score,correct_answer,wrong_answer, total_question;

    public Score(int score, int correct_answer, int wrong_answer, int total_question){
        this.score = score;
        this.correct_answer = correct_answer;
        this.wrong_answer = wrong_answer;
        this.total_question = total_question;
    }

    public int getCorrect_answer() {
        return correct_answer;
    }

    public int getScore() {
        return score;
    }

    public int getWrong_answer() {
        return wrong_answer;
    }

    public int getTotal_question() {
        return total_question;
    }
}
