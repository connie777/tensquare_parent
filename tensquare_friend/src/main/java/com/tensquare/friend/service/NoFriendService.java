package com.tensquare.friend.service;

import com.tensquare.friend.dao.NoFriendDao;
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
public class NoFriendService {
    @Autowired
    private NoFriendDao noFriendDao;

    public int addNoFriend(String userId, String friendid) {
        //判断是否已是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndAndFriendid(userId, friendid);
        if(noFriend!=null){
            return 0;
        }else {
            noFriend=new NoFriend();
            noFriend.setUserid(userId);
            noFriend.setFriendid(friendid);
            noFriendDao.save(noFriend);
            return 1;
        }
    }
}
