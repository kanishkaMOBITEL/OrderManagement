package com.spb.POS.service;


import com.spb.POS.dto.ItemDTO;

import com.spb.POS.dto.request.RequestSeveItemDTO;

import java.util.List;

public interface ItemService {
    void addCustomer(RequestSeveItemDTO itemDTO);

    List<ItemDTO> getItemByName(String itemName);

    List<ItemDTO> getAllItems();



}
