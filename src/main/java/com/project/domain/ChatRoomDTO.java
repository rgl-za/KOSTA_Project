package com.project.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDTO {
    private int pnum;
    private int cnum;

    // 모르겠다...
//    public static ChatRoom create(int cnum){
//        ChatRoom room = new ChatRoom();
//        room.cnum = cnum;
//        return cnum;
//    }
}
