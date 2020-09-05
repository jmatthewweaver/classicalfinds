package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Work;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(Work work) {
        sqlSession.insert("com.iw.cf.mybatis.Work.insert", work);
    }

    public void deleteAll() {
        sqlSession.delete("com.iw.cf.mybatis.Work.deleteAll");
    }
}
