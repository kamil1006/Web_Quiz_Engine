package engine.service;

import engine.WebQuizEngine;
import engine.dao.RoleDao;
import engine.entity.Role;
import engine.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    //--------------------------------------------------------------------------------------

    // need to inject user dao
   // @Autowired
   // private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //--------------------------------------------------------------------------------------
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //User user = userDao.findByUserName(userName);
        User user=null;
        user =  findByUserName(username);



        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }
    //--------------------------------------------------------------------------------------
    @Override
    public User findByUserName(String userName) {
       // return null;
        // check the database if the user already exists
        //return userDao.findByUserName(userName);
    User user=null;
        boolean jest=false;
        for(User u: WebQuizEngine.lista){
            if(u.getUsername().equals(userName)){
                jest=true;
                user=u;
            }
        }
        if(jest){
            return user;
        }else {
            return null;
        }

    }
    //--------------------------------------------------------------------------------------
    @Override
    public void save(User user) {

      //  User user = new User();
        // assign user details to the user object
        //user.setUsername(crmUser.getEmail());
        //user.setPassword(crmUser.getPassword());
        // give user default role of "employee"
      //  user.setRoles(Arrays.asList(roleDao.findRoleByName("USER")));
        // save user in the database
        //userDao.save(user);
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));

        String haslo=user.getPassword();
        haslo = passwordEncoder.encode(haslo);
        user.setPassword(haslo);
        WebQuizEngine.lista.add(user);
        int k=2;
    }
    //--------------------------------------------------------------------------------------
    @Override
    public void saveWithRole(User user) {

    }
    //--------------------------------------------------------------------------------------

}
