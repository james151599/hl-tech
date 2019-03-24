package middleTierRelated.entity;

import java.io.Serializable;
import middleTierRelated.tool.TableName;

@TableName("sys_user_role")
public class UserRolePO extends BasePO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long userId;

  private Long roleId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", userId=").append(userId);
    sb.append(", roleId=").append(roleId);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
