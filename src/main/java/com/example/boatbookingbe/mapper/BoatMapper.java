package com.example.boatbookingbe.mapper;


import com.example.boatbookingbe.dto.BoatDto;
import com.example.boatbookingbe.entity.Boat;

public class BoatMapper {
    public static BoatDto mapToBoatDto(Boat boat){
        return new BoatDto(
                boat.getId(),
                boat.getName(),
                boat.getNum_seat(),
                boat.getNum_bed()
        );
    }

    public static Boat mapToBoat(BoatDto boatDto) {
        return new Boat (
                boatDto.getId(),
                boatDto.getName(),
                boatDto.getNum_seat(),
                boatDto.getNum_bed()
        );
    }
}
