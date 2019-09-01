package shiro.shiroService;

import java.util.Map;
import java.util.Set;

public interface ShiroService {

  Map<String, String> namePassword();

  Map<String, Set<String>> userRolesPermissions(String userName);

  void clearAuthorizeCache();
}
