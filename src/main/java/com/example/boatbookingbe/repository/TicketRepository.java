package com.example.boatbookingbe.repository;


import com.example.boatbookingbe.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findByCId(Long cId);
    List<Ticket> findSeatIdBySId(Long sId);
}
