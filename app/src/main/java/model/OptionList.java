package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionList {

    @SerializedName("option_id")
    @Expose
    private String option_id;

    @SerializedName("question_id")
    @Expose
    private String question_id;

    @SerializedName("option_no")
    @Expose
    private String option_no;

    @SerializedName("option_text")
    @Expose
    private String option_text;

    @SerializedName("option_value")
    @Expose
    private String option_value;
}
