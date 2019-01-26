package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionList {

    @SerializedName("option_id")
    @Expose
    private String optionId;
    @SerializedName("question_id")
    @Expose
    private String questionId;
    @SerializedName("option_no")
    @Expose
    private String optionNo;
    @SerializedName("option_text")
    @Expose
    private String optionText;
    @SerializedName("option_value")
    @Expose
    private String optionValue;

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getOptionNo() {
        return optionNo;
    }

    public void setOptionNo(String optionNo) {
        this.optionNo = optionNo;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
}
