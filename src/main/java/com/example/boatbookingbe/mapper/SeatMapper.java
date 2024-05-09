package com.example.boatbookingbe.mapper;


import com.example.boatbookingbe.dto.SeatDto;
import com.example.boatbookingbe.entity.Seat;

public class SeatMapper {
    public static SeatDto mapToSeatDto (Seat seat) {
        return new SeatDto(
                seat.getId(),
                seat.getType()
        );
    }
    public static Seat mapToSeat(SeatDto seatDto) {
        return new Seat(
                seatDto.getId(),
                seatDto.getType()
        );
    }
}
