package com.nangman.socket;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessageDto {

    private String roomId;
    private String writer;
    private String message;

}
