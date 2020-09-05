package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Composer;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ComposerDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(Composer composer) {
        sqlSession.insert("com.iw.cf.mybatis.Composer.insert", composer);
    }

    public void deleteAll() {
        sqlSession.delete("com.iw.cf.mybatis.Composer.deleteAll");
    }

    public Composer getById(Long id) {
        return sqlSession.selectOne("com.iw.cf.mybatis.Composer.getById", id);
    }
}
