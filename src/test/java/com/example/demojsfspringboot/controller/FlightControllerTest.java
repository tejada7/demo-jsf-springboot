package com.example.demojsfspringboot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.example.demojsfspringboot.model.Flight;
import com.example.demojsfspringboot.repository.FlightRepository;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

  @Mock
  FlightRepository flightRepository;

  @InjectMocks
  FlightController flightController;

  @Test
  void fetchAll() {
    given(flightRepository.findAll()).willReturn(new ArrayList<>());

    flightController.fetchAll();

    assertThat(flightController.getFlights()).isEmpty();
  }

  @Test
  void save() {
    given(flightRepository.save(Mockito.any())).willReturn(new Flight());
    given(flightRepository.findAll()).willReturn(new ArrayList() {{
      add(new Flight());
    }});

    flightController.save();

    assertThat(flightController.getFlights().size() > 0).isTrue();
  }
}