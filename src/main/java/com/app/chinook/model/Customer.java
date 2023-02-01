package com.app.chinook.model;

import lombok.*;

/**
 * The type Customer model, where customer record will be mapped.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Customer {
    private Integer customer_id;
    private String first_name;
    private String last_name;
    private String country;
    private String postal_code;
    private String phone;
    private String email;
}
