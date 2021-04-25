package hu.se.naseer.hml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.se.naseer.hml.dto.ItemDto;
import hu.se.naseer.hml.model.Item;
import hu.se.naseer.hml.service.ItemService;

@RestController
@CrossOrigin
public class ItemController {
    
    @Autowired
    private ItemService itemService;
   

    @PostMapping("/items/add")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemDto> addItem(@RequestBody Item item){
        Item saveItem =  itemService.addItem(item);
        ItemDto saveItemDto = itemService.convertToDto(saveItem);
        return new ResponseEntity<>(saveItemDto , HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDto>> getItems(){
        List<Item> items = itemService.getItems();
        List<ItemDto> itemsDtoList = itemService.convertToDto(items);
        return new ResponseEntity<>(itemsDtoList, HttpStatus.OK);
    }

    @GetMapping("/items/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("id") Integer id){
        Item item = itemService.getItemById(id);
        ItemDto itemDto = itemService.convertToDto(item);
        return new ResponseEntity<>(itemDto , HttpStatus.OK);
    }

    @PutMapping("/items/{id}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemDto> updateItem(@RequestBody Item item){
        Item updateItem = itemService.addItem(item);
        ItemDto updatedItemDto = itemService.convertToDto(updateItem);
        return new ResponseEntity<>(updatedItemDto , HttpStatus.OK);
    }

    @DeleteMapping("/items/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteItem(@PathVariable Integer id){
        itemService.deleteItemById(id);
        return new ResponseEntity<>("Item "+id+" delete",HttpStatus.OK);
    }

}
