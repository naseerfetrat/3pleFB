package hu.se.naseer.hml.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.se.naseer.hml.dto.ItemDto;
import hu.se.naseer.hml.model.Item;
import hu.se.naseer.hml.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Item addItem(Item item) {
        return  itemRepository.save(item);
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItemById(Integer id) {
        return itemRepository.getOne(id);
    }

    @Override
    public void deleteItemById(Integer id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<ItemDto> convertToDto(List<Item> items) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(Item item : items){
            ItemDto itemDto = modelMapper.map(item, ItemDto.class);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }

    @Override
    public ItemDto convertToDto(Item item) {
            ItemDto itemDto = modelMapper.map(item, ItemDto.class);
            return itemDto;
    }

    
}
