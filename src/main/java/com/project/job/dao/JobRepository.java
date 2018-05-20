package com.project.job.dao;

import com.project.job.model.Job;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends CrudRepository<Job, Long> {

    @Query("Select c from Job c where c.name like:name")
    List<Job> findByNameContaining(@Param("name")String name);


    Job findFirstById(Long id);

    List<Job> findAllByOrderByCreatedDesc();


}
