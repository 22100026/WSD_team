package org.example.hw3.crud;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CrudDAO {
    @Autowired
    SqlSession sqlSession;
    private final String CRUD_INSERT = "insert into crud (name, dorm, phone, student, bday, gender, city, etc, view) values(?,?,?,?,?,?,?,?,1)";
    private final String CRUD_UPDATE = "update crud set name=?, dorm=?, phone=?, student=?, bday=?, gender=?, city=?, etc=? where id=?";
    private final String CRUD_DELETE = "delete from crud where id=?";
    private final String CRUD_SELECT = "select * from crud where id =?";
    private final String CRUD_SELECTALL = "select * from crud";
    private final String UPDATE_VIEW = "update crud set view=view+1 where id=?";

    //public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        //this.jdbcTemplate = jdbcTemplate;
    //}

    public int insertCrud(CrudVO vo) {
        int count;
        count = sqlSession.insert("Crud.insertCrud", vo);
        return count;
    }

    public int updateCrud(CrudVO vo) {
        int count;
        count = sqlSession.update("Crud.updateCrud", vo);
        return count;
    }

    public int deleteCrud(int id) {
        int count;
        count = sqlSession.delete("Crud.deleteCrud", id);
        return count;
    }


    public CrudVO getCrud(int id) {
        CrudVO vo;
        vo = sqlSession.selectOne("Crud.getCrud",id);
        return vo;
    }



    public List<CrudVO> getCrudList() {
        List<CrudVO> list = sqlSession.selectList("Crud.getCrudList");
        return list;
    }
    public List<CrudVO> getCrudListName() {
        List<CrudVO> list = sqlSession.selectList("Crud.getCrudListName");
        return list;
    }
    public List<CrudVO> getCrudListStudent() {
        List<CrudVO> list = sqlSession.selectList("Crud.getCrudListStudent");
        return list;
    }
    public List<CrudVO> getCrudListBday() {
        List<CrudVO> list = sqlSession.selectList("Crud.getCrudListBday");
        return list;
    }
    public List<CrudVO> getCrudListGender() {
        List<CrudVO> list = sqlSession.selectList("Crud.getCrudListGender");
        return list;
    }


    public List<CrudVO> searchCrud(String category, String search) {
        // MyBatis 동적 SQL 호출
        Map<String, Object> params = new HashMap<>();
        params.put("category", category); // 검색할 컬럼 이름
        params.put("search", search);    // 검색 값

        // MyBatis 매퍼 호출
        List<CrudVO> list = sqlSession.selectList("Crud.searchCrud", params);
        return list;
    }



    public void updateView(int id){
        sqlSession.update("Crud.updateView",id);
    }

}
