package middleTierRelated.entity;

import java.io.Serializable;
import java.util.Date;

public class BasePO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Date createDate;

  private Date updateDate;

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }
}
