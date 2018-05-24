package com.project.job.dao;


import com.project.job.model.Mark;
import org.springframework.data.repository.CrudRepository;

public interface MarkRepository extends CrudRepository<Mark, String> {


    Mark findByJobUserIdAndJobId(Long userId, String jobId);
}
