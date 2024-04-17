package com.rzp.entity.controller;

import com.rzp.entity.vo.ResponseVO;

public class AbaseController {

    protected <T> ResponseVO getSuccessResponseVO(T t){
        ResponseVO resultVO = new ResponseVO();
        resultVO.setData(t);
        resultVO.setMessage("请求成功!!");
        return resultVO;
    }


}
