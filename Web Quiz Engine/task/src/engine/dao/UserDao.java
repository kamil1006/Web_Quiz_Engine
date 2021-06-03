package engine.dao;

import engine.entity.User;

public interface UserDao {

    User findByUserName(String userName);

    void save(User user);

}
