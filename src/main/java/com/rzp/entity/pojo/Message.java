package com.rzp.entity.pojo;

import lombok.Data;

// 客户端发给服务端发送的消息
@Data
public class Message {

    private String toName;
    private String message;

}
