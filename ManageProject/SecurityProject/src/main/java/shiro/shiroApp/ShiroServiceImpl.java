package shiro.shiroApp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ShiroServiceImpl implements ShiroService {

  @Override
  public Map<String, String> namePassword() {
    Map<String, String> up = new HashMap<String, String>();
    up.put("name1", "password1");
    up.put("name2", "password2");

    return up;
  }

  @Override
  public Map<String, Set<String>> userRolesPermissions(String userName) {
    Map<String, Map<String, Set<String>>> urp = new HashMap<>();
    Map<String, Set<String>> rolesPermissions = new HashMap<>();
    Set<String> permissions = new HashSet<>();
    permissions.add("role1:view");
    permissions.add("role1:update");
    rolesPermissions.put("role1", permissions);
    urp.put("name1", rolesPermissions);
    rolesPermissions = new HashMap<>();
    permissions = new HashSet<>();
    permissions.add("role2:*");
    rolesPermissions.put("role2", permissions);
    urp.put("name2", rolesPermissions);

    return urp.get(userName);
  }
}
