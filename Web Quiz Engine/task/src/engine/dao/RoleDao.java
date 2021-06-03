package engine.dao;

import engine.entity.Role;
import org.springframework.stereotype.Repository;


public interface RoleDao {
    public Role findRoleByName(String theRoleName);
}
