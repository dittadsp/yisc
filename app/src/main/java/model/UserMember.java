package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserMember {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    public ListMember data;

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

    public ListMember getData() {
        return data;
    }

    public void setData(ListMember data) {
        this.data = data;
    }
    public class ListMember {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("member_no")
        @Expose
        private String memberNo;
        @SerializedName("member_name")
        @Expose
        private String memberName;
        @SerializedName("member_nickname")
        @Expose
        private Object memberNickname;
        @SerializedName("member_gender")
        @Expose
        private Object memberGender;
        @SerializedName("member_blood")
        @Expose
        private Object memberBlood;
        @SerializedName("member_pob")
        @Expose
        private Object memberPob;
        @SerializedName("member_dob")
        @Expose
        private Object memberDob;
        @SerializedName("member_identity_type")
        @Expose
        private Object memberIdentityType;
        @SerializedName("member_identity_number")
        @Expose
        private Object memberIdentityNumber;
        @SerializedName("member_alamat")
        @Expose
        private Object memberAlamat;
        @SerializedName("member_poscode")
        @Expose
        private Object memberPoscode;
        @SerializedName("member_telp")
        @Expose
        private Object memberTelp;
        @SerializedName("member_hp")
        @Expose
        private String memberHp;
        @SerializedName("member_wa")
        @Expose
        private Object memberWa;
        @SerializedName("member_pinbb")
        @Expose
        private Object memberPinbb;
        @SerializedName("member_job")
        @Expose
        private Object memberJob;
        @SerializedName("member_jabatan")
        @Expose
        private Object memberJabatan;
        @SerializedName("member_office_address")
        @Expose
        private Object memberOfficeAddress;
        @SerializedName("member_office_telp")
        @Expose
        private Object memberOfficeTelp;
        @SerializedName("member_hoby")
        @Expose
        private Object memberHoby;
        @SerializedName("member_angkatan")
        @Expose
        private String memberAngkatan;
        @SerializedName("member_stsrumah")
        @Expose
        private Object memberStsrumah;
        @SerializedName("member_kantor")
        @Expose
        private Object memberKantor;
        @SerializedName("member_pict")
        @Expose
        private Object memberPict;
        @SerializedName("member_identity_pict")
        @Expose
        private Object memberIdentityPict;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("member_status")
        @Expose
        private String memberStatus;
        @SerializedName("member_pic2")
        @Expose
        private Object memberPic2;
        @SerializedName("member_pic3")
        @Expose
        private Object memberPic3;
        @SerializedName("member_check")
        @Expose
        private String memberCheck;
        @SerializedName("member_kota")
        @Expose
        private Object memberKota;
        @SerializedName("member_kelas")
        @Expose
        private Object memberKelas;
        @SerializedName("member_email")
        @Expose
        private String memberEmail;
        @SerializedName("member_domisili")
        @Expose
        private Object memberDomisili;
        @SerializedName("bahasa")
        @Expose
        private Object bahasa;
        @SerializedName("keahlian")
        @Expose
        private Object keahlian;
        @SerializedName("kegiatan")
        @Expose
        private Object kegiatan;
        @SerializedName("organisasi")
        @Expose
        private Object organisasi;
        @SerializedName("pendidikan")
        @Expose
        private Object pendidikan;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemberNo() {
            return memberNo;
        }

        public void setMemberNo(String memberNo) {
            this.memberNo = memberNo;
        }

        public String getMemberName() {
            return memberName;
        }

        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }

        public Object getMemberNickname() {
            return memberNickname;
        }

        public void setMemberNickname(Object memberNickname) {
            this.memberNickname = memberNickname;
        }

        public Object getMemberGender() {
            return memberGender;
        }

        public void setMemberGender(Object memberGender) {
            this.memberGender = memberGender;
        }

        public Object getMemberBlood() {
            return memberBlood;
        }

        public void setMemberBlood(Object memberBlood) {
            this.memberBlood = memberBlood;
        }

        public Object getMemberPob() {
            return memberPob;
        }

        public void setMemberPob(Object memberPob) {
            this.memberPob = memberPob;
        }

        public Object getMemberDob() {
            return memberDob;
        }

        public void setMemberDob(Object memberDob) {
            this.memberDob = memberDob;
        }

        public Object getMemberIdentityType() {
            return memberIdentityType;
        }

        public void setMemberIdentityType(Object memberIdentityType) {
            this.memberIdentityType = memberIdentityType;
        }

        public Object getMemberIdentityNumber() {
            return memberIdentityNumber;
        }

        public void setMemberIdentityNumber(Object memberIdentityNumber) {
            this.memberIdentityNumber = memberIdentityNumber;
        }

        public Object getMemberAlamat() {
            return memberAlamat;
        }

        public void setMemberAlamat(Object memberAlamat) {
            this.memberAlamat = memberAlamat;
        }

        public Object getMemberPoscode() {
            return memberPoscode;
        }

        public void setMemberPoscode(Object memberPoscode) {
            this.memberPoscode = memberPoscode;
        }

        public Object getMemberTelp() {
            return memberTelp;
        }

        public void setMemberTelp(Object memberTelp) {
            this.memberTelp = memberTelp;
        }

        public String getMemberHp() {
            return memberHp;
        }

        public void setMemberHp(String memberHp) {
            this.memberHp = memberHp;
        }

        public Object getMemberWa() {
            return memberWa;
        }

        public void setMemberWa(Object memberWa) {
            this.memberWa = memberWa;
        }

        public Object getMemberPinbb() {
            return memberPinbb;
        }

        public void setMemberPinbb(Object memberPinbb) {
            this.memberPinbb = memberPinbb;
        }

        public Object getMemberJob() {
            return memberJob;
        }

        public void setMemberJob(Object memberJob) {
            this.memberJob = memberJob;
        }

        public Object getMemberJabatan() {
            return memberJabatan;
        }

        public void setMemberJabatan(Object memberJabatan) {
            this.memberJabatan = memberJabatan;
        }

        public Object getMemberOfficeAddress() {
            return memberOfficeAddress;
        }

        public void setMemberOfficeAddress(Object memberOfficeAddress) {
            this.memberOfficeAddress = memberOfficeAddress;
        }

        public Object getMemberOfficeTelp() {
            return memberOfficeTelp;
        }

        public void setMemberOfficeTelp(Object memberOfficeTelp) {
            this.memberOfficeTelp = memberOfficeTelp;
        }

        public Object getMemberHoby() {
            return memberHoby;
        }

        public void setMemberHoby(Object memberHoby) {
            this.memberHoby = memberHoby;
        }

        public String getMemberAngkatan() {
            return memberAngkatan;
        }

        public void setMemberAngkatan(String memberAngkatan) {
            this.memberAngkatan = memberAngkatan;
        }

        public Object getMemberStsrumah() {
            return memberStsrumah;
        }

        public void setMemberStsrumah(Object memberStsrumah) {
            this.memberStsrumah = memberStsrumah;
        }

        public Object getMemberKantor() {
            return memberKantor;
        }

        public void setMemberKantor(Object memberKantor) {
            this.memberKantor = memberKantor;
        }

        public Object getMemberPict() {
            return memberPict;
        }

        public void setMemberPict(Object memberPict) {
            this.memberPict = memberPict;
        }

        public Object getMemberIdentityPict() {
            return memberIdentityPict;
        }

        public void setMemberIdentityPict(Object memberIdentityPict) {
            this.memberIdentityPict = memberIdentityPict;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMemberStatus() {
            return memberStatus;
        }

        public void setMemberStatus(String memberStatus) {
            this.memberStatus = memberStatus;
        }

        public Object getMemberPic2() {
            return memberPic2;
        }

        public void setMemberPic2(Object memberPic2) {
            this.memberPic2 = memberPic2;
        }

        public Object getMemberPic3() {
            return memberPic3;
        }

        public void setMemberPic3(Object memberPic3) {
            this.memberPic3 = memberPic3;
        }

        public String getMemberCheck() {
            return memberCheck;
        }

        public void setMemberCheck(String memberCheck) {
            this.memberCheck = memberCheck;
        }

        public Object getMemberKota() {
            return memberKota;
        }

        public void setMemberKota(Object memberKota) {
            this.memberKota = memberKota;
        }

        public Object getMemberKelas() {
            return memberKelas;
        }

        public void setMemberKelas(Object memberKelas) {
            this.memberKelas = memberKelas;
        }

        public String getMemberEmail() {
            return memberEmail;
        }

        public void setMemberEmail(String memberEmail) {
            this.memberEmail = memberEmail;
        }

        public Object getMemberDomisili() {
            return memberDomisili;
        }

        public void setMemberDomisili(Object memberDomisili) {
            this.memberDomisili = memberDomisili;
        }

        public Object getBahasa() {
            return bahasa;
        }

        public void setBahasa(Object bahasa) {
            this.bahasa = bahasa;
        }

        public Object getKeahlian() {
            return keahlian;
        }

        public void setKeahlian(Object keahlian) {
            this.keahlian = keahlian;
        }

        public Object getKegiatan() {
            return kegiatan;
        }

        public void setKegiatan(Object kegiatan) {
            this.kegiatan = kegiatan;
        }

        public Object getOrganisasi() {
            return organisasi;
        }

        public void setOrganisasi(Object organisasi) {
            this.organisasi = organisasi;
        }

        public Object getPendidikan() {
            return pendidikan;
        }

        public void setPendidikan(Object pendidikan) {
            this.pendidikan = pendidikan;
        }
    }
}