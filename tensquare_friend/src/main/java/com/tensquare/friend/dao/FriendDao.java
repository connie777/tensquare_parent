package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {

    Friend findFriendByUseridAndFriendid(String userid,String frindid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike = ? WHERE userid= ? AND friendid= ?",nativeQuery = true)
    void updateIsLike(String islike,String userid,String friendid);

    @Query(value = "DELETE FROM tb_friend WHERE userid= ? AND friendid= ?",nativeQuery = true)
    @Modifying
    void deleteFriend(String userId, String friendId);
}
