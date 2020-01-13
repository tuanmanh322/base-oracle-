package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.ABSENT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsentRepo extends JpaRepository<ABSENT,Long> {
}
