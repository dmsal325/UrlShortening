package kr.or.winterdevcamp.urlshortening.dao;

import static kr.or.winterdevcamp.urlshortening.dao.UrlShortingDaoSqls.*;

import java.util.Collections;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import kr.or.winterdevcamp.urlshortening.dto.UrlInfo;

@Repository
public class UrlShortingDao {
	
	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
//    private RowMapper<UrlInfo> rowMapper = BeanPropertyRowMapper.newInstance(UrlInfo.class);

	
	public UrlShortingDao(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("shortingdb")
                .usingGeneratedKeyColumns("id");
    }
	
	//insert
	public Long insert(UrlInfo urlInfo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(urlInfo);
		return insertAction.executeAndReturnKey(params).longValue();
	}
	
	//가장 큰 id 찾기
	public int selectMaxId() {
		return jdbc.queryForObject(SELECT_MAX, Collections.emptyMap(), Integer.class);
	}
	
	//원래 url 찾기
	public String selectUrl(String shortaddress) {
		Map<String, ?> params = Collections.singletonMap("shortaddress", shortaddress);
		return jdbc.queryForObject(SELECT_URL, params, String.class);
	}
	

}
