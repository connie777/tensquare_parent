package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleSearchDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @ClassName ArticleSearchService
 * @Description TODO
 * @Author Silence
 * @Date 2019/8/14 15:48
 * @Version 1.0
 **/
@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    /**
     * 增加文章
     * @param article
     */
    public void add(Article article){
        articleSearchDao.save(article);
    }

    public Page<Article> findByTitleLike(String keywords,int page,int size){
        Pageable pageable=PageRequest.of(page-1,size);
        return articleSearchDao.findByTitleOrContentLike(keywords,keywords,pageable);
    }
}
