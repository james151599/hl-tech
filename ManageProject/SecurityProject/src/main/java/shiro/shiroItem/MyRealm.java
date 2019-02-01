package shiro.shiroItem;

import java.util.Map;
import java.util.Set;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import shiro.shiroService.ShiroService;

public class MyRealm extends AuthorizingRealm {

  private ShiroService ss;

  public MyRealm(ShiroService ss) {
    this.ss = ss;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String userName = (String) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
    for (Map.Entry<String, Set<String>> each : this.ss.userRolesPermissions(userName).entrySet()) {
      sai.addRole(each.getKey());
      sai.addStringPermissions(each.getValue());
    }

    return sai;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    String username = (String) token.getPrincipal();
    Map<String, String> user = this.ss.namePassword();

    if (username == null || user.get(username) == null) {
      throw new UnknownAccountException("UnknownAccountException");
    }

    return new SimpleAuthenticationInfo(username, user.get(username),
        ByteSource.Util.bytes("44bab1b7e615547b3ead081f96edded0"), getName());
  }

  @Override
  public boolean supports(AuthenticationToken token) {
    return token instanceof UsernamePasswordToken;
  }

  @Override
  public String getName() {
    return "MyRealm";
  }
}
