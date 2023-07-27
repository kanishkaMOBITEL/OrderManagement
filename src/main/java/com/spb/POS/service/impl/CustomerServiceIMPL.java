package com.spb.POS.service.impl;

import com.spb.POS.service.CustomerService;
import com.spb.POS.dto.CustomerDTO;
import com.spb.POS.entity.Customer;
import com.spb.POS.repo.CustomerRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addCustomer(CustomerDTO customerDTO) {

        Customer customer = new Customer(

                customerDTO.getCustomerID(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getSalary(),
                customerDTO.getContactNumbers(),
                customerDTO.getNic(),
                customerDTO.isActiveState()
        );

            if(!customerRepo.existsById(customer.getCustomerID())){
                customerRepo.save(customer);
            }else{
                System.out.println("Customer is already exists");
            }
    }

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {

        if(customerRepo.existsById(customerDTO.getCustomerID())){
            Customer customer = customerRepo.getById(customerDTO.getCustomerID());

            customer.setCustomerAddress(customerDTO.getCustomerAddress());
            customer.setCustomerName(customerDTO.getCustomerName());
            customer.setSalary(customerDTO.getSalary());

            customerRepo.save(customer);
            return "updated";
        }else{

            System.out.println("no customer found for that id");
            return  "no customer found for that id";
        }


    }

    @Override
    public CustomerDTO getCustomerByID(int customerId) {

        Customer customer = customerRepo.getById(customerId);
        if(customer!=null) {
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);


            return customerDTO;
        }else{
            return null;
        }




//        Customer customer = customerRepo.getById(customerId);
//        if(customer!=null){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.getCustomerID(),
//                    customer.getCustomerName(),
//                    customer.getCustomerAddress(),
//                    customer.getSalary(),
//                    customer.getContactNumbers(),
//                    customer.getNic(),
//                    customer.isActiveState()
//            );
//
//            return  customerDTO;
//
//        }else{
//            return null;
//        }

//       Optional<Customer> customer = customerRepo.findById(customerId);
//        if(customer!=null){
//            CustomerDTO customerDTO = new CustomerDTO(
//                    customer.get().getCustomerID(),
//                    customer.get().getCustomerName(),
//                    customer.get().getCustomerAddress(),
//                    customer.get().getSalary(),
//                    customer.get().getContactNumbers(),
//                    customer.get().getNic(),
//                    customer.get().isActiveState()
//            );
//
//            return  customerDTO;
//
//        }else{
//            return null;
//        }



    }

    @Override
    public List<CustomerDTO> getAllCustomers() {

        List<Customer>getCustomers = customerRepo.findAll();
        List<CustomerDTO>customerDTOList = new ArrayList<>();
//        for (Customer c: getCustomers){

//            CustomerDTO customerDTO = new CustomerDTO(
//                    c.getCustomerID(),
//                    c.getCustomerName(),
//                    c.getCustomerAddress(),
//                    c.getSalary(),
//                    c.getContactNumbers(),
//                    c.getNic(),
//                    c.isActiveState()
//            );
//
//            customerDTOList.add(customerDTO);
//
//        }
            if(customerDTOList!=null){
                customerDTOList = modelMapper.map(getCustomers,new TypeToken<List<CustomerDTO>>(){}
                .getType());
            }
        return customerDTOList;
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "deleted";
        }else{
            return "no customer exists for that id";
        }
    }






}
