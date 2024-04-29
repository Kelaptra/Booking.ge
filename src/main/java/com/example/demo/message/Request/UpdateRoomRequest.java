package com.example.demo.message.Request;

import lombok.Data;

@Data
public class UpdateRoomRequest {
    private Integer roomId;
    private boolean available;
}
