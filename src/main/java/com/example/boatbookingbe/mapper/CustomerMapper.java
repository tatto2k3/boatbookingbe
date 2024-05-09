package com.example.boatbookingbe.mapper;


import com.example.boatbookingbe.dto.CustomerDto;
import com.example.boatbookingbe.entity.Customer;

public class CustomerMapper {
    public static CustomerDto mapToCustomerDto (Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getNumId(),
                customer.getBirth(),
                customer.getAddress(),
                customer.getEmail()
        );
    }
    public static Customer mapToCustomer(CustomerDto customerDto) {
        return new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getNum_id(),
                customerDto.getBirth(),
                customerDto.getAddress(),
                customerDto.getEmail()
        );
    }

}
