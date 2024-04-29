package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hotel {

    private Integer id;
    private String name;
    private Integer roomCount;
    private List<Room> roomList;
    private Integer availableRoomCount;
    private List<Integer> availableRoomIdList;

    public Hotel(String name){
        this.name = name;
    }

    public Hotel(String name, List<Room> roomList, List<Integer> availableRoomIdList) {
        this.name = name;
        this.roomCount = roomList.size();
        this.roomList = roomList;
        this.availableRoomCount = availableRoomIdList.size();
        this.availableRoomIdList = availableRoomIdList;
    }

    public List<Integer> getAvailableRoomNumberList(){
        List<Integer> roomNumberList = new ArrayList<>();
        for(Room room : roomList){
            if(room.isAvailable()){
                roomNumberList.add(room.getRoomNumber());
            }
        }
        return roomNumberList;
    }
}
