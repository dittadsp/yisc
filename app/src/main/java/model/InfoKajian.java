package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoKajian {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("rows")
    @Expose
    private String rows;
    @SerializedName("maxPages")
    @Expose
    private Integer maxPages;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRows() {
        return rows;
    }

    public void setRows(String rows) {
        this.rows = rows;
    }

    public Integer getMaxPages() {
        return maxPages;
    }

    public void setMaxPages(Integer maxPages) {
        this.maxPages = maxPages;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public static class Datum {

        @SerializedName("ID")
        @Expose
        private String iD;
        @SerializedName("post_title")
        @Expose
        private String postTitle;
        @SerializedName("post_date")
        @Expose
        private String postDate;
        @SerializedName("post_url")
        @Expose
        private String postUrl;
        @SerializedName("post_picture")
        @Expose
        private String postPicture;
        @SerializedName("post_cats")
        @Expose
        private List<PostCat> postCats = null;

        private final String name;

        public Datum( String name,String postTitle, String postDate,String postUrl, String postPicture) {
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

        public String getPostUrl() {
            return postUrl;
        }

        public void setPostUrl(String postUrl) {
            this.postUrl = postUrl;
        }

        public String getPostPicture() {
            return postPicture;
        }

        public void setPostPicture(String postPicture) {
            this.postPicture = postPicture;
        }

        public List<PostCat> getPostCats() {
            return postCats;
        }

        public void setPostCats(List<PostCat> postCats) {
            this.postCats = postCats;
        }

    }
    public class PostCat {

        @SerializedName("term_id")
        @Expose
        private String termId;
        @SerializedName("name")
        @Expose
        private String name;

        public String getTermId() {
            return termId;
        }

        public void setTermId(String termId) {
            this.termId = termId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}
