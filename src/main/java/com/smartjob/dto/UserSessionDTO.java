package com.smartjob.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.smartjob.model.User;

import java.util.Date;


public class UserSessionDTO {
    @Override
    public String toString() {
        return "UserSessionDTO{" +
                "sessionId='" + sessionId + '\'' +
                ", user=" + user +
                ", expirationTime=" + expirationTime +
                ", created=" + created +
                ", modified=" + modified +
                ", last_login=" + last_login +
                ", token='" + token + '\'' +
                ", isActive=" + isActive +
                '}';
    }

    private String sessionId;
    private User user;

    private long expirationTime;
    private Date created;
    private Date  modified;
    private Date  last_login;
    private String  token;
    private Boolean  isActive;


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
