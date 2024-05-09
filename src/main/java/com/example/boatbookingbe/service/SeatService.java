package com.example.boatbookingbe.service;

import com.example.boatbookingbe.dto.SeatDto;

import java.util.List;

public interface SeatService {
    SeatDto createSeat(SeatDto seatDto);

    SeatDto getSeatById(String seatId);

    List<SeatDto> getAllSeat();

    SeatDto updatedSeat(String seatId, SeatDto updatedSeat);

    void deleteSeat(String seatId);
}
