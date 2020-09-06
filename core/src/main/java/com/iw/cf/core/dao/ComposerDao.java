package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Composer;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    public List<Composer> getByGenre(Long genreId) {
        return sqlSession.selectList("com.iw.cf.mybatis.Composer.getByGenre", genreId);
    }
}
