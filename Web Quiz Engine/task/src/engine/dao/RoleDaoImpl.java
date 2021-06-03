package engine.dao;

import engine.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;

@Repository
public class RoleDaoImpl implements RoleDao{

    private Map<String,String> roles;

    @Override
    public Role findRoleByName(String theRoleName) {

        // now retrieve/read from database using name
        roles= new LinkedHashMap<String,String>();
        //key role, value display to user
        roles.put("ADMIN","admin");
        roles.put("USER","user");
        String s = roles.get(theRoleName);

        Role role= new Role();
        role.setName(s);

        return role;
    }
}
