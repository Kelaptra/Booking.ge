package com.example.demo.util;

import com.example.demo.message.Response.GetHotelsResponse;
import com.example.demo.message.Response.GetRoomsResponse;

import com.example.demo.Model.*;
import com.example.demo.dto.HotelDto;
import com.example.demo.dto.RoomDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converter {

    public HotelDto toHotelDto(Hotel hotel) {
        return HotelDto.builder()
                .name(hotel.getName())
                .roomCount(hotel.getRoomCount())
                .availableRoomCount(hotel.getAvailableRoomCount())
                .availableRooms(hotel.getAvailableRoomNumberList())
                .build();
    }

    public GetHotelsResponse generateGetHotelsResponse(List<Hotel> hotelList) {
        return GetHotelsResponse.builder()
                .totalRecords(hotelList.size())
                .hotelDtoList(hotelList.stream().map(this::toHotelDto).toList())
                .build();
    }

    public RoomDto toRoomDto(Room room) {
        return RoomDto.builder()
                .HotelName(room.getHotel().getName())
                .roomNumber(room.getRoomNumber())
                .available(room.isAvailable())
                .build();
    }

    public GetRoomsResponse generateGetRoomsResponse(List<Room> roomList) {
        return GetRoomsResponse.builder()
                .totalRecords(roomList.size())
                .roomList(roomList.stream().map(this::toRoomDto).toList())
                .build();
    }

}
