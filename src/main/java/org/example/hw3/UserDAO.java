package org.example.hw3;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {
    @Autowired
    SqlSession sqlSession;
    public UserVO getUser(UserVO vo){

        return sqlSession.selectOne("User.getUser",vo);
    }
}
