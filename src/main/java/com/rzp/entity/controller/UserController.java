package com.rzp.entity.controller;

import cn.hutool.core.util.StrUtil;
import com.rzp.entity.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController extends AbaseController{

    @RequestMapping("/login")
    public ResponseVO login(HttpSession session,String userName){
        if(StrUtil.isEmpty(userName)) return getSuccessResponseVO("请输入你的用户名啊!");
        // 创建token.

        session.setAttribute("userName",userName);
        return getSuccessResponseVO(null);
    }

    @RequestMapping("/getUserName")
    public ResponseVO getUserName(HttpSession session){
        String userName = (String) session.getAttribute("userName");
        return getSuccessResponseVO(userName);
    }

}
