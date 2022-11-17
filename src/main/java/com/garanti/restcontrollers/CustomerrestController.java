package com.garanti.restcontrollers;

import com.garanti.entities.Customer;
import com.garanti.services.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerrestController {

    final CustomerDetailService service;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Customer customer) {
        return service.register(customer);
    }

}
