package com.example.boatbookingbe.repository;


import com.example.boatbookingbe.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, String> {
}
