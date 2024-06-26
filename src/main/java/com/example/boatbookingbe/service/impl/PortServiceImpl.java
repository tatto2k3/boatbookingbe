package com.example.boatbookingbe.service.impl;

import com.example.boatbookingbe.dto.PortDto;
import com.example.boatbookingbe.entity.Boat;
import com.example.boatbookingbe.entity.Port;
import com.example.boatbookingbe.exception.ResourceNotFoundException;
import com.example.boatbookingbe.mapper.BoatMapper;
import com.example.boatbookingbe.mapper.PortMapper;
import com.example.boatbookingbe.repository.PortRepository;
import com.example.boatbookingbe.service.PortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PortServiceImpl implements PortService {

    private PortRepository portRepository;

    @Autowired
    public PortServiceImpl(PortRepository portRepository){this.portRepository = portRepository;}

    @Override
    public PortDto createPort(PortDto portDto) {
        Port port = PortMapper.mapToPort(portDto);
        if (!portRepository.existsById(port.getId())) {
            Port savePort = portRepository.save(port);
            return PortMapper.mapToPortDto(savePort);
        } else {
            return null;
        }
    }

    @Override
    public PortDto getPortById(Long portId) {
        Port port = portRepository.findById(portId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Port is not exists with given id: " + portId));
        return PortMapper.mapToPortDto(port);
    }

    @Override
    public List<PortDto> getAllPorts() {
        List<Port> ports = portRepository.findAll();
        return ports.stream().map((port) -> PortMapper.mapToPortDto(port))
                .collect(Collectors.toList());
    }

    @Override
    public PortDto updatedPort(Long portId, PortDto updatedPort) {
        Port port = portRepository.findById(portId).orElseThrow(
                () -> new ResourceNotFoundException("Port is not exists with given id: " + portId)
        );

        port.setPort_name(updatedPort.getPort_name());
        port.setPort_place(updatedPort.getPort_place());

        Port updatedPortObj = portRepository.save(port);
        return PortMapper.mapToPortDto(updatedPortObj);
    }

    @Override
    public void deletePort(Long portId) {
        Port port = portRepository.findById(portId).orElseThrow(
                () -> new ResourceNotFoundException("Port is not exists with given id: " + portId)
        );

        portRepository.deleteById(portId);
    }
}
