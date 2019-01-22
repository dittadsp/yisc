package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLogin {

    private String key,  email,  password;
    public void setKey(String key) {
        this.key = key;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
