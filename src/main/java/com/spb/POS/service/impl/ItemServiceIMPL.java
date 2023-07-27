package com.spb.POS.service.impl;

import com.spb.POS.dto.ItemDTO;
import com.spb.POS.dto.request.RequestSeveItemDTO;
import com.spb.POS.entity.Item;
import com.spb.POS.repo.ItemRepo;
import com.spb.POS.service.ItemService;
import com.spb.POS.exception.NotFoundException;
import com.spb.POS.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceIMPL implements ItemService {
    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public void addCustomer(RequestSeveItemDTO requestSeveItemDTO) {

//        Item item = modelMapper.map(requestSeveItemDTO,Item.class);
//        item.setActiveState(false);
//        if(!itemRepo.existsById(item.getItemID())){
//            itemRepo.save(item);
//            System.out.println("Saved");
//        }

        Item item = itemMapper.requestDtoToEntity(requestSeveItemDTO);
        item.setActiveState(false);
        if(!itemRepo.existsById(item.getItemID())){
            itemRepo.save(item);
            System.out.println("Saved");
        }

    }

    @Override
    public List<ItemDTO> getItemByName(String itemName) {

        List<Item> items = itemRepo.findAllByItemName(itemName);

        List<ItemDTO> itemDTOS = itemMapper.requestEntityListToDtoList(items);

        return itemDTOS;
    }

    @Override
    public List<ItemDTO> getAllItems() {

        List<Item> items = itemRepo.findAllByActiveStateIs(false);

        if(items.size()>0) {
            List<ItemDTO> itemDTOS = itemMapper.requestEntityListToDtoList(items);
            return itemDTOS;
        }else {
            throw new NotFoundException("No data found");
        }
    }




}
