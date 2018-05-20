package com.project.job.controller;


import com.project.job.dao.JobRepository;
import com.project.job.dao.RoleRepository;
import com.project.job.dao.UserRepository;
import com.project.job.model.Job;
import com.project.job.model.JobUser;
import com.project.job.model.Mark;
import com.project.job.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class JobController {


    @Autowired
    JobRepository jobRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct()
    void init(){

        JobUser tamerlan = this.userRepository.findByUsername("tamerlan");
        JobUser togrul = this.userRepository.findByUsername("togrul");

        if (tamerlan == null && togrul == null){

            // TODO  for Registration process we should create separate page,
            // fetch parameters from registration form.
            // However createUser is custom method and should be used
            userRepository.save("tamerlan", "tamerlan");
            userRepository.save("togrul", "togrul");

        }

        this.jobRepository.save(
                new Job(
                    "Senior Nese developer",
                    "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                    new Date()
                )
        );

    }



    @RequestMapping("/index")
    @Transactional
    public ModelAndView getIndex(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        JobUser authUser = userRepository.findByUsername(username);

        List<Job> jobs = this.jobRepository.findAllByOrderByCreatedDesc();
        for (Job job: jobs){

            for (Mark mark: authUser.getMarks()){

                if (mark.getJob().getId().equals(job.getId())){

                    job.setMarked(true);
                }
            }
        }
        map.put("jobs", jobs);

        System.out.println(jobs);
        ModelAndView view = new ModelAndView("index", map);
//        view.getModel()
        return view;
    }






}
