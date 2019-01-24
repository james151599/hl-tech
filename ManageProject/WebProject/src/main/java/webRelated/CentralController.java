package webRelated;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CentralController {

  @ExceptionHandler(UnauthorizedException.class)
  @ResponseBody
  public String handleUnauthorized() {
    return "no authorization";
  }
}
