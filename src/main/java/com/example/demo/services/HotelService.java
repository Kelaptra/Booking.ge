package com.example.demo.services;

import com.example.demo.message.Request.AddHotelRequest;
import com.example.demo.message.Request.UpdateHotelRequest;
import com.example.demo.message.Response.GetHotelsResponse;
import com.example.demo.dto.HotelDto;
import com.example.demo.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class HotelService {

    @Autowired
    @Qualifier("DBS")
    private DBServiceInterface db;
    @Autowired
    private Converter converter;

    public GetHotelsResponse getAllHotels() {
        return converter.generateGetHotelsResponse(db.getHotels());
    }

    public HotelDto getHotelById(int id) {
        return converter.toHotelDto(db.getHotel(id));
    }

    public HotelDto addHotel(AddHotelRequest addHotelRequest) {
        return converter.toHotelDto(db.addHotel(addHotelRequest));
    }
    public HotelDto updateHotel(UpdateHotelRequest updateHotelRequest) {
        return converter.toHotelDto(db.updateHotel(updateHotelRequest));
    }
    public HotelDto deleteHotel(int id) {
        return converter.toHotelDto(db.deleteHotel(id));
    }

}
