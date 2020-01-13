package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepo extends JpaRepository<Issues,Long> {
}
