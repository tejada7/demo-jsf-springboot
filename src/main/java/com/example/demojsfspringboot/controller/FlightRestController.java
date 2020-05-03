package com.example.demojsfspringboot.controller;

import com.example.demojsfspringboot.model.Flight;
import com.example.demojsfspringboot.repository.FlightRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flights")
public class FlightRestController {

  @Autowired
  FlightRepository flightRepository;

  @GetMapping
  public List<Flight> list() {
    return flightRepository.findAll();
  }
}
