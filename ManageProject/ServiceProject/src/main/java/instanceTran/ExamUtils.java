package instanceTran;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExamUtils {

  private JdbcTemplate jdbcTemplate;

  private NamedParameterJdbcTemplate jdbcTemplate2;

  @Autowired
  public ExamUtils(JdbcTemplate jt, NamedParameterJdbcTemplate npjt) {
    this.jdbcTemplate = jt;
    this.jdbcTemplate2 = npjt;
  }

  public int insertExam(final String name, final String course, final int score, final File img) {
    return this.jdbcTemplate.update("insert into exam_info(name,course,score,img) values(?,?,?,?)",
        new PreparedStatementSetter() {

          @Override
          public void setValues(PreparedStatement ps) throws SQLException {
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setInt(3, score);
            try {
              ps.setBinaryStream(4, new FileInputStream(img), img.length());
            } catch (FileNotFoundException e) {
              throw new SQLException(e.getMessage());
            }
          }
        });
  }

  public int[][] insertExams(final List<String[]> many) {
    return this.jdbcTemplate.batchUpdate("insert into exam_info(name,course,score) values(?,?,?)",
        many, 3, new ParameterizedPreparedStatementSetter<String[]>() {

          @Override
          public void setValues(PreparedStatement ps, String[] argument) throws SQLException {
            ps.setString(1, argument[0]);
            ps.setString(2, argument[1]);
            ps.setInt(3, Integer.parseInt(argument[2]));
          }
        });
  }

  public int deleteExamById(long id) {
    return this.jdbcTemplate.update("delete from exam_info where id=?", id);
  }

  public int updateExamScore(long id, int score) {
    return this.jdbcTemplate.update("update exam_info set score=? where id=?", score, id);
  }

  public int getExamRowCount() {
    return this.jdbcTemplate.queryForObject("select count(*) from exam_info", Integer.class);
  }

  public Exam getExamById(long id) {
    return this.jdbcTemplate.queryForObject("select * from exam_info where id=?", new ExamMapper(),
        id);
  }

  public List<Exam> getExamsByCourse(String course) {
    return this.jdbcTemplate2.query("select * from exam_info where course=:param1",
        Collections.singletonMap("param1", course), new ExamMapper());
  }

  public void otherOperations() {
    this.jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");
    // invokes a stored procedure
    this.jdbcTemplate.update("call SUPPORT.REFRESH_ACTORS_SUMMARY(?)", 1L);
  }

  private static class ExamMapper implements RowMapper<Exam> {

    @Override
    public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
      try {
        Exam obj = new Exam();
        obj.setId(rs.getLong("id"));
        obj.setName(rs.getString("name"));
        obj.setCourse(rs.getString("course"));
        obj.setScore(rs.getInt("score"));
        InputStream is = rs.getBinaryStream("img");
        byte[] cache = new byte[1000];
        int count = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((count = is.read(cache)) > 0) {
          baos.write(cache, 0, count);
        }
        obj.setImg(baos.toByteArray());
        is.close();
        baos.close();
        return obj;
      } catch (Exception e) {
        throw new SQLException(e.getMessage());
      }
    }
  }
}
