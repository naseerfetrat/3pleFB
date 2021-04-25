package hu.se.naseer.hml.service;

import java.util.List;

import hu.se.naseer.hml.dto.ItemDto;
import hu.se.naseer.hml.model.Item;

public interface ItemService {
    Item addItem(Item item);
    List<Item> getItems();
    Item getItemById(Integer id);
    void deleteItemById(Integer id);

    List<ItemDto> convertToDto(List<Item> items);
    ItemDto convertToDto(Item item);

}
