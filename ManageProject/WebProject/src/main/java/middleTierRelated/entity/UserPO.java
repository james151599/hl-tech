package middleTierRelated.entity;

import java.io.Serializable;
import middleTierRelated.tool.TableName;

@TableName("sys_user")
public class UserPO extends BasePO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String loginCode;

  private String userName;

  private String password;

  private String passwordSalt;

  private String email;

  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getLoginCode() {
    return loginCode;
  }

  public void setLoginCode(String loginCode) {
    this.loginCode = loginCode == null ? null : loginCode.trim();
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName == null ? null : userName.trim();
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password == null ? null : password.trim();
  }

  public String getPasswordSalt() {
    return passwordSalt;
  }

  public void setPasswordSalt(String passwordSalt) {
    this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", id=").append(id);
    sb.append(", loginCode=").append(loginCode);
    sb.append(", userName=").append(userName);
    sb.append(", password=").append(password);
    sb.append(", passwordSalt=").append(passwordSalt);
    sb.append(", email=").append(email);
    sb.append(", status=").append(status);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
