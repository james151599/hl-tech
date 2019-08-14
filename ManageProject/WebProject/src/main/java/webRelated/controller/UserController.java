package webRelated.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import middleTierRelated.dto.LoginDTO;
import middleTierRelated.entity.UserPO;
import middleTierRelated.service.UserService;
import middleTierRelated.vo.UserVO;
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

  @PostMapping("/save")
  @RequiresPermissions("user:add")
  public int save(@RequestBody UserPO user) {
    return userService.save(user);
  }

  @PostMapping("/removeById")
  @RequiresPermissions("user:remove")
  public int removeById(String id) {
    return userService.removeById(id);
  }

  @PostMapping("/alter")
  @RequiresPermissions("user:alter")
  public int alter(@RequestBody UserPO user) {
    return userService.alter(user);
  }

  @GetMapping("/getById")
  @RequiresPermissions("user:search")
  public UserVO getById(String id) {
    return userService.getById(id);
  }
}
