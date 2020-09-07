package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Form;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FormDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(Form form) {
        sqlSession.insert("com.iw.cf.mybatis.Form.insert", form);
    }

    public void deleteAll() {
        sqlSession.delete("com.iw.cf.mybatis.Form.deleteAll");
    }

    public List<Form> getWithVideos() {
        return sqlSession.selectList("com.iw.cf.mybatis.Form.getWithVideos");
    }
}
