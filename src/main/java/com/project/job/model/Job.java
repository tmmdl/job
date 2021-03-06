package com.project.job.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Job {

    @Id
    @Column(length = 2000)
    private String id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private String company;
    @Column
    private java.sql.Date created;
    @Column (length = 2000)
    private String link;
    @Column
    private String comment;

    @Transient
    boolean marked=false;

    public Job() {

    }

    public Job(String id, String title, String location, String company, java.sql.Date date, String link, String comment) {
        this.id = id;
        this.name = title;
        this.location = location;
        this.company = company;
        this.created = date;
        this.link = link;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public java.sql.Date getCreated() {
        return created;
    }

    public void setCreated(java.sql.Date created) {
        this.created = created;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public String toString() {
        return ""+ marked;
    }
}
