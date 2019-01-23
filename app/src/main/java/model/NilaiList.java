package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NilaiList {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public static class Datum {
        public Datum(String participantQuiz, String participantTotal, String id, String participantQuizid, String participantMemberid,
                     String participantStartdate, String participantEnddate, String participantScore, String participantName,
                     String participantHp, String participantEmail, String createdAt, String updatedAt) {
            this.participantQuiz = participantQuiz;
            this.participantTotal = participantTotal;
            this.id = id;
            this.participantQuizid = participantQuizid;
            this.participantMemberid = participantMemberid;
            this.participantStartdate = participantStartdate;
            this.participantEnddate = participantEnddate;
            this.participantScore = participantScore;
            this.participantName = participantName;
            this.participantHp = participantHp;
            this.participantEmail = participantEmail;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }

        @SerializedName("participant_quiz")
        @Expose
        private String participantQuiz;
        @SerializedName("participant_total")
        @Expose
        private String participantTotal;
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("participant_quizid")
        @Expose
        private String participantQuizid;
        @SerializedName("participant_memberid")
        @Expose
        private String participantMemberid;
        @SerializedName("participant_startdate")
        @Expose
        private String participantStartdate;
        @SerializedName("participant_enddate")
        @Expose
        private String participantEnddate;
        @SerializedName("participant_score")
        @Expose
        private String participantScore;
        @SerializedName("participant_name")
        @Expose
        private String participantName;
        @SerializedName("participant_hp")
        @Expose
        private String participantHp;
        @SerializedName("participant_email")
        @Expose
        private String participantEmail;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public String getParticipantQuiz() {
            return participantQuiz;
        }

        public void setParticipantQuiz(String participantQuiz) {
            this.participantQuiz = participantQuiz;
        }

        public String getParticipantTotal() {
            return participantTotal;
        }

        public void setParticipantTotal(String participantTotal) {
            this.participantTotal = participantTotal;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParticipantQuizid() {
            return participantQuizid;
        }

        public void setParticipantQuizid(String participantQuizid) {
            this.participantQuizid = participantQuizid;
        }

        public String getParticipantMemberid() {
            return participantMemberid;
        }

        public void setParticipantMemberid(String participantMemberid) {
            this.participantMemberid = participantMemberid;
        }

        public String getParticipantStartdate() {
            return participantStartdate;
        }

        public void setParticipantStartdate(String participantStartdate) {
            this.participantStartdate = participantStartdate;
        }

        public String getParticipantEnddate() {
            return participantEnddate;
        }

        public void setParticipantEnddate(String participantEnddate) {
            this.participantEnddate = participantEnddate;
        }

        public String getParticipantScore() {
            return participantScore;
        }

        public void setParticipantScore(String participantScore) {
            this.participantScore = participantScore;
        }

        public String getParticipantName() {
            return participantName;
        }

        public void setParticipantName(String participantName) {
            this.participantName = participantName;
        }

        public String getParticipantHp() {
            return participantHp;
        }

        public void setParticipantHp(String participantHp) {
            this.participantHp = participantHp;
        }

        public String getParticipantEmail() {
            return participantEmail;
        }

        public void setParticipantEmail(String participantEmail) {
            this.participantEmail = participantEmail;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}
