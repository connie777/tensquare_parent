package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName FriendService
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/30 19:13
 * @Version 1.0
 **/
@Service
@Transactional
public class FriendService {
    @Autowired
    FriendDao friendDao;

    @Autowired
    NoFriendDao noFriendDao;

    public int addFriend(String userId, String friendid) {
        //先判断好友表中userid到friendid是否有数据，有就是重复添加好友，返回0
        Friend friend = friendDao.findFriendByUseridAndFriendid(userId, friendid);
        if(friend!=null){
            return 0;
        }
        //添加好友，让好友表中userid到friendid方向的type为0
        friend=new Friend();
        friend.setUserid(userId);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断好友表中friendid到userid是否有数据，如果有，把双方的type都改为1
        Friend friend1=friendDao.findFriendByUseridAndFriendid(friendid,userId);
        if(friend1!=null){
            friendDao.updateIsLike("1",userId,friendid);
            friendDao.updateIsLike("1",friendid,userId);
            /*friend.setIslike("1");
            friend1.setIslike("1");
            friendDao.save(friend);
            friendDao.save(friend1);*/
        }
        return 1;
    }


    public void deleteFriend(String userId, String friendId) {
        //删除好友表中userId-->friendId 数据
        friendDao.deleteFriend(userId,friendId);
        //好友表中friendId-->userId islike为0
        friendDao.updateIsLike("0",friendId,userId);
        //非好友表中添加userId-->friendId 数据
        NoFriend noFriend=new NoFriend();
        noFriend.setUserid(userId);
        noFriend.setFriendid(friendId);
        noFriendDao.save(noFriend);
    }
}
