package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Era;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EraDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(Era era) {
        sqlSession.insert("com.iw.cf.mybatis.Era.insert", era);
    }

    public void deleteAll() {
        sqlSession.delete("com.iw.cf.mybatis.Era.deleteAll");
    }
}
