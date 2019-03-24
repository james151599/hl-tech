package middleTierRelated.entity;

import java.io.Serializable;
import middleTierRelated.tool.TableName;

@TableName("sys_menu")
public class MenuPO extends BasePO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String menuName;

  private Long parentId;

  private Integer menuSort;

  private String menuType;

  private String menuHref;

  private String permission;

  private String status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName == null ? null : menuName.trim();
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getMenuSort() {
    return menuSort;
  }

  public void setMenuSort(Integer menuSort) {
    this.menuSort = menuSort;
  }

  public String getMenuType() {
    return menuType;
  }

  public void setMenuType(String menuType) {
    this.menuType = menuType == null ? null : menuType.trim();
  }

  public String getMenuHref() {
    return menuHref;
  }

  public void setMenuHref(String menuHref) {
    this.menuHref = menuHref == null ? null : menuHref.trim();
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission == null ? null : permission.trim();
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
    sb.append(", menuName=").append(menuName);
    sb.append(", parentId=").append(parentId);
    sb.append(", menuSort=").append(menuSort);
    sb.append(", menuType=").append(menuType);
    sb.append(", menuHref=").append(menuHref);
    sb.append(", permission=").append(permission);
    sb.append(", status=").append(status);
    sb.append(", serialVersionUID=").append(serialVersionUID);
    sb.append("]");
    return sb.toString();
  }
}
