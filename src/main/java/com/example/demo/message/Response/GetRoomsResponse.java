package com.example.demo.message.Response;

import com.example.demo.dto.RoomDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetRoomsResponse {
    private Integer totalRecords;
    private List<RoomDto> roomList;
}
