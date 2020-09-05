package com.iw.cf.core.dao;

import com.iw.cf.core.dto.Genre;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(Genre genre) {
        sqlSession.insert("com.iw.cf.mybatis.Genre.insert", genre);
    }

    public void deleteAll() {
        sqlSession.delete("com.iw.cf.mybatis.Genre.deleteAll");
    }
}
