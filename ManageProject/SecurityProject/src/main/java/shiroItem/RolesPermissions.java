package shiroItem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RolesPermissions {

  private Map<String, Set<String>> userRoles;

  private Map<String, Set<String>> userPermissions;

  public RolesPermissions() {
    userRoles = new HashMap<>();
    Set<String> roles = new HashSet<>();
    roles.add("role1");
    userRoles.put("abc", roles);
    userPermissions = new HashMap<>();
    Set<String> permissions = new HashSet<>();
    permissions.add("role1:view,update");
    userPermissions.put("abc", permissions);
  }

  public Map<String, Set<String>> getUserRoles() {
    return userRoles;
  }

  public void setUserRoles(Map<String, Set<String>> userRoles) {
    this.userRoles = userRoles;
  }

  public Map<String, Set<String>> getUserPermissions() {
    return userPermissions;
  }

  public void setUserPermissions(Map<String, Set<String>> userPermissions) {
    this.userPermissions = userPermissions;
  }
}
