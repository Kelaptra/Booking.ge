package com.example.demo.controllers;

import com.example.demo.message.Request.AddHotelRequest;
import com.example.demo.message.Request.UpdateHotelRequest;
import com.example.demo.message.Response.GetHotelsResponse;
import com.example.demo.services.HotelService;
import com.example.demo.dto.HotelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping()
    @ResponseBody
    public GetHotelsResponse getHotels() {
        System.out.println("movida");
        return hotelService.getAllHotels();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public HotelDto getHotel(@PathVariable Integer id) {
        return hotelService.getHotelById(id);
    }

    @PostMapping()
    @ResponseBody
    public HotelDto addHotel(@RequestBody AddHotelRequest addHotelRequest) {
        return hotelService.addHotel(addHotelRequest);
    }

    @PutMapping()
    @ResponseBody
    public HotelDto updateHotel(@RequestBody UpdateHotelRequest updateHotelRequest) {
        return hotelService.updateHotel(updateHotelRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public HotelDto deleteHotel(@PathVariable Integer id) {
        return hotelService.deleteHotel(id);
    }
}
