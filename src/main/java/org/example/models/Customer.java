package org.example.models;

import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String firstName;
    private String lastName;
    private String postCode;

    public static Customer init() {
        return Customer.builder().firstName("Rachel").lastName("Green").postCode("2066").build();
    }
}
