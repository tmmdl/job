package com.project.job.dao;

import com.project.job.model.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, Long>
{

    UserRole findByName(String name);
}
