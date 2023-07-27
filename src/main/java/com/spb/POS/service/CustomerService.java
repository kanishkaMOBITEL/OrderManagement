package com.spb.POS.service;

import com.spb.POS.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void addCustomer(CustomerDTO customerDTO);

    String updateCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerByID(int customerId);

    List<CustomerDTO> getAllCustomers();


    String deleteCustomer(int customerID);
}
