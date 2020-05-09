package com.example.demojsfspringboot.controller;

import com.example.demojsfspringboot.model.Flight;
import com.example.demojsfspringboot.repository.FlightRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Named
@ViewScoped
public class FlightController implements Serializable {

  private Flight flight = new Flight();

  private List<Flight> flights = new ArrayList<>();

  @Autowired
  private FlightRepository flightRepository;

  public void fetchAll() {
    flights = flightRepository.findAll();
  }

  public void save() {
    flightRepository.save(flight);
    flight = new Flight();
    flights = flightRepository.findAll();
  }

  public void save(Flight flight) {
    flightRepository.save(flight);
    flights = flightRepository.findAll();
  }

  public void edit(Flight flight) {
    this.flight = flight;
  }

  public void refresh() {
    flight = new Flight();
  }

  public List<Flight> getFlights() {
    return flights;
  }

  public void setFlights(List<Flight> flights) {
    this.flights = flights;
  }

  public Flight getFlight() {
    return flight;
  }

  public void setFlight(Flight flight) {
    this.flight = flight;
  }

}