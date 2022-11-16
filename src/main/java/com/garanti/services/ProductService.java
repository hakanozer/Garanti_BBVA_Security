package com.garanti.services;

import com.garanti.entities.Product;
import com.garanti.props.Rest;
import com.garanti.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository pRepo;

    // save
    public ResponseEntity<Rest> save(Product product) {
        pRepo.save(product);
        Rest rest = new Rest();
        if ( product.getPid() != null ) {
            rest.setStatus(true);
            rest.setResult(product);
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }else {
            rest.setStatus(false);
            rest.setResult(product);
            return new ResponseEntity<>(rest, HttpStatus.BAD_REQUEST);
        }
    }

    // list
    public ResponseEntity<Rest> list() {
        Rest rest = new Rest();
        rest.setStatus(true);
        rest.setResult(pRepo.findAll());
        return new ResponseEntity<>(rest, HttpStatus.OK);
    }

}
