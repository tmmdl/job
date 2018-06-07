package com.project.job.controller;


import com.project.job.dao.JobRepository;
import com.project.job.dao.RoleRepository;
import com.project.job.dao.UserRepository;
import com.project.job.model.Job;
import com.project.job.model.JobUser;
import com.project.job.model.Mark;
import com.project.job.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
    void init() {

//        JobUser tamerlan = this.userRepository.findByUsername("tamerlan");
        JobUser togrul = this.userRepository.findByUsername("togrul");

        if (togrul == null) {

            // TODO  for Registration process we should create separate page,
            // fetch parameters from registration form.
            // However createUser is custom method and should be used
//            userRepository.save("tamerlan", "tamerlan");
            userRepository.save("togrul", "togrul");

        }

//        this.jobRepository.save(
//                new Job(
//                        "Senior Nese developer",
//                        "Lorem Ipsum is simply dummy",
//                        "location",
//                        "company",
//                        new java.sql.Date(),
//                        "link",
//                        "comment"
//                )
//        );

    }

    @RequestMapping("/")
    public ModelAndView getHome() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Job> jobs = this.jobRepository.findAllByOrderByCreatedDesc();
        map.put("jobs", jobs); //html template engine understands spring easily

        ModelAndView view = new ModelAndView("home", map);

        return view;
    }

    @RequestMapping("/index")
    @Transactional
    public ModelAndView getIndex() {

        HashMap<String, Object> map = new HashMap<String, Object>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName(); //get logged in username
        JobUser authUser = userRepository.findByUsername(username);

        List<Job> jobs = this.jobRepository.findAllByOrderByCreatedDesc();
        for (Job job : jobs) {

            for (Mark mark : authUser.getMarks()) {

                if (mark.getJob().getId().equals(job.getId())) {

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