package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @ClassName ArticleSearchDao
 * @Description 文章数据访问层接口
 * @Author Silence
 * @Date 2019/8/14 15:46
 * @Version 1.0
 **/
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {
    public Page<Article> findByTitleOrContentLike(String title, String content,
                                                  Pageable pageable);
}
