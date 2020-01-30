package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.pojo.NoFriend;
import com.tensquare.friend.service.FriendService;
import com.tensquare.friend.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName FriendController
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/30 18:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private FriendService friendService;

    @Autowired
    private NoFriendService noFriendService;

    @Autowired
    private UserClient userClient;

    /**
     * 添加好友或非好友
     * @param friendid 好友id
     * @param type 1：好友 2：非好友
     * @return
     */
    @PutMapping("/like/{friendid}/{type}")
    public Result addFriend(@PathVariable String friendid,@PathVariable String type){
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足");
        }
        String userId=claims.getId();
        //判断是好友还是非好友
        if(type!=null){
            if("1".equals(type)){
                //添加好友
                int flag=friendService.addFriend(userId,friendid);
                if(flag==0){
                    return new Result(false, StatusCode.ERROR,"不能重复添加好友");
                }
                if(flag==1){
                    //处理关注数与粉丝数
                    userClient.updateFansAndFllow(userId,friendid,1);
                    return new Result(true, StatusCode.OK,"添加成功");
                }
            }
            if("2".equals(type)){
                //添加非好友
                int flag=noFriendService.addNoFriend(userId,friendid);
                if(flag==0){
                    return new Result(false, StatusCode.ERROR,"不能重复添加非好友");
                }
                if(flag==1){
                    return new Result(true, StatusCode.OK,"添加成功");
                }
            }
            return new Result(false, StatusCode.ERROR,"参数异常");
        }else{
            return new Result(false, StatusCode.ERROR,"参数异常");
        }
    }

    @DeleteMapping("/{friendId}")
    public Result deleteFriend(@PathVariable String friendId){
        //验证是否登录，并且拿到当前登录的用户id
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足");
        }
        String userId=claims.getId();
        //好友表中删除userId-->friendId
        friendService.deleteFriend(userId,friendId);
        //更新用户和好友的关注与粉丝
        userClient.updateFansAndFllow(userId,friendId,-1);
        return new Result(true,StatusCode.OK,"删除成功");
    }

}
