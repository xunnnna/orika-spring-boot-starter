package com.example.orika;

import com.example.orika.domain.Cat;
import com.example.orika.domain.Person;
import com.example.orika.dto.CatDTO;
import com.example.orika.dto.PersonDTO;
import ma.glasnost.orika.MapperFacade;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrikaApplicationTests {

	@Autowired
	private MapperFacade orikaMapperFacade;

	@Test
	public void simpleTest() {
		Cat cat = new Cat();
		cat.setColor("yellow");
		cat.setWeight(10);
		System.out.println(cat);
		CatDTO catDTO = orikaMapperFacade.map(cat, CatDTO.class);
		System.out.println(catDTO);
	}

	@Test
	public void complexTest() {
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Smith");
		person.setAge(1);
		person.setBirthDay(Instant.now());
		System.out.println(person);
		PersonDTO personDTO = orikaMapperFacade.map(person, PersonDTO.class);
		System.out.println(personDTO);
	}

	/**
	 * expect deep
	 */
	@Test
	public void deepOrShallow() {
		Cat cat = new Cat();
		cat.setColor("yellow");
		cat.setWeight(10);
		Person person = new Person();
		person.setCat(cat);
		PersonDTO personDTO = orikaMapperFacade.map(person, PersonDTO.class);
		Cat cat1 = personDTO.getCat();
		Assert.assertNotEquals(cat, cat1);
	}



}
