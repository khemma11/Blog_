package storage;

import exception.UserNotFoundException;
import model.User;

public class UserStorageImpl implements UserStorage {
    private User[] users = new User[15];
    private int size = 0;


    public void add(User user) {
        if (size == users.length) {
            extend();
        }
        users[size++] = user;
    }


    private void extend() {
        User[] temp = new User[users.length + 10];
        System.arraycopy(users, 0, temp, 0, users.length);
        users = temp;
    }


    @Override
    public User getUserByEmail(String email) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email)) {
                return users[i];
            }

        }
        throw new UserNotFoundException("model.User with " + email + " already exists ");
    }


    @Override
    public User getUserByEmailAddPassword(String email, String password) throws UserNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                return users[i];
            }

        }
        throw new UserNotFoundException("model.User with " + email + "and" + password + " already exists ");
    }


    @Override
    public void printAllUser() {
        for (int i = 0; i < size; i++) {
            System.out.println(users[i]);
        }

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}



