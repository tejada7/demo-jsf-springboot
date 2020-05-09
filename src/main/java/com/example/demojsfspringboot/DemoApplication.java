package com.example.demojsfspringboot;

import com.example.demojsfspringboot.controller.FlightController;
import com.example.demojsfspringboot.model.Flight;
import com.sun.faces.config.ConfigureListener;
import java.util.Date;
import javax.faces.webapp.FacesServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

  private final FlightController flightController;

  public DemoApplication(FlightController flightController) {
    this.flightController = flightController;
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public ServletRegistrationBean facesServletRegistration() {
    ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
    registration.setLoadOnStartup(1);
    return registration;
  }

  @Bean
  public ServletContextInitializer servletContextInitializer() {
    return servletContext -> {
      servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
//      servletContext.setInitParameter("primefaces.THEME", "nova-light");
      servletContext.setInitParameter("primefaces.THEME", "hot-sneaks");
    };
  }

  @Bean
  public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
    return new ServletListenerRegistrationBean<>(new ConfigureListener());
  }

  @EventListener(ApplicationStartedEvent.class)
  public void doSomethingAfterStartup() {
    Flight f1 = buildFlight(728349L, "Air France", "ORY", "MAD", new Date(), new Date(), new Date());
    Flight f2 = buildFlight(33243L, "Iberia", "MAD", "VVI", new Date(), new Date(), new Date());
    Flight f3 = buildFlight(346546L, "Emirates", "FRK", "LND", new Date(), new Date(), new Date());
    Flight f4 = buildFlight(123454L, "Transavia", "CDG", "MUN", new Date(), new Date(), new Date());
    Flight f5 = buildFlight(823478L, "Nowëgian", "STK", "ORY", new Date(), new Date(), new Date());
    flightController.save(f1);
    flightController.save(f2);
    flightController.save(f3);
    flightController.save(f4);
    flightController.save(f5);
  }

  private Flight buildFlight(Long id, String airline, String from, String to, Date now, Date now1, Date now2) {
    Flight flight = new Flight();
    flight.setId(id);
    flight.setAirline(airline);
    flight.setFromAirport(from);
    flight.setToAirport(to);
    flight.setScheduledDateTime(now);
    flight.setEstimatedDateTime(now1);
    flight.setActualDateTime(now2);
    return flight;
  }

}