package com.mall.auth_server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.api.client.searchClient;
@Service
public class test {

    @Autowired
    private searchClient searchClient;

    public void test() {
        String s=searchClient.test();
        System.out.println(s);
    }

}
