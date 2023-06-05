package com.example.springboot.dao;


import com.example.springboot.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }


    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public void addUser(User user) {
            entityManager.merge(user);
    }

    public void updateUser(Long id, User user) {
        User oldUser = getUserById(id);
        oldUser.setName(user.getName());
        oldUser.setLastName(user.getLastName());

    }


}
