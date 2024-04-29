package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDto {
    private Integer roomNumber;
    private boolean available;
    private String HotelName;
}
