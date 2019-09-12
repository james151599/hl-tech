package shiro.shiroItem;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
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

  public void clearAuthorizationCache() {
    super.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // null usernames are invalid
    if (principals == null) {
      throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
    }
    String username = (String) super.getAvailablePrincipal(principals);

    SimpleAuthorizationInfo sai = new SimpleAuthorizationInfo();
    sai.setRoles(this.ss.getUserRoles(username));
    sai.setStringPermissions(this.ss.getUserPermissions(username));

    return sai;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    String username = upToken.getUsername();
    // String username = (String) token.getPrincipal();
    String password = this.ss.getUserPassword(username);

    if (username == null || password == null) {
      throw new UnknownAccountException("UnknownAccountException");
    }

    return new SimpleAuthenticationInfo(username, password,
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
