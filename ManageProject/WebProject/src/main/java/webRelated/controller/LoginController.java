package webRelated.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import middleTierRelated.dto.LoginDTO;
import shiro.shiroItem.ShiroUtil;

@Controller
@RequestMapping("/login")
public class LoginController {

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

  @PostMapping("/doLogin")
  public String doLogin(LoginDTO data) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken upt = new UsernamePasswordToken(data.getUsername(), data.getPassword());
    upt.setRememberMe(Boolean.valueOf(data.getRememberMe()));
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
}
