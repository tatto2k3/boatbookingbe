package com.example.boatbookingbe.repository;


import com.example.boatbookingbe.entity.ERole;
import com.example.boatbookingbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User findByRole(ERole role);

}

