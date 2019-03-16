package shiro.shiroItem;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroUtil {

  public static final String ALGORITHM_NAME = "SHA-512";

  public static final int HASH_ITERATIONS = 3;

  private static final String PRIVATE_SALT = "hl";

  private ShiroUtil() {}

  public static String generatePublicSalt(String account) {
    SecureRandomNumberGenerator srng = new SecureRandomNumberGenerator();
    srng.setSeed((PRIVATE_SALT + account).getBytes());

    return srng.nextBytes().toHex();
  }

  public static String encryption(String content, String salt) {
    return new SimpleHash(ALGORITHM_NAME, content, salt, HASH_ITERATIONS).toString();
  }

  public static void setSessionValue(Object key, Object value) {
    SecurityUtils.getSubject().getSession().setAttribute(key, value);
  }

  public static void getSessionValue(Object key) {
    SecurityUtils.getSubject().getSession().getAttribute(key);
  }

  public static String getSessionId() {
    return (String) SecurityUtils.getSubject().getSession().getId();
  }
}
