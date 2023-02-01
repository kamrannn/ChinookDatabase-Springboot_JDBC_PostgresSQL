package com.app.chinook.repository.customer;

import com.app.chinook.mapper.CustomerCountryRowMapper;
import com.app.chinook.mapper.CustomerGenreRowMapper;
import com.app.chinook.mapper.CustomerRowMapper;
import com.app.chinook.mapper.CustomerSpenderRowMapper;
import com.app.chinook.model.Customer;
import com.app.chinook.model.CustomerCountry;
import com.app.chinook.model.CustomerGenre;
import com.app.chinook.model.CustomerSpender;
import com.app.chinook.repository.generic.GenericCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * It's the Customer repository.
 */
@Repository
@Slf4j
public class CustomerRepository implements GenericCrudRepository<Customer> {

    /**
     * The Jdbc template that we are going to use to execute the queries.
     */
    @Autowired
    JdbcTemplate jdbcTemplate;


    /**
     * Find all records from a database from the customer table.
     *
     * @return all customers record from the database.
     */
    @Override
    public List<Customer> findAll() {
        try {
            String sql = "SELECT customer_id,first_name, last_name,country,postal_code,phone,email FROM CUSTOMER";
            List<Customer> customers = jdbcTemplate.query(
                    sql,
                    new CustomerRowMapper());
            log.info("All the customers from database: {}", customers);
            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * Get a customer from the database against a specific id
     *
     * @param id it's the customer id for which are going to find the record.
     * @return customer record from the database.
     */
    @Override
    public Customer getById(int id) {
        try {
            String sql = "SELECT * FROM CUSTOMER WHERE customer_id = ?";
            Customer customer = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CustomerRowMapper());
            log.info("Getting customer by id: {}", customer);
            return customer;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * Gets customer by name.
     *
     * @param name the name
     * @return the customer by name
     */
    public List<Customer> getCustomerByName(String name) {
        try {
            String sql = "select customer_id,first_name, last_name,country,postal_code,phone,email " +
                    "from customer " +
                    "where first_name LIKE '%" + name + "%' or last_name LIKE '%" + name + "%'";

            List<Customer> customers = jdbcTemplate.query(
                    sql,
                    new CustomerRowMapper());
            log.info("Getting customer by name: {}", customers);
            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * Gets customers by using offset and limit.
     * Limit will limit the records.
     * While offset will tell from which row it should start fetching the record.
     *
     * @param offset the offset
     * @param limit  the limit
     * @return the customers by offset and limit
     */
    public List<Customer> getCustomersByOffsetAndLimit(int offset, int limit) {
        try {
            String sql = "select customer_id,first_name, last_name,country,postal_code,phone,email from customer offset " + offset + " limit " + limit + ";";
            List<Customer> customers = jdbcTemplate.query(
                    sql,
                    new CustomerRowMapper());
            log.info("Getting customers by limit and offset: {}", customers);
            return customers;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * Add a new customer in the database.
     *
     * @param customer the new customer record
     * @return the value, if successfully added, then it will return 1, else 0
     */
    @Override
    public Integer insertNewRecord(Customer customer) {
        log.info("Going to add a new customer: {}", customer);
        try {
            int result = jdbcTemplate.update(
                    "insert into customer(first_name, last_name,country,postal_code,phone,email) values( ?,?, ?, ?,?, ?);",
                    customer.getFirst_name(), customer.getLast_name(), customer.getCountry(), customer.getPostal_code(), customer.getPhone(), customer.getEmail());
            if (result == 1) {
                log.info("New customer has been added to the database.");
            } else {
                log.info("New customer is not added in the database.");
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * Update an existing customer in the database.
     *
     * @param customer the new customer record will be replaced from old record
     * @return the value, if successfully added, then it will return 1, else 0
     */
    @Override
    public Integer update(Customer customer) {
        try {
            log.info("Going to update customer: {}", customer);
            Integer result = jdbcTemplate.update(
                    "UPDATE customer SET first_name = ?, last_name = ?, country = ?,postal_code = ?,phone = ?,email = ? WHERE customer_id=?;",
                    customer.getFirst_name(), customer.getLast_name(), customer.getCountry(), customer.getPostal_code(), customer.getPhone(), customer.getEmail(), customer.getCustomer_id());
            if (result == 1) {
                log.info("Customer with id {} has been updated in the database.", customer.getCustomer_id());
            } else {
                log.info("Customer with id {} hasn't updated in the database.", customer.getCustomer_id());
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * Gets country with most customers.
     *
     * @return the country with most customers
     */
    public CustomerCountry getCountryWithMostCustomers() {
        try {
            String sql = "SELECT country FROM customer GROUP BY country ORDER BY COUNT(customer_id) DESC LIMIT 1;";
            CustomerCountry customerCountry = jdbcTemplate.queryForObject(sql, new Object[]{}, new CustomerCountryRowMapper());
            log.info("Getting country having most customers: {}", customerCountry);
            return customerCountry;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    /**
     * In this we are finding the Highest spender customer from the database.
     *
     * @return the highest customer spender
     */
    public CustomerSpender highestSpenderCustomer() {
        try {
            String sql = "Select * from customer where customer_id= (select customer_id from invoice group by customer_id order by SUM(total) DESC LIMIt 1);";
            CustomerSpender customerSpender = jdbcTemplate.queryForObject(sql, new Object[]{}, new CustomerSpenderRowMapper());
            log.info("The highest Spender Customer is: {}", customerSpender);
            return customerSpender;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Customer most popular genre list is queried in this function.
     *
     * @param customer_id the customer id
     * @return the list
     */
    public List<CustomerGenre> customerMostPopularGenre(int customer_id) {
        try {
            String sql = "SELECT genre.name, tracks_count\n" +
                    "FROM genre\n" +
                    "JOIN (\n" +
                    "SELECT genre_id, COUNT(*) as tracks_count\n" +
                    "FROM invoice_line\n" +
                    "JOIN track ON track.track_id = invoice_line.track_id\n" +
                    "JOIN invoice ON invoice.invoice_id = invoice_line.invoice_id\n" +
                    "WHERE invoice.customer_id = " + customer_id + "\n" +
                    "GROUP BY genre_id\n" +
                    ") AS customer_tracks ON genre.genre_id = customer_tracks.genre_id\n" +
                    "ORDER BY customer_tracks.tracks_count DESC\n" +
                    "LIMIT 2;";
            List<CustomerGenre> customerGenres = jdbcTemplate.query(
                    sql,
                    new CustomerGenreRowMapper());
            log.info("Getting customer most popular genres: {}", customerGenres);
            return customerGenres;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
