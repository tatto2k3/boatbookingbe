package com.example.boatbookingbe.repository;


import com.example.boatbookingbe.entity.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {
}
