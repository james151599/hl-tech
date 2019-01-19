package shiroItem;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

  @Autowired
  private RolesPermissions rsps;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    String username = (String) principals.getPrimaryPrincipal();
    SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
    sai.addRoles(rsps.getUserRoles().get(username));
    sai.addStringPermissions(rsps.getUserPermissions().get(username));
    return sai;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    String username = token.getPrincipal().toString();
    String password = new String((char[]) token.getCredentials());

    if (!"abc".equals(username)) {
      throw new UnknownAccountException("UnknownAccountException");
    }
    if (!"123".equals(password)) {
      throw new IncorrectCredentialsException("IncorrectCredentialsException");
    }
    return new SimpleAuthenticationInfo(username, password, getName());
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
