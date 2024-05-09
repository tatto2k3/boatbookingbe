package com.example.boatbookingbe.mapper;


import com.example.boatbookingbe.dto.PortDto;
import com.example.boatbookingbe.entity.Port;

public class PortMapper {
    public static PortDto mapToPortDto (Port port) {
        return new PortDto(
                port.getId(),
                port.getPort_name(),
                port.getPort_place()
        );
    }
    public static Port mapToPort(PortDto portDto) {
        return new Port(
                portDto.getId(),
                portDto.getPort_name(),
                portDto.getPort_place()
        );
    }
}
