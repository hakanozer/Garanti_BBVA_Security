package com.garanti.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String name;
    private String email;
    private String password;
    private Boolean enable;

    @ManyToMany
    List<Role> roles;

}
