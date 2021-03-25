package com.test1.art_test1;


public class Credentials {
    /**
     * The user name
     */
    private String userName;

    /**
     * The user password
     */
    private String userPass;

    private String id;

    public Credentials() {
    }

    public Credentials(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
