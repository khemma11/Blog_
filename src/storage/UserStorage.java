package storage;

import exception.UserNotFoundException;
import model.User;


public interface UserStorage {
    void add(User user);

    User getUserByEmail(String email) throws UserNotFoundException;

    User getUserByEmailAddPassword(String email, String password) throws UserNotFoundException;

    void printAllUser();

    boolean isEmpty();

}

