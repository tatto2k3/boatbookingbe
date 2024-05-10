package com.example.boatbookingbe.repository;


import com.example.boatbookingbe.entity.Boat;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BoatRepository extends JpaRepository<Boat, Long> {

}
