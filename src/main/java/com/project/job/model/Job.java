package com.project.job.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Job {

    @Id
    @GeneratedValue
    Long id;


    @Column
    String name;

    @Column(length = 2000)
    String description;


    @Column
    Date created;

    @Transient
    boolean marked=false;

    public Job() {

    }

    public Job(String name, String description, Date created) {
        this.name = name;
        this.description = description;
        this.created = created;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }


    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return ""+ marked;
    }
}
