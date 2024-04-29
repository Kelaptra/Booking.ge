package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelDto {

    private String name;
    private Integer roomCount;
    private Integer availableRoomCount;
    private List<Integer> availableRooms; // room numbers (not id)

}
