package com.rzp.entity.utils;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.rzp.entity.pojo.Message;
import com.rzp.entity.pojo.ResultMessage;

public class MessageUtils {

    public static String getMessage(boolean isSystem,String fromName,Object message){
        ResultMessage result = new ResultMessage();
        result.setSystem(isSystem);
        result.setMessage(message);
        if(fromName != null) result.setFromName(fromName);

        String res = JSONUtil.toJsonStr(result);
        return res;
    }


}
