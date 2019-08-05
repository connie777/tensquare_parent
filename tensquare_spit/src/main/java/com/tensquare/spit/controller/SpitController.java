package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import sun.security.provider.ConfigFile;

/**
 * @ClassName SpitController
 * @Description TODO
 * @Author Silence
 * @Date 2019/8/5 15:59
 * @Version 1.0
 **/
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    @PostMapping
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true,StatusCode.OK,"保存成功");
    }

    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,
                                 @PathVariable int size){
        Page<Spit> pageData = spitService.findByParentid(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(pageData.getTotalElements(),pageData.getContent()));
    }

    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        //判断当前用户是否已点赞，暂时写死userid
        String userid="111";
        if(redisTemplate.opsForValue().get("thumbup_"+userid)!=null){
            return new Result(false,StatusCode.REPERROR,"不能重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid,1);
        return new Result(true,StatusCode.OK,"点赞成功");
    }
















}
