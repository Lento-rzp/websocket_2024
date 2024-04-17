package com.rzp.entity.pojo;

import lombok.Data;

// 服务端发给客户端发送的消息
@Data
public class ResultMessage {

    private boolean isSystem;

    private String fromName;

    private Object message;

}
