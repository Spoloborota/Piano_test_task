package com.spoloborota.piano.common;

import java.util.*;

public class Question {

    private String ownerDisplaName;
    private Date creationDate;
    private String title;
    private String link;
    private boolean isAnswered;

    public Question() {
    }

    public Question(String ownerDisplaName, Date creationDate, String title, String link, boolean isAnswered) {
        this.ownerDisplaName = ownerDisplaName;
        this.creationDate = creationDate;
        this.title = title;
        this.link = link;
        this.isAnswered = isAnswered;
    }

    public String getOwnerDisplaName() {
        return ownerDisplaName;
    }

    public void setOwnerDisplaName(String ownerDisplaName) {
        this.ownerDisplaName = ownerDisplaName;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean getIsAnswered() {
        return this.isAnswered;
    }

    public void setIsAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }
}

