package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.Issues_History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueHistoryRepo extends JpaRepository<Issues_History,Long> {

    @Query(nativeQuery = true,value = "select * from ISSUES_HISTORY i where i.UPDATE_PERSON_ID=?1")
    Issues_History findByUpdatePerson(Long id);

    @Query(nativeQuery = true,value = "UPDATE ISSUES_HISTORY  SET UPDATE_PERSON_ID=?1 ")
    void updateIH(String type);
}
