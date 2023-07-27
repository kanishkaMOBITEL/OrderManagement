package com.spb.POS.util.mappers;

import com.spb.POS.dto.ItemDTO;
import com.spb.POS.dto.request.RequestSeveItemDTO;
import com.spb.POS.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item requestDtoToEntity(RequestSeveItemDTO requestSeveItemDTO);
    List<ItemDTO> requestEntityListToDtoList(List<Item> items);
    List<ItemDTO> retuestPagetoList(Page<Item> items);
 }
