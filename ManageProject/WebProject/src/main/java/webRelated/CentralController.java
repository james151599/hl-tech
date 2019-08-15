package webRelated;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CentralController {

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseBody
  public String handleUnauthorized() {
    return "exception: no authorization";
  }

  @ExceptionHandler(UnknownAccountException.class)
  @ResponseBody
  public String handleUnknownAccountException() {
    return "exception: incorrect username or password";
  }

  @ExceptionHandler(IncorrectCredentialsException.class)
  @ResponseBody
  public String handleIncorrectCredentialsException() {
    return "exception: incorrect username or password";
  }
}
