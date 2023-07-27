package com.spb.POS.service.impl;

import com.spb.POS.service.OrderService;
import com.spb.POS.dto.request.RequestOrderSaveDTO;
import com.spb.POS.entity.Order;
import com.spb.POS.entity.OrderDetails;
import com.spb.POS.repo.CustomerRepo;
import com.spb.POS.repo.ItemRepo;
import com.spb.POS.repo.OrderDetailsRepo;
import com.spb.POS.repo.OrderRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceIMPL implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;


    @Override
    @Transactional
    public String addOrder(RequestOrderSaveDTO requestOrderSaveDTO) {

        Order order = new Order(

                requestOrderSaveDTO.getDate(),
                requestOrderSaveDTO.getTotal(),
                customerRepo.getById(requestOrderSaveDTO.getCustomers())

        );

        orderRepo.save(order);

        if(orderRepo.existsById(order.getOrderID())){
            List<OrderDetails> orderDetails = new ArrayList<>();

            orderDetails = modelMapper.map(requestOrderSaveDTO.getOrderDetails(),new TypeToken<List<OrderDetails>>(){}
                    .getType());

            for(int i=0; i<orderDetails.size();i++){
                orderDetails.get(i).setOrders(order);
                orderDetails.get(i).setItems(itemRepo.getById(requestOrderSaveDTO.getOrderDetails().get(i).getItems()));
            }

            if(orderDetails.size()>0){
                orderDetailsRepo.saveAll(orderDetails);
            }
            return "saved";
        }
        return null;
    }
}
