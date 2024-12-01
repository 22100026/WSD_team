package org.example.hw3.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CrudDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String CRUD_INSERT = "insert into crud (name, dorm, phone, student, bday, gender, city, etc, view) values(?,?,?,?,?,?,?,?,1)";
    private final String CRUD_UPDATE = "update crud set name=?, dorm=?, phone=?, student=?, bday=?, gender=?, city=?, etc=? where id=?";
    private final String CRUD_DELETE = "delete from crud where id=?";
    private final String CRUD_SELECT = "select * from crud where id =?";
    private final String CRUD_SELECTALL = "select * from crud";
    private final String UPDATE_VIEW = "update crud set view=view+1 where id=?";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insertCrud(CrudVO vo) {
        return jdbcTemplate.update(CRUD_INSERT, vo.getName(), vo.getDorm(), vo.getPhone(), vo.getStudent(),vo.getBday(), vo.getGender(), vo.getCity(), vo.getEtc());
    }

    public int updateCrud(CrudVO vo) {
        return jdbcTemplate.update(CRUD_UPDATE, vo.getName(), vo.getDorm(), vo.getPhone(), vo.getStudent(),vo.getBday(), vo.getGender(), vo.getCity(), vo.getEtc(), vo.getId());
    }

    public int deleteCrud(int id) {
        return jdbcTemplate.update(CRUD_DELETE, id);
    }

    public CrudVO getCrud(int id) {
        return jdbcTemplate.queryForObject(CRUD_SELECT, new Object[] {id}, new BeanPropertyRowMapper<CrudVO>(CrudVO.class));
    }

    public List<CrudVO> getListCrud() {
        return jdbcTemplate.query(CRUD_SELECTALL, new RowMapper<CrudVO>() {

            @Override
            public CrudVO mapRow(ResultSet set, int rowNum) throws SQLException {
                CrudVO vo = new CrudVO();
                vo.setId(set.getInt("id"));
                vo.setName(set.getString("name"));
                vo.setDorm(set.getInt("dorm"));
                vo.setPhone(set.getString("phone"));
                vo.setStudent(set.getInt("student"));
                vo.setBday(set.getString("bday"));
                vo.setGender(set.getString("gender"));
                vo.setCity(set.getString("city"));
                vo.setEtc(set.getString("etc"));
                return vo;
            }
        });
    }

    public List<CrudVO> searchCrud(String category, String search) {
        String CRUD_SEARCH = "select * from crud where " + category + " like ?";
        return jdbcTemplate.query(CRUD_SEARCH, new Object[] {search}, new RowMapper<CrudVO>() {

            @Override
            public CrudVO mapRow(ResultSet set, int rowNum) throws SQLException {
                CrudVO vo = new CrudVO();
                vo.setId(set.getInt("id"));
                vo.setName(set.getString("name"));
                vo.setDorm(set.getInt("dorm"));
                vo.setPhone(set.getString("phone"));
                vo.setStudent(set.getInt("student"));
                vo.setBday(set.getString("bday"));
                vo.setGender(set.getString("gender"));
                vo.setCity(set.getString("city"));
                vo.setEtc(set.getString("etc"));
                return vo;
            }
        });
    }

    public void updateView(int id){
        jdbcTemplate.update(UPDATE_VIEW, id);
    }

}
