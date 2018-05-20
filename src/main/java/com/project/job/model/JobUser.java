package com.project.job.model;


import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Entity
public class JobUser {


    @Id
    @GeneratedValue
    Long id;

    // this is the main field for login, we should apply unique index
    @Column(unique = true)
    String username;


    String password;


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    List<Mark> marks;

    boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    @ManyToMany
    List<UserRole> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }


    @Override
    public String toString() {
        return username;
    }
}
