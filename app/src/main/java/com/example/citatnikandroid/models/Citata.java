package com.example.citatnikandroid.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Citata {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("author")
    @Expose
    private String author;

    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("creationDate")
    @Expose
    private String creationDate;


    public Citata(Integer id, String author, String content, String creationDate) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.creationDate = creationDate;
    }


    public Integer getId() {
        return id;
    }
    public void setUserId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
