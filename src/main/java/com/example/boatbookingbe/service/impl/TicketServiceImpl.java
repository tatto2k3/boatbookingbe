package com.example.boatbookingbe.service.impl;

import com.example.boatbookingbe.dto.TicketDto;
import com.example.boatbookingbe.dto.TicketReviewDto;
import com.example.boatbookingbe.entity.Ticket;
import com.example.boatbookingbe.exception.ResourceNotFoundException;
import com.example.boatbookingbe.mapper.TicketMapper;
import com.example.boatbookingbe.repository.TicketRepository;
import com.example.boatbookingbe.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {this.ticketRepository = ticketRepository;}

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Ticket ticket = TicketMapper.mapToTicket(ticketDto);
        if (!ticketRepository.existsById(ticket.getId())) {
            Ticket saveTicket = ticketRepository.save(ticket);
            return TicketMapper.mapToTicketDto(saveTicket);
        } else {
            return null;
        }
    }

    @Override
    public TicketDto getTicketById(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket is not exists with given id: " + ticketId));
        return TicketMapper.mapToTicketDto(ticket);
    }

    @Override
    public List<TicketDto> getAllTicket() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map((ticket) -> TicketMapper.mapToTicketDto(ticket))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDto updatedTicket(Long ticketId, TicketDto updatedTicket) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket is not exists with given id: " + ticketId)
        );

        ticket.setCId(updatedTicket.getCId());
        ticket.setSId(updatedTicket.getSId());
        ticket.setSeatId(updatedTicket.getSeatId());

        Ticket updatedTicketObj = ticketRepository.save(ticket);
        return TicketMapper.mapToTicketDto(updatedTicketObj);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket is not exists with given id: " + ticketId)
        );

        ticketRepository.deleteById(ticketId);
    }

    @Override
    public TicketDto findByC_id(Long cId) {
        Ticket ticket = ticketRepository.findByCId(cId);
        return TicketMapper.mapToTicketDto(ticket);
    }

    @Override
    public List<Ticket> findSeatIdBySId(Long sId){
        return ticketRepository.findSeatIdBySId(sId);
    }

    @Override
    public TicketReviewDto ticketReview(String numId, String fromLocation, String toLocation, String departureDay) {

        return null;
    }


}
