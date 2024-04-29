package com.example.demo.message.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDeleteRequest {
    private Integer hotelId;
    private Integer roomId;
}
