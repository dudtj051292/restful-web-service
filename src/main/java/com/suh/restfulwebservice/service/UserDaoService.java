package com.suh.restfulwebservice.service;

import com.suh.restfulwebservice.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1,"egg", new Date(),"test1","920512-1111111"));

        users.add(new User(2,"bread", new Date(),"test1","950108-2121212"));
        users.add(new User(3,"becon", new Date(),"test1","971203-1231231"));

    }

    public List<User> findAll(){
        return users;
    }


    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for(User u : users ){
            if(u.getId() == id){
                return u;
            }
        }
        return null;
    }

    public User deleteById(int id){
        Iterator<User> iter = users.iterator();

        while(iter.hasNext()){
            User user = iter.next();
            if(user.getId() == id){
                iter.remove();
                return user;
            }
        }
        return null;
    }

    public User modify(User user) {
        for(User u : users){
            if(u.getId() == user.getId()){
                u.setName(user.getName());
                u.setJoinDate(new Date());
                return u;
            }
        }
        return null;
    }
}
