package com.iw.cf.api.dao;

import com.iw.cf.api.dto.Genre;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GenreDao {

    @Autowired
    private SqlSession sqlSession;

    public void insert(Genre genre) {
        sqlSession.insert("com.iw.cf.Genre", genre);
    }
}
