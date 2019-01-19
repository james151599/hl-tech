package instanceBean.noScanBean;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class UserPreferences {

  @Autowired
  private HttpServletRequest request;

  public void desc() {
    System.out.println("request: " + request.getAttribute("requestValue"));
    System.out.println("session: " + request.getSession().getAttribute("sessionValue"));
  }
}
