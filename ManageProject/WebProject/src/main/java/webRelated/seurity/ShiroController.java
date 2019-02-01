package webRelated.seurity;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/testShiro")
public class ShiroController {

  @GetMapping("/index")
  public String index() {
    return "shiroLogin";
  }

  @PostMapping("/login")
  @ResponseBody
  public String login(@RequestParam String username, @RequestParam String password,
      @RequestParam(defaultValue = "false") String rememberMe) {
    String result = "login success";
    Subject subject = SecurityUtils.getSubject();
    if (!subject.isAuthenticated()) {
      UsernamePasswordToken upt = new UsernamePasswordToken(username, password);
      upt.setRememberMe(Boolean.valueOf(rememberMe));
      try {
        subject.login(upt);
        Session session = subject.getSession();
        System.out.println("getStartTimestamp: " + session.getStartTimestamp()
            + "getLastAccessTime: " + session.getLastAccessTime());
        // JavaSE应用需要自己定期调用session.touch();去更新最后访问时间
        session.setAttribute("someKey", "someValue");

      } catch (AuthenticationException e) {
        result = "login failure: " + e.getMessage();
      }
    }

    return result;
  }

  @GetMapping("/logout")
  @ResponseBody
  public String logout() {
    SecurityUtils.getSubject().logout();
    return "logout";
  }

  @GetMapping("/unauthorized")
  public String unauthorized() {
    return "shiroUnauthorized";
  }

  @RequiresPermissions("role2:insert")
  @PostMapping("/doInsert")
  @ResponseBody
  public String doInsert() {
    return "do insert";
  }

  @RequiresPermissions("role2:delete")
  @PostMapping("/doDelete")
  @ResponseBody
  public String doDelete() {
    return "do delete";
  }

  @RequiresPermissions("role1:update")
  @PostMapping("/doUpdate")
  @ResponseBody
  public String doUpdate() {
    return "do update";
  }

  @RequiresPermissions("role1:view")
  @GetMapping("/doView")
  @ResponseBody
  public String doView() {
    return "do view";
  }
}
