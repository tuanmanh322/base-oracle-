package com.mockapi.mockapi.repository;

import com.mockapi.mockapi.model.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken,Long> {
    ConfirmationToken findByToken(String token);
}
