package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.ABSENT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsentRepo extends JpaRepository<ABSENT,Long> {
    @Query(nativeQuery = true,value = "select * from ABSENT a where a.PERSON_ABSENT_ID = ?1")
    ABSENT findByEmployeeId(Long id);
}
