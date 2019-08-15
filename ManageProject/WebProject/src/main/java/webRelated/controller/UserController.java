package webRelated.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import middleTierRelated.dto.LoginDTO;
import middleTierRelated.service.UserService;
import shiro.shiroItem.ShiroUtil;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/login")
  public ModelAndView index() {
    return new ModelAndView("userLogin");
  }

  @GetMapping("/main")
  public ModelAndView main() {
    return new ModelAndView("main");
  }

  @GetMapping("/pay")
  @RequiresPermissions("user:pay")
  public ModelAndView pay() {
    return new ModelAndView("doPay");
  }

  @PostMapping("/doLogin")
  public ModelAndView doLogin(LoginDTO data) {
    Subject subject = SecurityUtils.getSubject();
    UsernamePasswordToken upt = new UsernamePasswordToken(data.getUsername(), data.getPassword());
    upt.setRememberMe(Boolean.valueOf(data.getRememberMe()));
    subject.login(upt);

    Session session = subject.getSession();
    System.out.println("getStartTimestamp: " + session.getStartTimestamp() + "getLastAccessTime: "
        + session.getLastAccessTime());
    // JavaSE应用需要自己定期调用session.touch();去更新最后访问时间
    ShiroUtil.setSessionValue("key", "value");

    return new ModelAndView("main");
  }

  // @GetMapping("/logout")
  // public ModelAndView logout() {
  // SecurityUtils.getSubject().logout();
  // return new ModelAndView("redirect:/login");
  // }

  @GetMapping("/unauthorized")
  public String unauthorized() {
    return "filter: unauthorized";
  }

  @PostMapping("/filter")
  public String filter() {
    return "do something";
  }

  @PostMapping("/add")
  public String add() {
    SecurityUtils.getSubject().checkPermission("user:add");
    return "do user add";
  }

  @PostMapping("/remove")
  @RequiresPermissions("user:remove")
  public String removeById() {
    return "do user remove";
  }

  @PostMapping("/alter")
  @RequiresPermissions("user:alter")
  public String alter() {
    return "do user alter";
  }

  @GetMapping("/view")
  @RequiresPermissions("user:view")
  public String getById() {
    return "do user view";
  }
}
