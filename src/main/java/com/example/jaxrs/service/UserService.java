package com.example.jaxrs.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

import com.example.jaxrs.model.User;

public class UserService {

    private List< User> users = new ArrayList< User>();

    public List< User> findAll() {
        users.add(new User(100L,
                "Ramesh", "ramesh@gmail.com"));
        users.add(new User(101L,
                "Tny", "tony@gmail.com"));
        users.add(new User(102L,
                "Tom", "tom@gmail.com"));
        return users;
    }

    public User fetchBy(long id) throws NotFoundException {
        for (User user : findAll()) {
            if (id == user.getId()) {
                return user;
            } else {
                throw new NotFoundException("Resource not found with Id :: " + id);
            }
        }
        return null;
    }

    public boolean create(User user) {
        return users.add(user);
    }

    public boolean update(User user) {
        for (User updateUser : users) {
            if (user.getId().equals(updateUser.getId())) {
                users.remove(updateUser);
                users.add(user);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long id) throws NotFoundException {
        for (User user : users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }
}
