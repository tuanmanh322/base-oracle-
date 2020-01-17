package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String token);

    @Query(nativeQuery = true,value = "select * from CONFIRMATION_TOKEN c where c.EMPLOYEE_ID=?1")
    ConfirmationToken findByEmployeeId(Long id);
}
