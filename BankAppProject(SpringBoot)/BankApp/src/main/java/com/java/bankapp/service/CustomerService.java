package com.java.bankapp.service;

import com.java.bankapp.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomer);

    void deleteCustomer(Long id);

}
