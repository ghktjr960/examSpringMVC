package spring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		System.out.println("MemberDao(DataSource)");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) { //select
		List<Member> result = jdbcTemplate.query(
				"select \"ID\", \"EMAIL\", \"PASSWORD\", \"NAME\", \"REGDATE\" from"
				+ " \"MEMBER\" where \"EMAIL\"=?", 
				new MemberRowMapper(),
				email);
		return result.isEmpty() ? null : result.get(0);
	}
	
	public List<Member> selectAll(){
		List<Member> result = jdbcTemplate.query(
				"select \"ID\", \"EMAIL\", \"PASSWORD\", \"NAME\", \"REGDATE\" from \"MEMBER\"",
				new MemberRowMapper());
		return result;
	}
	
	public int count() {
		String query = "select count(*) from \"MEMBER\"";
		//쿼리 결과가 하나만 있을 경우 활용(쿼리 결과가 하나가 아니면 예외)
		return jdbcTemplate.queryForObject(query, Integer.class);
	}
	
	public void update(Member member) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "update \"MEMBER\" set \"NAME\"= ?, \"PASSWORD\" = ? where \"EMAIL\"= ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, member.getName());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getEmail());
				return pstmt;
			}
			
		});
	}

	public void insert(Member member){ //insert
//		KeyHolder keyHolder = new GeneratedKeyHolder();
		String query = "insert into \"MEMBER\" (\"ID\", \"EMAIL\", \"PASSWORD\", \"NAME\", \"REGDATE\")"
				+ " values (\"MEMBER_SEQ\".nextval, ?, ?, ?, sysdate)";
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(query/* , new String[] {"ID"} */);
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());

				return pstmt;
			}
			
		}/*, keyHolder*/); //prepareStatement에 전달한 String배열에 해당하는 컬럼에 사용된 값을 키홀더에 저장
//		Number key = keyHolder.getKey();
//		member.setId(key.longValue());
		member.setId(this.lastSequence());
	}
	
	public long lastSequence() {
		String sql = "select max(\"ID\") from \"MEMBER\"";
		return jdbcTemplate.queryForObject(sql, Long.class);
	}
}
























