package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Kajian {
    String VideoName;
    String VideoDesc;
    String URL;
    String VideoId;

    public void setVideoName(String VideoName){
        this.VideoName=VideoName;
    }

    public String getVideoName(){
        return VideoName;
    }

    public void setVideoDesc(String VideoDesc){
        this.VideoDesc=VideoDesc;
    }

    public String getVideoDesc(){
        return VideoDesc;
    }

    public void setURL(String URL){
        this.URL=URL;
    }

    public String getURL(){
        return URL;
    }

    public void setVideoId(String VideoId){
        this.VideoId=VideoId;
    }
    public String getVideoId(){
        return VideoId;
    }

}