package ru.itis.services;

import ru.itis.dto.AjaxDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService {

    private UserRepository userRepository;

    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    public void addUser(AjaxDto form) throws SQLException {
        User user = User.builder()
                .name(form.getFirstName())
                .surname(form.getLastName())
                .email(form.getEmail())
                .password("qwerty007")
                .build();
        userRepository.save(user);
    }
}
