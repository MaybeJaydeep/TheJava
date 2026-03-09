package com.java.bankapp.service.impl;

import com.java.bankapp.dto.CustomerDTO;
import com.java.bankapp.entity.Customer;
import com.java.bankapp.repository.CustomerRepository;
import com.java.bankapp.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {

        Customer customer = new Customer();

        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setEmail(dto.getEmail());

        customer = customerRepository.save(customer);

        dto.setId(customer.getId());

        return dto;
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerDTO dto = new CustomerDTO();

        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());

        return dto;
    }
    @Override
    public List<CustomerDTO> getAllCustomers() {

        return customerRepository.findAll()
                .stream()
                .map(customer -> {

                    CustomerDTO dto = new CustomerDTO();

                    dto.setId(customer.getId());
                    dto.setFirstName(customer.getFirstName());
                    dto.setLastName(customer.getLastName());
                    dto.setEmail(customer.getEmail());

                    return dto;

                }).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO updatedCustomerDTO) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update entity using DTO
        existingCustomer.setFirstName(updatedCustomerDTO.getFirstName());
        existingCustomer.setLastName(updatedCustomerDTO.getLastName());
        existingCustomer.setEmail(updatedCustomerDTO.getEmail());
        existingCustomer.setPhone(updatedCustomerDTO.getPhone());

        Customer savedCustomer = customerRepository.save(existingCustomer);

        // Convert entity → DTO
        CustomerDTO dto = new CustomerDTO();
        dto.setId(savedCustomer.getId());
        dto.setFirstName(savedCustomer.getFirstName());
        dto.setLastName(savedCustomer.getLastName());
        dto.setEmail(savedCustomer.getEmail());
        dto.setPhone(savedCustomer.getPhone());

        return dto;
    }

    @Override
    public void deleteCustomer(Long id) {

        if(!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }

        customerRepository.deleteById(id);
    }
}