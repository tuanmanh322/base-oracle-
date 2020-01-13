package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.TeamProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamProjectRepo extends JpaRepository<TeamProject,Long> {
}
