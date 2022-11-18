package com.garanti.restcontrollers;

import com.garanti.entities.Product;
import com.garanti.props.UserData;
import com.garanti.services.ProductService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductRestController {

    final ProductService pService;

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody Product product) {
        return pService.save(product);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return pService.list();
    }

    @GetMapping("/all")
    public ResponseEntity all() {
        RestTemplate template = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/posts";
        String data = template.getForObject(url, String.class);
        Gson gson = new Gson();
        UserData[] datas = gson.fromJson(data, UserData[].class );
        datas[0].setTitle("Java Spring - Security");
        return new ResponseEntity(datas, HttpStatus.OK);
    }

}
