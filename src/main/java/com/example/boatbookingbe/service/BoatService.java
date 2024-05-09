package com.example.boatbookingbe.service;

import com.example.boatbookingbe.dto.BoatDto;

import java.util.List;

public interface BoatService {
    BoatDto createBoat(BoatDto boatDto);

    BoatDto getBoatById(Long boatId);

    List<BoatDto> getAllBoats();

    BoatDto updatedBoat(Long boatId, BoatDto updatedBoat);

    void deleteBoat(Long boatId);
}
