package webRelated.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import middleTierRelated.entity.UserPO;
import middleTierRelated.service.UserService;
import middleTierRelated.vo.UserVO;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/save")
  // @RequiresPermissions("sys:role2:insert")
  public int save(@RequestBody UserPO user) {
    return userService.save(user);
  }

  @PostMapping("/removeById")
  // @RequiresPermissions("sys:role2:delete")
  public int removeById(String id) {
    return userService.removeById(id);
  }

  @PostMapping("/alter")
  // @RequiresPermissions("sys:role1:update")
  public int alter(@RequestBody UserPO user) {
    return userService.alter(user);
  }

  @GetMapping("/getById")
  // @RequiresPermissions("sys:role1:view")
  public UserVO getById(String id) {
    return userService.getById(id);
  }
}
