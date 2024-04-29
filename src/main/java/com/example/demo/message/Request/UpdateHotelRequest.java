package com.example.demo.message.Request;

import lombok.Data;

@Data
public class UpdateHotelRequest {
    private Integer hotelId;
    private String hotelName;
}
