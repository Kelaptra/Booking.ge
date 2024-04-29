package com.example.demo.Model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room {
    private Integer id;
    private Integer roomNumber;
    private boolean available;
    private Hotel hotel;
}
