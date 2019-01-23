package model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateriList {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<DatumMateri> data = null;

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

    public List<DatumMateri> getData() {
        return data;
    }

    public void setData(List<DatumMateri> data) {
        this.data = data;
    }

    public static class DatumMateri {
        public DatumMateri( String id,String tema, String category,String semester, String resume, String video, String file) {
            this.id = id;
            this.tema= tema;
            this.category= category;
            this.semester= semester;
            this.resume = resume;
            this.video = video;
            this.file = file;
        }
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("tema")
        @Expose
        private String tema;
        @SerializedName("category")
        @Expose
        private String category;
        @SerializedName("semester")
        @Expose
        private String semester;
        @SerializedName("resume")
        @Expose
        private String resume;
        @SerializedName("video")
        @Expose
        private Object video;
        @SerializedName("file")
        @Expose
        private Object file;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTema() {
            return tema;
        }

        public void setTema(String tema) {
            this.tema = tema;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSemester() {
            return semester;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public String getResume() {
            return resume;
        }

        public void setResume(String resume) {
            this.resume = resume;
        }

        public Object getVideo() {
            return video;
        }

        public void setVideo(Object video) {
            this.video = video;
        }

        public Object getFile() {
            return file;
        }

        public void setFile(Object file) {
            this.file = file;
        }
    }
}

