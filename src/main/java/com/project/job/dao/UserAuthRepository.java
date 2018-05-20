package com.project.job.dao;

import com.project.job.model.JobUser;

public interface UserAuthRepository {

    public JobUser save(String username, String password);
}
