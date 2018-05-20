package com.project.job.dao;

import com.project.job.model.JobUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<JobUser, Long>, UserAuthRepository {


    JobUser findByUsername(String username);
}
