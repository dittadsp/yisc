package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserList {

   public boolean status;
   public UserListChild data;

    public static class UserListChild {

        private String id, ip_address, username, password, salt, email, activation_code, forgotten_password_code, forgotten_password_time,
                remember_code, created_on, last_login, active, first_name, last_name, company, phone, user_id;


        public void setId(String id) {
            this.id = id;
        }

        public void setIp_address(String ip_address) {
            this.ip_address = ip_address;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setActivation_code(String activation_code) {
            this.activation_code = activation_code;
        }

        public void setForgotten_password_code(String forgotten_password_code) {
            this.forgotten_password_code = forgotten_password_code;
        }

        public void setForgotten_password_time(String forgotten_password_time) {
            this.forgotten_password_time = forgotten_password_time;
        }

        public void setRemember_code(String remember_code) {
            this.remember_code = remember_code;
        }

        public void setCreated_on(String created_on) {
            this.created_on = created_on;
        }

        public void setLast_login(String last_login) {
            this.last_login = last_login;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getUsername() {
            return username;
        }

        public String getFirst_name() {
            return first_name;
        }

        public String getPhone() {
            return phone;
        }
    }
}


