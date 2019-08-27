package instanceTran;

public class Exam {

  private String id;

  private String name;

  private String course;

  private Integer score;

  private byte[] img;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCourse() {
    return course;
  }

  public void setCourse(String course) {
    this.course = course;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  @Override
  public String toString() {
    return "Exam [id=" + this.id + ", name=" + this.name + ", course=" + this.course + ", score="
        + this.score + ", img=" + this.img.length + "]";
  }

  public byte[] getImg() {
    return img;
  }

  public void setImg(byte[] img) {
    this.img = img;
  }
}
