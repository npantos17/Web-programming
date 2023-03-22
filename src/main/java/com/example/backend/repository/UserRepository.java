package com.example.backend.repository;

import com.example.backend.entities.user.User;
import com.example.backend.entities.user.UserType;

import java.util.List;

public interface UserRepository {

    public User addUser(User user);
    public User findUserById(Integer id);
    public User findUserByEmail(String email);
    public User editUser(User user);
    public List<User> getAllUsers();
    public void deleteUser(int id);
    public void activateUser(Integer id);
    public void deactivateUser(Integer id);
    public List<User> usersByPage(Integer pageNum);




}
