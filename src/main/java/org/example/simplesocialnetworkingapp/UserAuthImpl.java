package org.example.simplesocialnetworkingapp;

import org.example.simplesocialnetworkingapp.dao.UserDAO;
import org.example.simplesocialnetworkingapp.model.User;
import org.example.simplesocialnetworkingapp.util.PasswordHasher;

public class UserAuthImpl implements IUserAuth {

    private final UserDAO userDAO;

    public UserAuthImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean registerUser(String username, String password) {
        // 1. Check if user already exists
        User existing = userDAO.findByUsername(username);
        if (existing != null) {
            return false; // or throw an exception
        }

        // 2. Hash the password
        String hashed = PasswordHasher.hash(password);

        // 3. Create a new user
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(hashed);
        newUser.setRole("Regular User");
        // created_at is auto-handled by DB

        // 4. Insert into DB
        userDAO.insertUser(newUser);
        return true;
    }

    @Override
    public User loginUser(String username, String password) {
        // 1. Look up user
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return null;
        }

        // 2. Check password
        boolean valid = PasswordHasher.verify(password, user.getPassword());
        return valid ? user : null;
    }
}
