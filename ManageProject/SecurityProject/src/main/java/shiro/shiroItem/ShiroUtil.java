package shiro.shiroItem;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroUtil {

  public static final String ALGORITHMNAME = "SHA-512";

  public static final int HASHITERATIONS = 3;

  private static final String PRIVATESALT = "hl";

  private ShiroUtil() {}

  public static String generatePublicSalt() {
    SecureRandomNumberGenerator srng = new SecureRandomNumberGenerator();
    srng.setSeed(PRIVATESALT.getBytes());

    return srng.nextBytes().toHex();
  }

  public static String encryption(String content, String salt) {
    return new SimpleHash(ALGORITHMNAME, content, salt, HASHITERATIONS).toString();
  }

  public static void setSessionValue(Object key, Object value) {
    SecurityUtils.getSubject().getSession().setAttribute(key, value);
  }

  public static void getSessionValue(Object key) {
    SecurityUtils.getSubject().getSession().getAttribute(key);
  }
}
