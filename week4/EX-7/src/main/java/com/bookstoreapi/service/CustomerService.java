package com.bookstoreapi.service;

import com.bookstoreapi.dto.BookDTO;
import com.bookstoreapi.dto.CustomerDTO;
import com.bookstoreapi.entity.Book;
import com.bookstoreapi.entity.Customer;
import com.bookstoreapi.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());

    }

    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
        Customer customer = convertDTOToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertEntityToDTO(savedCustomer);
    }


    //Model Mapper Implementation
    private CustomerDTO convertEntityToDTO(Customer customer) {
        CustomerDTO customerDTO = this.modelMapper.map(customer, CustomerDTO.class);
        return customerDTO;
    }

    private Customer convertDTOToEntity(CustomerDTO customerDTO) {
        Customer customer = this.modelMapper.map(customerDTO, Customer.class);
        return customer;
    }

}
