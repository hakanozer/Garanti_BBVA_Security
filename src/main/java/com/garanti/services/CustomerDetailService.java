package com.garanti.services;

import com.garanti.entities.Customer;
import com.garanti.entities.Role;
import com.garanti.props.Rest;
import com.garanti.repositories.CustomerRepository;
import com.garanti.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerDetailService implements UserDetailsService {

    final CustomerRepository cRepo;
    final RoleRepository rRepo;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalCustomer = cRepo.findByEmailEqualsIgnoreCase(username);
        if ( optionalCustomer.isPresent() ) {
            Customer c = optionalCustomer.get();
            return new User(
                    c.getEmail(), c.getPassword(), c.getEnable(), true,true, true, parseRole(c.getRoles())
            );
        }else {
            throw new UsernameNotFoundException("User Name Not Found");
        }
    }

    private Collection<? extends GrantedAuthority> parseRole(List<Role> roles) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for( Role item: roles ) {
            ls.add(new SimpleGrantedAuthority(item.getName()));
        }
        return ls;
    }

    public ResponseEntity register(Customer customer) {
        Rest rest = new Rest();
        Optional<Customer> optionalCustomer = cRepo.findByEmailEqualsIgnoreCase(customer.getEmail());
        if (optionalCustomer.isPresent()) {
            rest.setStatus(false);
            rest.setResult("This email use");
        }else {
            customer.setPassword( passwordEncoder.encode(customer.getPassword()) );
            cRepo.save(customer);
            rest.setStatus(true);
            rest.setResult(customer);
        }
        return new ResponseEntity(rest, HttpStatus.OK);
    }



}
