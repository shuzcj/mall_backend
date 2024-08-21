package com.mall.mall_backend.service.impl;

import com.mall.mall_backend.dao.UserLogInDao;
import com.mall.mall_backend.domain.entity.User;
import com.mall.mall_backend.domain.sercurity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author : k
 * @Date : 2022/3/23
 * @Desc :
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserLogInDao userLogInDao;

    //实现UserDetailsService接口，重写UserDetails方法，自定义用户的信息从数据中查询
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //（认证，即校验该用户是否存在）查询用户信息
        User user = userLogInDao.getUserByUserName(username);

        //如果没有查询到用户
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }

        // TODO (授权，即查询用户具有哪些权限)查询对应的用户信息

        //把数据封装成UserDetails返回
        return new LoginUser(user);
        //当一个方法的返回类型是某个接口时，实际上你可以返回该接口的任何实现类的实例。
        //Java 支持多态性，这意味着你可以将一个实现类的对象赋值给接口类型的变量。
        /*多态的三个条件
            继承或实现：在多态中必须存在有继承或实现关系的子类和父类
    方法的重写：子类对父类中的某些方法进行重新定义（重写，使用@Override注解进行重写）
    基类引用指向派生类对象，即父类引用指向子类对象，父类类型：指子类对象继承的父类类型，或实现的父接口类型
        父类类型  变量名 = new 子类类型（）；
        然后通过 变量名.方法名（）调用在子类中重写的方法
        多态体现为父类引用变量可以指向子类对象：定义了一个父类类型的引用，指向新建的子类类型的对象，由于子类是继承他的父类的，所以父类类型的引用是可以指向子类类型的对象的


         */
    }
}
