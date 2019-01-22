package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsFeedCategory {
    @SerializedName("term_id")
    @Expose
    private String termId;
    @SerializedName("name")
    @Expose
    private String name;

    public String getTerm_id() {
        return termId;
    }

    public void setTerm_id(String term_id) {
        this.termId = term_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
