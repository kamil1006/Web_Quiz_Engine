package engine.service;

import engine.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface UserService extends UserDetailsService{

    User findByUserName(String userName);
    void save(User user);
    void saveWithRole(User user);

}
