package com.example.sprin_security_jwt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.sprin_security_jwt.models.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, Long>{
    
}
