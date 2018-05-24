package com.project.job.dao;

import com.project.job.model.Job;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends CrudRepository<Job, String> {

    @Query("Select c from Job c where c.name like:name")
    List<Job> findByNameContaining(@Param("name")String name);


    Job findFirstById(String id);

    List<Job> findAllByOrderByCreatedDesc();


}
