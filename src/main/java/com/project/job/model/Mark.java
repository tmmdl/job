package com.project.job.model;


import javax.persistence.*;

@Entity
public class Mark {


    @Id
    @GeneratedValue
    Long id;

    @OneToOne(cascade = {CascadeType.MERGE})
    JobUser jobUser;

    @OneToOne(cascade = {CascadeType.MERGE})
    Job job;


    public Mark(JobUser jobUser, Job job) {

        this.jobUser = jobUser;
        this.job = job;
    }

    public Mark() {
    }

    public JobUser getJobUser() {
        return jobUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setJobUser(JobUser jobUser) {
        this.jobUser = jobUser;
    }
}
