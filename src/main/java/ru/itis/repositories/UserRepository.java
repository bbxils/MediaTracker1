package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRepository<User>{
    List<User> findAll() throws SQLException;
}
