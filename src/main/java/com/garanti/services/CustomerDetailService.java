package com.garanti.services;

import com.garanti.repositories.CustomerRepository;
import com.garanti.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerDetailService {

    final CustomerRepository cRepo;
    final RoleRepository rRepo;
    final PasswordEncoder passwordEncoder;

}
