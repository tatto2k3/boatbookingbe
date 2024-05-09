package com.example.boatbookingbe.service.impl;

import com.example.boatbookingbe.dto.CustomerDto;
import com.example.boatbookingbe.entity.Boat;
import com.example.boatbookingbe.entity.Customer;
import com.example.boatbookingbe.exception.ResourceNotFoundException;
import com.example.boatbookingbe.mapper.BoatMapper;
import com.example.boatbookingbe.mapper.CustomerMapper;
import com.example.boatbookingbe.repository.CustomerRepository;
import com.example.boatbookingbe.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto);
        if (!customerRepository.existsById(customer.getId())) {
            Customer saveCustomer = customerRepository.save(customer);
            return CustomerMapper.mapToCustomerDto(saveCustomer);
        } else {
            return null;
        }
    }

    @Override
    public CustomerDto getCustomerById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Customer is not exists with given id: " + customerId));
        return CustomerMapper.mapToCustomerDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map((customer) -> CustomerMapper.mapToCustomerDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto updatedCustomer(Long customerId, CustomerDto updatedCustomer) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer is not exists with given id: " + customerId)
        );
        customer.setName(updatedCustomer.getName());
        customer.setNumId(updatedCustomer.getNum_id());
        customer.setBirth(updatedCustomer.getBirth());
        customer.setAddress(updatedCustomer.getAddress());
        customer.setEmail(updatedCustomer.getEmail());

        Customer updatedCustomerObj = customerRepository.save(customer);
        return CustomerMapper.mapToCustomerDto(updatedCustomerObj);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new ResourceNotFoundException("Customer is not exists with given id: " + customerId)
        );

        customerRepository.deleteById(customerId);
    }

    @Override
    public CustomerDto findCutomerByName(String name) {
        Customer customer = customerRepository.findCustomerByName(name);
        return CustomerMapper.mapToCustomerDto(customer);
    }


}
