package org.example.simplesocialnetworkingapp;

import org.example.simplesocialnetworkingapp.model.User;

public interface IUserAuth {

    /**
     * Registers a new user with the given username & password.
     *
     * @return true if registration succeeded, false if username is taken.
     */
    boolean registerUser(String username, String password);

    /**
     * Attempts to log in a user with the given credentials.
     *
     * @return the User object if valid, otherwise null.
     */
    User loginUser(String username, String password);
}
