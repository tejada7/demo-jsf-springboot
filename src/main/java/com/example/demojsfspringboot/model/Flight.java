package com.example.demojsfspringboot.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Flight {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String airline;
  private String fromAirport;
  private String toAirport;

  private Date scheduledDateTime;
  private Date estimatedDateTime;
  private Date actualDateTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }

  public String getFromAirport() {
    return fromAirport;
  }

  public void setFromAirport(String fromAirport) {
    this.fromAirport = fromAirport;
  }

  public String getToAirport() {
    return toAirport;
  }

  public void setToAirport(String toAirport) {
    this.toAirport = toAirport;
  }

  public Date getScheduledDateTime() {
    return scheduledDateTime;
  }

  public void setScheduledDateTime(Date scheduledDateTime) {
    this.scheduledDateTime = scheduledDateTime;
  }

  public Date getEstimatedDateTime() {
    return estimatedDateTime;
  }

  public void setEstimatedDateTime(Date estimatedDateTime) {
    this.estimatedDateTime = estimatedDateTime;
  }

  public Date getActualDateTime() {
    return actualDateTime;
  }

  public void setActualDateTime(Date actualDateTime) {
    this.actualDateTime = actualDateTime;
  }
}