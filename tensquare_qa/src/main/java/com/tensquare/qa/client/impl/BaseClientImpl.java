package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @ClassName BaseClientImpl
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/31 14:05
 * @Version 1.0
 **/
@Component
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR,"熔断器启动！");
    }
}
