package com.marketplace.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class Authority {

    @Id
    private String authorityName;

    @ManyToMany
    private Set<Users> usersList;

}
