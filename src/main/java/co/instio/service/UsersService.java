package co.instio.service;

import co.instio.dto.UserView;
import co.instio.entity.Users;

import java.util.List;

public interface UsersService {

    List<UserView> createUsers(List<Users> users);
    List<UserView> getAllUsers();
    UserView getById(Long userId);
    UserView updateUsers(Long userId,Users users);
    void deleteUser(Long userId);
}
