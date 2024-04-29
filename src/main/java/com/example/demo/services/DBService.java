package com.example.demo.services;

import com.example.demo.message.Request.*;
import com.example.demo.Model.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Qualifier("DBS")
public class DBService implements DBServiceInterface {
    private Map<Integer, Hotel> hotelsMap = new ConcurrentHashMap<>();
    private Map<Integer, Room> roomsMap = new ConcurrentHashMap<>();

    private Integer hotelLastId = -1;
    private Integer roomLastId = -1;

    @Override
    public Hotel getHotel(Integer id) {
        return hotelsMap.get(id);
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelsMap.values().stream().toList();
    }

    @Override
    public Hotel addHotel(AddHotelRequest addHotelRequest) {
        Hotel hotel = Hotel.builder()
                .id(++hotelLastId)
                .name(addHotelRequest.getHotelName())
                .roomCount(0)
                .roomList(new ArrayList<>())
                .availableRoomCount(0)
                .availableRoomIdList(new ArrayList<>())
                .build();
        hotelsMap.put(hotel.getId(), hotel);
        return hotel;
    }

    @Override
    public Hotel updateHotel(UpdateHotelRequest updateHotelRequest) {
        Hotel oldHotel = hotelsMap.get(updateHotelRequest.getHotelId());
        oldHotel.setName(updateHotelRequest.getHotelName());
        return hotelsMap.replace(oldHotel.getId(), oldHotel);
    }

    @Override
    public Hotel deleteHotel(Integer id) {
        return hotelsMap.remove(id);
    }

    @Override
    public Room getRoom(Integer roomId) {
        return roomsMap.get(roomId);
    }

    @Override
    public List<Room> getRooms(Integer hotelId) {
        return hotelsMap.get(hotelId).getRoomList();
    }

    @Override
    public Room addRoom(Integer hotelId, AddRoomRequest addRoomRequest) {
        Room room = Room.builder()
                .id(++roomLastId)
                .roomNumber(addRoomRequest.getRoomNumber())
                .available(true)
                .hotel(hotelsMap.get(hotelId))
                .build();
        hotelsMap.get(hotelId).getRoomList().add(room);
        hotelsMap.get(hotelId).setRoomCount(hotelsMap.get(hotelId).getRoomList().size());
        hotelsMap.get(hotelId).getAvailableRoomIdList().add(room.getId());
        hotelsMap.get(hotelId).setAvailableRoomCount(hotelsMap.get(hotelId).getAvailableRoomIdList().size());
        roomsMap.put(room.getId(), room);
        return room;
    }

    @Override
    public Room updateRoom(UpdateRoomRequest updateRoomRequest) {
        Room oldRoom = getRoom(updateRoomRequest.getRoomId());
        Room updatedRoom = Room.builder()
                .id(updateRoomRequest.getRoomId())
                .roomNumber(oldRoom.getRoomNumber())
                .hotel(oldRoom.getHotel())
                .available(updateRoomRequest.isAvailable())
                .build();
        if(checkIfUpdatable(oldRoom, updatedRoom)){
            updateRoomList(oldRoom, updatedRoom);
            updateAvailableRoomIdList(oldRoom, updatedRoom);
            updateAvailableRoomCount(updatedRoom);
            roomsMap.replace(updatedRoom.getId(), updatedRoom);
        }
        return updatedRoom;
    }

    @Override
    public Room deleteRoom(RoomDeleteRequest roomDeleteRequest) {
        hotelsMap.get(roomDeleteRequest.getHotelId()).getRoomList().remove(roomsMap.get(roomDeleteRequest.getRoomId()));
        hotelsMap.get(roomDeleteRequest.getHotelId()).getAvailableRoomIdList().remove(roomDeleteRequest.getRoomId());
        updateRoomCounts(roomDeleteRequest.getRoomId());
        return roomsMap.remove(roomDeleteRequest.getRoomId());
    }

    private void updateRoomCounts(Integer roomId) {
        hotelsMap.get(roomsMap.get(roomId).getHotel().getId()).setRoomCount(
                hotelsMap.get(roomsMap.get(roomId).getHotel().getId()).getRoomList().size());
        hotelsMap.get(roomsMap.get(roomId).getHotel().getId()).setAvailableRoomCount(
                hotelsMap.get(roomsMap.get(roomId).getHotel().getId()).getAvailableRoomIdList().size());
    }

    private boolean checkIfUpdatable(Room oldRoom, Room updatedRoom){
        return oldRoom.isAvailable() != updatedRoom.isAvailable();
    }

    private void updateRoomList(Room oldRoom, Room updatedRoom){
        hotelsMap.get(updatedRoom.getHotel().getId())
                .getRoomList()
                .set(updatedRoom.getHotel().getRoomList().indexOf(oldRoom), updatedRoom);
    }

    private void updateAvailableRoomIdList(Room oldRoom, Room updatedRoom){
        if(updatedRoom.isAvailable()){
            hotelsMap.get(updatedRoom.getHotel().getId())
                    .getAvailableRoomIdList()
                    .add(updatedRoom.getId());
        }
        else {
            hotelsMap.get(updatedRoom.getHotel().getId())
                    .getAvailableRoomIdList()
                    .remove(updatedRoom.getHotel().getAvailableRoomIdList().indexOf(oldRoom.getId()));
        }
    }

    private void updateAvailableRoomCount(Room updatedRoom) {
        hotelsMap.get(updatedRoom.getHotel().getId())
                .setAvailableRoomCount(
                        updatedRoom
                                .getHotel()
                                .getAvailableRoomIdList()
                                .size());
    }

}
