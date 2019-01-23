package model;

import java.util.List;

public class Quiz_SubmitModel {

    private Boolean status;

    private List<Quiz_SubmitData> data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setData(List<Quiz_SubmitData> data) {
        this.data = data;
    }

    public List<Quiz_SubmitData> getData() {
        return data;
    }
}
