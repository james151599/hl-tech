package shiro.shiroService.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import shiro.shiroService.ShiroService;

@Service
public class ShiroServiceImpl implements ShiroService {

  @Override
  public Map<String, String> namePassword() {
    Map<String, String> up = new HashMap<String, String>();
    up.put("name1",
        "fd0c5c358de2471103e3282e0ba7b534367a58d68b2aaada8b484d9eff70e16aa31aa2f0de9d82fb8e8ce78c83614f1bd452357e1f791d6cbd155cc63f9929cf");
    up.put("name2",
        "fd0c5c358de2471103e3282e0ba7b534367a58d68b2aaada8b484d9eff70e16aa31aa2f0de9d82fb8e8ce78c83614f1bd452357e1f791d6cbd155cc63f9929cf");
    up.put("name3",
        "fd0c5c358de2471103e3282e0ba7b534367a58d68b2aaada8b484d9eff70e16aa31aa2f0de9d82fb8e8ce78c83614f1bd452357e1f791d6cbd155cc63f9929cf");

    return up;
  }

  @Override
  public Map<String, Set<String>> userRolesPermissions(String userName) {
    Map<String, Map<String, Set<String>>> urp = new HashMap<>();
    Map<String, Set<String>> rolesPermissions = new HashMap<>();
    Set<String> permissions = new HashSet<>();
    permissions.add("user:*");
    rolesPermissions.put("role1", permissions);
    urp.put("name1", rolesPermissions);
    rolesPermissions = new HashMap<>();
    permissions = new HashSet<>();
    permissions.add("user:view,add,alter");
    rolesPermissions.put("role2", permissions);
    urp.put("name2", rolesPermissions);
    rolesPermissions = new HashMap<>();
    permissions = new HashSet<>();
    permissions.add("user:view");
    rolesPermissions.put("role3", permissions);
    urp.put("name3", rolesPermissions);

    return urp.get(userName);
  }
}
