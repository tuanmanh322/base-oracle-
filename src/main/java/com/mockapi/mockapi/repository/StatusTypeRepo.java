package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Status_Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusTypeRepo extends JpaRepository<Status_Type,Long> {
}
