package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Issues_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueHistoryRepo extends JpaRepository<Issues_History,Long> {
}
