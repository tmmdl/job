package com.project.job.dao;

import com.project.job.model.JobUser;
import com.project.job.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Arrays;

@Repository
public class UserRepositoryImpl implements UserAuthRepository {

    @PersistenceContext
    EntityManager em;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public JobUser save(String username, String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserRole role = roleRepository.findByName("ADMIN");
        if (role == null) {
            role = new UserRole();
            role.setName("ADMIN");
            em.persist(role);
            role = roleRepository.findByName("ADMIN");
        }
        JobUser jobUser = new JobUser();
        jobUser.setUsername(username);
        jobUser.setActive(true);
        jobUser.setPassword(bCryptPasswordEncoder.encode(password));
        jobUser.setRoles(Arrays.asList(role));
        em.persist(jobUser);
        return jobUser;

    }
}
