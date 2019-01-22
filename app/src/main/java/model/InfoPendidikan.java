package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoPendidikan {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<InfoJadwal> dataJadwal = null;
    private List<MateriList> dataMateri = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<InfoJadwal> getDataJadwal() {
        return dataJadwal;
    }

    public void setData(List<InfoJadwal> dataJadwal) {
        this.dataJadwal = dataJadwal;
    }

    public void setDataMateri(List<MateriList> dataMateri) {
        this.dataMateri = dataMateri;
    }

    public List<MateriList> getDataMateri() {
        return dataMateri;
    }
}
