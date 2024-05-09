package com.example.boatbookingbe.service.impl;

import com.example.boatbookingbe.dto.SeatDto;
import com.example.boatbookingbe.entity.Seat;
import com.example.boatbookingbe.exception.ResourceNotFoundException;
import com.example.boatbookingbe.mapper.SeatMapper;
import com.example.boatbookingbe.repository.SeatRepository;
import com.example.boatbookingbe.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatServiceImpl implements SeatService {

    private SeatRepository seatRepository;

    @Autowired
    public SeatServiceImpl(SeatRepository seatRepository) {this.seatRepository = seatRepository;}
    @Override
    public SeatDto createSeat(SeatDto seatDto) {
        Seat seat = SeatMapper.mapToSeat(seatDto);
        Seat saveSeat = seatRepository.save(seat);

        return SeatMapper.mapToSeatDto(saveSeat);
    }

    @Override
    public SeatDto getSeatById(String seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Ticket is not exists with given id: " + seatId));
        return SeatMapper.mapToSeatDto(seat);
    }

    @Override
    public List<SeatDto> getAllSeat() {
        List<Seat> seats = seatRepository.findAll();
        return seats.stream().map((seat) -> SeatMapper.mapToSeatDto(seat))
                .collect(Collectors.toList());
    }

    @Override
    public SeatDto updatedSeat(String seatId, SeatDto updatedSeat) {
        Seat seat = seatRepository.findById(seatId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket is not exists with given id: " + seatId)
        );

        seat.setType(updatedSeat.getType());

        Seat updatedTicketObj = seatRepository.save(seat);
        return SeatMapper.mapToSeatDto(updatedTicketObj);
    }

    @Override
    public void deleteSeat(String seatId) {
        Seat ticket = seatRepository.findById(seatId).orElseThrow(
                () -> new ResourceNotFoundException("Ticket is not exists with given id: " + seatId)
        );

        seatRepository.deleteById(seatId);
    }
}
