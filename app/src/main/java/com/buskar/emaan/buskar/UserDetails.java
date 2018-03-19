package com.buskar.emaan.buskar;

/**
 * Created by Emaan on 11/30/2017.
 */

public class UserDetails {
    private String user_name;
    private String user_password;
    private String user_email;
    private String user_status;

    public String getUserName() {
        return user_name;
    }
    public void setUserName(String UserName) {
        this.user_name = UserName;
    }

    public String getUserPassword() {
        return user_password;
    }
    public void setUserPassword(String UserPW) {
        this.user_password = UserPW;
    }

    public String getUserEmail() {
        return user_email;
    }
    public void setUserEmail(String UserEmail) {
        this.user_email = UserEmail;
    }

    public String getUserStatus() {
        return user_status;
    }
    public void setUserStatus(String UserStatus) {
        this.user_status = UserStatus;
    }
}
