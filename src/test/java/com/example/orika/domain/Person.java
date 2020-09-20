package com.example.orika.domain;

import lombok.Data;

import java.time.Instant;

/**
 * The source object of person data.
 */
@Data
public class Person {

    /**
     * The first name.
     */
    private String firstName;

    /**
     * The last name.
     */
    private String lastName;

    /**
     * The age.
     */
    private int age;

    private Instant birthDay;

    private Cat cat;

}
