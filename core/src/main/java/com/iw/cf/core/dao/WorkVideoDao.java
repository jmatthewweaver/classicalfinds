package com.iw.cf.core.dao;

import com.iw.cf.core.dto.WorkVideo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkVideoDao {
    @Autowired
    private SqlSession sqlSession;

    public void insert(WorkVideo workVideo) {
        sqlSession.insert("com.iw.cf.mybatis.WorkVideo.insert", workVideo);
    }

    public void deleteForWork(Long workId) {
        sqlSession.delete("com.iw.cf.mybatis.WorkVideo.deleteForWork", workId);
    }
}
