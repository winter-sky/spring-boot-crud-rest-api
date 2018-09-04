package myspring.controller;

import myspring.dao.MyDao;
import myspring.entity.Category;
import myspring.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class MyController {

    @Autowired
    private MyDao myDao;

    @GetMapping("/categories")//get all Category
    public List<Category> getAllCategories() {
       return myDao.getAllCategories();
    }

    @GetMapping("/items")//get all Item
    public List<Item> getAllItems() {
        return myDao.getAllItems();
    }

    @PostMapping("/items")//create new Item
    @ResponseStatus(HttpStatus.CREATED)
    public Item create(@RequestBody Item item) {
        return myDao.createItem(item);
    }

    @DeleteMapping("/items/{itemId}")//remove Item
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItem(@PathVariable("itemId") int id) {
        myDao.delete(id);
    }

    @PutMapping("/items/{itemId}")//test change the old item to a new one
    public Item updateItem(@PathVariable("itemId") int id, @RequestBody Item item) {
        return myDao.update(id, item);
    }

    @GetMapping("/items/search/{itemName}")//test searching Item by part of name
    public List<Item> findItem(@PathVariable("itemName") String str) {
        return myDao.searchItem(str);
    }

    @GetMapping("/categories/search/{categoryName}")//test searching Item by Category name
    public List<Category> findItemByCategory(@PathVariable("categoryName") String str) {
        return myDao.searchByCategory(str);
    }
}
