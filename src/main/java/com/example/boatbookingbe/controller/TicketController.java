package com.example.boatbookingbe.controller;

import com.example.boatbookingbe.dto.CustomerDto;
import com.example.boatbookingbe.dto.ScheduleDto;
import com.example.boatbookingbe.dto.TicketDto;
import com.example.boatbookingbe.dto.TicketReviewDto;
import com.example.boatbookingbe.entity.Schedule;
import com.example.boatbookingbe.entity.Ticket;
import com.example.boatbookingbe.service.CustomerService;
import com.example.boatbookingbe.service.ScheduleService;
import com.example.boatbookingbe.service.TicketService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth/tickets")
public class TicketController {
    private TicketService ticketService;
    private CustomerService customerService;
    private ScheduleService scheduleService;
    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        TicketDto savedTicket = ticketService.createTicket(ticketDto);
        if (savedTicket == null) return new ResponseEntity<>(savedTicket, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(savedTicket, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TicketDto> getTicketById(@PathVariable("id") Long ticketId){
        TicketDto ticketDto = ticketService.getTicketById(ticketId);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAllTicket(){
        List<TicketDto> ticketDtos = ticketService.getAllTicket();
        return ResponseEntity.ok(ticketDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable("id") Long ticketId,
                                                      @RequestBody TicketDto updatedTicket){
        TicketDto ticketDto = ticketService.updatedTicket(ticketId,updatedTicket);
        return ResponseEntity.ok(ticketDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTicket(@RequestParam("id") Long ticketId){
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.ok("Delete ticket successfully!");
    }

    @GetMapping("/search")
    public ResponseEntity<?> findTicket (@RequestParam String name){
        CustomerDto customer = customerService.findCutomerByName(name);
        if (customer != null) {
            TicketDto ticket = ticketService.findByC_id(customer.getId());
            if (ticket != null) {
                ScheduleDto schedule = scheduleService.getScheduleById(ticket.getSId());
                if (schedule != null) {
                    TicketReviewDto result = new TicketReviewDto(customer.getName(), customer.getNum_id(), schedule.getBoat_name(), ticket.getSId(), ticket.getSeatId(), schedule.getDepartureDay(), schedule.getDepartureTime(), schedule.getArrivalTime(), schedule.getDepartureDay());
                    return ResponseEntity.ok().body(result);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/searchSeatID/{scheduleID}")
    public ResponseEntity<?> searchSeatID(@PathVariable Long scheduleID) {
        List<Ticket> bookedSeats = ticketService.findSeatIdBySId(scheduleID);
        List<String> seatIds = new ArrayList<>();
        for (Ticket ticket : bookedSeats) {
            seatIds.add(ticket.getSeatId());
        }
        return ResponseEntity.ok().body(seatIds);
    }

//    @GetMapping("/search-ticket")
//    public ResponseEntity<?> searchTicket(@RequestBody String numId,
//                                          @RequestBody String fromLocation,
//                                          @RequestBody String toLocation,
//                                          @RequestBody String departureDay){
//        TicketReviewDto ticketReviewDto = ticketService.ticketReview(numId, fromLocation, toLocation, departureDay);
//
//    }

}

