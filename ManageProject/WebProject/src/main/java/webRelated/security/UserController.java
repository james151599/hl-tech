package webRelated.security;

import org.apache.shiro.SecurityUtils;
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
import shiro.shiroItem.ShiroUtil;

@Controller
@RequestMapping("/user")
public class UserController {

  @GetMapping("/index")
  public String index() {
    Subject subject = SecurityUtils.getSubject();
    if (subject.isRemembered() || subject.isAuthenticated()) {
      return "main";
    }

    return "userLogin";
  }

  @GetMapping("/main")
  public String main() {
    return "main";
  }

  @PostMapping("/login")
  public String login(@RequestParam String username, @RequestParam String password,
      @RequestParam(defaultValue = "false") String rememberMe) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken upt = new UsernamePasswordToken(username, password);
    upt.setRememberMe(Boolean.valueOf(rememberMe));
    subject.login(upt);

    Session session = subject.getSession();
    System.out.println("getStartTimestamp: " + session.getStartTimestamp() + "getLastAccessTime: "
        + session.getLastAccessTime());
    // JavaSE应用需要自己定期调用session.touch();去更新最后访问时间
    ShiroUtil.setSessionValue("key", "value");

    return "main";
  }

  @GetMapping("/logout")
  @ResponseBody
  public String logout() {
    SecurityUtils.getSubject().logout();
    return "logout";
  }

  @GetMapping("/unauthorized")
  public String unauthorized() {
    return "unauthorized";
  }

  @PostMapping("/doInsert")
  @RequiresPermissions("sys:role2:insert")
  @ResponseBody
  public String doInsert() {
    return "do insert";
  }

  @PostMapping("/doDelete")
  @RequiresPermissions("sys:role2:delete")
  @ResponseBody
  public String doDelete() {
    return "do delete";
  }

  @PostMapping("/doUpdate")
  @RequiresPermissions("sys:role1:update")
  @ResponseBody
  public String doUpdate() {
    return "do update";
  }

  @GetMapping("/doView")
  @RequiresPermissions("sys:role1:view")
  @ResponseBody
  public String doView() {
    return "do view";
  }
}
