package com.marketplace.demo.Controller;

import com.marketplace.demo.Model.Store;
import com.marketplace.demo.Service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Controller and API LAYER
@RestController
@RequestMapping(path = "/api/v0")
public class StoreController {

    private final StoreService storeService;


    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping(value = "/store", produces ="application/json")
    public Store save(@RequestBody Store store){
       return storeService.save(store);
    }

    @DeleteMapping(value = "/store/{id}", produces ="application/json")
    public void delete(@PathVariable Long id){
        storeService.delete(id);
    }

    @GetMapping(value = "/stores", produces ="application/json" )
    public List<Store> listStore(){
        return storeService.listStore();
    }

    @GetMapping(value = "/store/{id}", produces ="application/json")
    public Optional<Store> getStore(Long id){
        return storeService.getStore(id);
    }

}
