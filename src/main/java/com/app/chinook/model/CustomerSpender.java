package com.app.chinook.model;

import lombok.*;

/**
 * The type Customer spender.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CustomerSpender {
    private Integer customer_id;
    private String first_name;
    private String last_name;
    private String company;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postal_code;
    private String phone;
    private String fax;
    private String email;
    private Integer support_rep_id;
}
