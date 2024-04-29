package com.example.demo.controllers;

import com.example.demo.message.Request.AddRoomRequest;
import com.example.demo.message.Request.RoomDeleteRequest;
import com.example.demo.message.Request.UpdateRoomRequest;
import com.example.demo.message.Response.GetRoomsResponse;
import com.example.demo.services.RoomService;
import com.example.demo.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/all/{hotelId}")
    @ResponseBody
    public GetRoomsResponse getRooms(@PathVariable Integer hotelId) {
        return roomService.getAllRooms(hotelId);
    }

    @GetMapping("/{roomId}")
    @ResponseBody
    public RoomDto getRoom(@PathVariable Integer roomId) {
        return roomService.getRoomById(roomId);
    }

    @PostMapping("/{hotelId}")
    @ResponseBody
    public RoomDto addRoom(@PathVariable Integer hotelId, @RequestBody AddRoomRequest addRoomRequest) {
        return roomService.addRoom(hotelId, addRoomRequest);
    }

    @PutMapping()
    @ResponseBody
    public RoomDto updateRoom(@RequestBody UpdateRoomRequest updateRoomRequest) {
        return roomService.updateRoom(updateRoomRequest);
    }

    @DeleteMapping()
    @ResponseBody
    public RoomDto deleteRoom(@RequestBody RoomDeleteRequest roomDeleteRequest) {
        return roomService.deleteRoom(roomDeleteRequest);
    }
}
