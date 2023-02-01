package com.app.chinook;

import com.app.chinook.model.Customer;
import com.app.chinook.repository.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ChinookApplication implements CommandLineRunner {

    /**
     * We are injecting the customer repository bean here so that we can use its functions.
     */
    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ChinookApplication.class, args);
    }

    /**
     * This is working as a runner method. Which will run all our methods that we have made in our customer repository.
     * You are to also create a ApplicationRunner class to test the repository (Covered this point from document)
     */
    @Override
    public void run(String... args) {

        //In this I am calling our find all customer generic function to get all the customers.
        customerRepository.findAll();

        //In this I am calling our get customer by id generic function to get the specific customer.
        customerRepository.getById(1);

        //In this I am calling our get customer by name function that
        // will fetch all those customer which names have that input value
        customerRepository.getCustomerByName("L");

        //In this I am calling our get customers query by a specific limit and offset.
        customerRepository.getCustomersByOffsetAndLimit(0, 10);

        //This line is calling our insert new record function and passing a customer record to insert the data into the db.
        customerRepository.insertNewRecord(Customer.builder()
                .first_name("Kamran")
                .last_name("Abbasi")
                .country("Pakistan")
                .postal_code("45400")
                .phone("03125153352")
                .email("sendtokamran0@gmail.com")
                .build());

        //This line is calling our update existing record function and passing updated customer record
        // to update the existing customer
        customerRepository.update(Customer.builder()
                .customer_id(1000)
                .first_name("Raja")
                .last_name("Kamran")
                .country("Pakistan")
                .postal_code("45400")
                .phone("03125153352")
                .email("rajakamran737@gmail.com")
                .build());


        //This line is calling our get that country who have the highest number of customers.
        customerRepository.getCountryWithMostCustomers();

        //This line is calling our get that customer who is the highest spender.
        customerRepository.highestSpenderCustomer();

        //This line is calling our get that genre who is most like by the given customer
        customerRepository.customerMostPopularGenre(1);
    }
}
