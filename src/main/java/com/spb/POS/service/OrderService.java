package com.spb.POS.service;

import com.spb.POS.dto.request.RequestOrderSaveDTO;

public interface OrderService {
    String
    addOrder(RequestOrderSaveDTO requestOrderSaveDTO);

}
