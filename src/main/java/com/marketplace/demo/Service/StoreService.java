package com.marketplace.demo.Service;

import com.marketplace.demo.Model.Store;
import com.marketplace.demo.Repository.StoreRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


// Service Layer
@Service
public class StoreService {

    private final StoreRepo storeRepo;

    public StoreService(StoreRepo storeService) {
        this.storeRepo = storeService;
    }

    public Store save(Store store){
        Store result = storeRepo.save(store);
        return result;
    }

    public List<Store> listStore(){
        return storeRepo.findAll();
    }

    public void delete(Long id){
        storeRepo.deleteById(id);
    }

    public Optional<Store> getStore(Long id){
        return storeRepo.findById(id);
    }

}
