package com.project.job.controller;

import com.project.job.dao.JobRepository;
import com.project.job.dao.MarkRepository;
import com.project.job.dao.UserRepository;
import com.project.job.model.Job;
import com.project.job.model.JobUser;
import com.project.job.model.Mark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.HashMap;

@RestController
public class JobRestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    MarkRepository markRepository;

    @RequestMapping("/jobs/{id}/mark/")
    @Transactional
    public ResponseEntity markJob(@PathVariable String id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        JobUser authUser = userRepository.findByUsername(username);
        Job job = jobRepository.findOne(id);

        if (job == null){

            return ResponseEntity.notFound().build();
        }

        Mark mark = markRepository.findByJobUserIdAndJobId(authUser.getId(), job.getId());

        if (mark != null){
            // unmark if marked
            authUser.getMarks().remove(mark);
            markRepository.delete(mark);
            return ResponseEntity.ok(
                    new HashMap<String, String>(){{
                        put("message", "Unmarked!");
                    }}
            );
        } else{

            // However we can add our JobRepository with addMark method.
            // MarkRepository is redundant now.
            System.out.println("SAVE");
            System.out.println(authUser);
            System.out.println(job);
            authUser.getMarks().add(new Mark(authUser, job));
            userRepository.save(authUser);
            return ResponseEntity.ok(
                    new HashMap<String, String>(){{
                        put("message", "Marked!");
                    }}
            );
        }
    }
}