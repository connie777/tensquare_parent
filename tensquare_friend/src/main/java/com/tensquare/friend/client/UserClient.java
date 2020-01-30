package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @ClassName UserClient
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/30 21:10
 * @Version 1.0
 **/
@FeignClient("tensquare-user")
public interface UserClient {
    @PutMapping("/user/updateFansAndFllow/{userId}/{friendId}/{x}")
    public void updateFansAndFllow(@PathVariable("userId") String userId,
                                   @PathVariable("friendId") String friendId,
                                   @PathVariable("x") int x);
}
