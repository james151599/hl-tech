package middleTierRelated.entity;

import java.io.Serializable;
import middleTierRelated.tool.TableName;

@TableName("sys_role")
public class RolePO extends BasePO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String roleName;

  private Integer roleSort;

  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName == null ? null : roleName.trim();
  }

  public Integer getRoleSort() {
    return roleSort;
  }

  public void setRoleSort(Integer roleSort) {
    this.roleSort = roleSort;
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
    sb.append(", roleName=").append(roleName);
    sb.append(", roleSort=").append(roleSort);
    sb.append(", status=").append(status);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
