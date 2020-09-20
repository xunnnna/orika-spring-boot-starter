package com.example.orika.dto;


import com.example.orika.domain.Cat;
import lombok.Data;

/**
 * The destination object of person data.
 */
@Data
public class PersonDTO {

    /**
     * The given name.
     */
    private String givenName;

    /**
     * The sir name.
     */
    private String sirName;

    /**
     * The age.
     */
    private String age;

    private String birthDay;

    private Cat cat;


}
