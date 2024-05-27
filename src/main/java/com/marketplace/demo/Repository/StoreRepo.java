package com.marketplace.demo.Repository;

import com.marketplace.demo.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepo extends JpaRepository<Store, Long> {
}
