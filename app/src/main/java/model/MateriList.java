package model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateriList {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("tema")
    @Expose
    private String tema;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("resume")
    @Expose
    private String resume;
    @SerializedName("video")
    @Expose
    private String video;
    @SerializedName("file")
    @Expose
    private String file;


    public void setId(String id) {
        this.id = id;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getId() {
        return id;
    }

    public String getTema() {
        return tema;
    }

    public String getCategory() {
        return category;
    }

    public String getResume() {
        return resume;
    }

    public String getFile() {
        return file;
    }

    public String getVideo() {
        return video;
    }
}

