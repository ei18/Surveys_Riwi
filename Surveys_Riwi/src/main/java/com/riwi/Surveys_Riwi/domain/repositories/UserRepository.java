package com.riwi.Surveys_Riwi.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.Surveys_Riwi.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
