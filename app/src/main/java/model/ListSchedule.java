package model;

public class ListSchedule {

    public boolean status;
    public String message;
    public ListScheduleChild data;

    public static class ListScheduleChild {

        private String id, tema, tanggal, waktu, tempat, nama, semester, tipe;


        public void setId(String id) {
            this.id = id;
        }

        public void setTema(String tema) {
            this.tema = tema;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public void setWaktu(String waktu) {
            this.waktu = waktu;
        }

        public void setTempat(String tempat) {
            this.tempat = tempat;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public void setSemester(String semester) {
            this.semester = semester;
        }

        public void setTipe(String tipe) {
            this.tipe = tipe;
        }

        public String getTema() {
            return tema;
        }

        public String getTanggal() {
            return tanggal;
        }

        public String getWaktu() {
            return waktu;
        }

        public String getNama() {
            return nama;
        }

        public String getSemester() {
            return semester;
        }

        public String getTipe() {
            return tipe;
        }

        public String getId() {
            return id;
        }

        public String getTempat() {
            return tempat;
        }
    }
}
