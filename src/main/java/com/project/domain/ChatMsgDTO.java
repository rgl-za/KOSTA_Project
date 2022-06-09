package com.project.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChatMsgDTO {
    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private int cnum; // 채팅방 번호
    private String userId; // 유저 id
    private String chatContent; // 채팅 내용
    private String chatDate; // 채팅 날짜
}
