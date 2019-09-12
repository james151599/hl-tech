package shiro.shiroService;

import java.util.Set;

public interface ShiroService {

  void clearAuthorizeCache();

  String getUserPassword(String username);

  Set<String> getUserRoles(String username);

  Set<String> getUserPermissions(String username);
}
