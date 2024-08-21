package com.mall.mall_backend.service;

import com.mall.mall_backend.dao.UserLogInDao;
import com.mall.mall_backend.domain.temporary.Person;
import com.mall.mall_backend.domain.entity.User;
import com.mall.mall_backend.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private RedisCache redisCache;

    private static final String PERSON_KEY_PREFIX = "person:";

    public void savePerson(Person person) {
        String key = PERSON_KEY_PREFIX + person.getName();
        redisCache.setCacheObject(key, person);
    }

    public Person getPerson(String name) {
        String key = PERSON_KEY_PREFIX + name;
        return redisCache.getCacheObject(key);
    }

    @Autowired
    private UserLogInDao userLogInDao;

    public void searchUser(){
        User user = userLogInDao.getFirstUser();
        System.out.println(user);
    }


}
