package com.example.demo.message.Response;

import com.example.demo.dto.HotelDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GetHotelsResponse {
    private Integer totalRecords;
    private List<HotelDto> hotelDtoList;
}
