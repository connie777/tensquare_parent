package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ArticleSearchController
 * @Description TODO
 * @Author Silence
 * @Date 2019/8/14 15:50
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;

    @PostMapping
    public Result save(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK,"操作成功");
    }

    @GetMapping("/search/{keywords}/{page}/{size}")
    public Result findByTitleLike(@PathVariable String keywords, @PathVariable int page,
                                  @PathVariable int size){
        Page<Article> articlePage=articleSearchService.findByTitleLike(keywords, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Article>(articlePage.getTotalElements(),
                articlePage.getContent()));
    }
}
