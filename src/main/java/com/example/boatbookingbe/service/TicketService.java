package com.example.boatbookingbe.service;

import com.example.boatbookingbe.dto.ScheduleDto;
import com.example.boatbookingbe.dto.TicketDto;
import com.example.boatbookingbe.dto.TicketReviewDto;
import com.example.boatbookingbe.entity.Ticket;

import java.util.List;

public interface TicketService {
    TicketDto createTicket(TicketDto ticketDto);

    TicketDto getTicketById(Long ticketId);

    List<TicketDto> getAllTicket();

    TicketDto updatedTicket(Long ticketId, TicketDto updatedTicket);

    void deleteTicket(Long ticketId);

    TicketDto findByC_id(Long cId);
    List<Ticket> findSeatIdBySId(Long sId);

    TicketReviewDto ticketReview(String numId, String fromLocation, String toLocation, String departureDay);
}
