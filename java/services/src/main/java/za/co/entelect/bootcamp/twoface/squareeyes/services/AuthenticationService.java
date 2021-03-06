package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.customer.Customer;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.customers.CustomersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class AuthenticationService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
    private CustomersRepository customersRepository;

    @Autowired
    public AuthenticationService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public Customer getCustomerWithEmail(String email) {
        return customersRepository.search("email", email).get(0);
    }

    public Customer getCustomerWithID(Customer customer) {
        return customersRepository.find(customer.getCustomerID());
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Customer customer = customersRepository.searchNoWild("email", email).get(0);

            if (customer == null) {
                logger.debug("email that was returned was null (empty)");
                return null;
            }
            logger.debug("User holds the value " + customer.toString());
            GrantedAuthority list = new SimpleGrantedAuthority("User");
            UserDetails userDetails = new User(customer.getEmail(), customer.getPasswordHash(), Arrays.asList(list));
            return userDetails;
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public Customer createCustomer(Customer customer){
        return customersRepository.create(customer);
    }
}
