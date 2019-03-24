package middleTierRelated.entity;

import java.io.Serializable;
import middleTierRelated.tool.TableName;

@TableName("sys_role_menu")
public class RoleMenuPO extends BasePO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long roleId;

  private Long menuId;

  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }

  public Long getMenuId() {
    return menuId;
  }

  public void setMenuId(Long menuId) {
    this.menuId = menuId;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(" [");
    sb.append("Hash = ").append(hashCode());
    sb.append(", roleId=").append(roleId);
    sb.append(", menuId=").append(menuId);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
