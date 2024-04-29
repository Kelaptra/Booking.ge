package com.example.demo.services;

import com.example.demo.message.Request.*;
import com.example.demo.Model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DBServiceInterface {

    Hotel getHotel(Integer id);
    List<Hotel> getHotels();
    Hotel addHotel(AddHotelRequest addHotelRequest);
    Hotel updateHotel(UpdateHotelRequest updateHotelRequest);
    Hotel deleteHotel(Integer id);

    Room getRoom(Integer roomId);
    List<Room> getRooms(Integer hotelId);
    Room addRoom(Integer hotelId, AddRoomRequest addRoomRequest);
    Room updateRoom(UpdateRoomRequest updateRoomRequest);
    Room deleteRoom(RoomDeleteRequest deleteRequest);

}
