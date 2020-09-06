package com.iw.cf.core.dao;

import com.iw.cf.core.dto.WorkVideo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<WorkVideo> getForWork(Long workId) {
        return sqlSession.selectList("com.iw.cf.mybatis.WorkVideo.getForWork", workId);
    }

    public List<WorkVideo> getAll() {
        return sqlSession.selectList("com.iw.cf.mybatis.WorkVideo.getAll");
    }

    public void update(WorkVideo workVideo) {
        sqlSession.update("com.iw.cf.mybatis.WorkVideo.update", workVideo);
    }
}
