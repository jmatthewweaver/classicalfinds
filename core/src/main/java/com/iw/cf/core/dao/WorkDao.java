package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Work;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Work> getWorksToProcess() {
        return sqlSession.selectList("com.iw.cf.mybatis.Work.getWorksToProcess");
    }

    public List<Work> search(Long formId, Long genreId, Long composerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("formId", formId);
        params.put("genreId", genreId);
        params.put("composerId", composerId);
        return sqlSession.selectList("com.iw.cf.mybatis.Work.search", params);
    }

    public List<Work> getAll() {
        return sqlSession.selectList("com.iw.cf.mybatis.Work.getAll");
    }

    public void update(Work work) {
        sqlSession.update("com.iw.cf.mybatis.Work.update", work);
    }

    public void clearForms() {
        sqlSession.update("com.iw.cf.mybatis.Work.clearForms");
    }
}
