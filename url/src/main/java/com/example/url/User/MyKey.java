package com.example.url.User;

import java.io.Serializable;

public class MyKey implements Serializable {
    private String username;
    private String url_shorten;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUrl_short() {
        return url_shorten;
    }

    public void setUrl_short(String url_short) {
        this.url_shorten = url_short;
    }
}
