package middleTierRelated.dto;

public class LoginDTO {

  private String username;

  private String password;

  private String rememberMe;

  private String imageCode;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRememberMe() {
    return rememberMe;
  }

  public void setRememberMe(String rememberMe) {
    this.rememberMe = rememberMe;
  }

  public String getImageCode() {
    return imageCode;
  }

  public void setImageCode(String imageCode) {
    this.imageCode = imageCode;
  }
}
