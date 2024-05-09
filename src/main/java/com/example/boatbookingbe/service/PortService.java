package com.example.boatbookingbe.service;



import com.example.boatbookingbe.dto.PortDto;

import java.util.List;

public interface PortService {
    PortDto createPort(PortDto portDto);

    PortDto getPortById(Long portId);

    List<PortDto> getAllPorts();

    PortDto updatedPort(Long portId, PortDto updatedPort);

    void deletePort(Long portId);
}
