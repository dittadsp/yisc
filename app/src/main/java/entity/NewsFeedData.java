package entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import model.Category;

public class NewsFeedData {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("post_title")
    @Expose
    private String postTitle;
    @SerializedName("post_date")
    @Expose
    private String postDate;
    @SerializedName("post_picture")
    @Expose
    private Object postPicture;
    @SerializedName("post_url")
    @Expose
    private String postUrl;

    @SerializedName("post_cats")
    @Expose
    private List<NewsFeedCategory> post_cats = null;
    private String name;

    public NewsFeedData( String name,String postTitle, String postDate,String postUrl, String postPicture) {
        this.name = name;
        this.postTitle = postTitle;
        this.postDate = postDate;
        this.postUrl = postUrl;
        this.postPicture = postPicture;

    }

    public String getID() {
        return iD;
    }

    public String getName() {
        return name;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public Object getPostPicture() {
        return postPicture;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostPicture(Object postPicture) {
        this.postPicture = postPicture;
    }

    public List<NewsFeedCategory> getPost_cats() {
        return post_cats;
    }

    public void setPost_cats(List<NewsFeedCategory> post_cats) {
        this.post_cats = post_cats;
    }
}
