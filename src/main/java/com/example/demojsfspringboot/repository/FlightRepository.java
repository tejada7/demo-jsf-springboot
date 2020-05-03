package com.example.demojsfspringboot.repository;

import com.example.demojsfspringboot.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface FlightRepository extends JpaRepository<Flight, Long> {

}
