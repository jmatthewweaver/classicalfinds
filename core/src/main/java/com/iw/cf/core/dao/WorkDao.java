package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Work;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Work> getRandomSampling(int limit) {
        return sqlSession.selectList("com.iw.cf.mybatis.Work.getRandomSampling", limit);
    }
}