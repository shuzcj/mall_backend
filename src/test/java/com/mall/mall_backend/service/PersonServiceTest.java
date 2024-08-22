package com.mall.mall_backend.service;

import com.mall.mall_backend.domain.entity.User;
import com.mall.mall_backend.domain.temporary.Person;
import com.mall.mall_backend.service.impl.PersonService;
import com.mall.mall_backend.utils.RedisCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private RedisCache redisCache;

    @BeforeEach
    public void setUp() {
        // Clear any existing data before each test
        redisCache.deleteObject("person:*");
    }

    @Test
    public void testSaveAndRetrievePerson() {
        // Create a new person
        Person person = new Person("John Doe", 30);

        // Save the person to Redis
        personService.savePerson(person);

        // Retrieve the person from Redis
        Person retrievedPerson = personService.getPerson("John Doe");
        System.out.println(retrievedPerson);
        // Verify that the retrieved person is the same as the saved one
        assertNotNull(retrievedPerson);
        //assertEquals("asd", retrievedPerson.getName());
        assertEquals(person.getAge(), retrievedPerson.getAge());
    }

    @Test
    public void testSearchUser(){
        personService.searchUser();
    }

    @Autowired
    LogInService logInService ;

    @Test
    public void testLogIn(){

        //logInService.register(new User("test1","123456",'1'));
        logInService.login(new User("test1","123456"));
    }

}
