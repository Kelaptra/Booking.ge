package com.example.demo.services;

import com.example.demo.message.Request.AddRoomRequest;
import com.example.demo.message.Request.RoomDeleteRequest;
import com.example.demo.message.Request.UpdateRoomRequest;
import com.example.demo.message.Response.GetRoomsResponse;
import com.example.demo.dto.RoomDto;
import com.example.demo.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    @Autowired
    @Qualifier("DBS")
    private DBServiceInterface db;
    @Autowired
    private Converter converter;

    public GetRoomsResponse getAllRooms(int hotelId) {
        return converter.generateGetRoomsResponse(db.getRooms(hotelId));
    }

    public RoomDto getRoomById(int id) {
        return converter.toRoomDto(db.getRoom(id));
    }

    public RoomDto addRoom(int hotelId, AddRoomRequest addRoomRequest) {
        return converter.toRoomDto(db.addRoom(hotelId, addRoomRequest));
    }
    public RoomDto updateRoom(UpdateRoomRequest updateRoomRequest) {
        return converter.toRoomDto(db.updateRoom(updateRoomRequest));
    }
    public RoomDto deleteRoom(RoomDeleteRequest roomDeleteRequest) {
        return converter.toRoomDto(db.deleteRoom(roomDeleteRequest));
    }

}
