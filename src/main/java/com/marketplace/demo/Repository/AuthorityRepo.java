package com.marketplace.demo.Repository;

import com.marketplace.demo.Model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepo extends JpaRepository<Authority, String> {
}
