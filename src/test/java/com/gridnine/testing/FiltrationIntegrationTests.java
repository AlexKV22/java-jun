package com.gridnine.testing;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.filtration.Filtration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class FiltrationIntegrationTests {
    private List<Segment> segments;
    private Flight flight;
    private List<Flight> flights;

    @BeforeEach
    void setUp() {
        Segment segment = new Segment(LocalDateTime.now(),LocalDateTime.now().plusHours(10));
        segments = new ArrayList<>();
        segments.add(segment);
        flight = new Flight(segments);
        flights = new ArrayList<>();
        flights.add(flight);
    }

    @AfterEach
    void tearDown() {
        segments.clear();
        flights.clear();
    }


    @Test
    void validFiltrationTest() {
        Specification<Flight> key = null;
        List<Flight> value = new ArrayList<>();
        List<Specification<Flight>> rules = List.of(new ArrivalBeforeDepartureSpecification());
        Filter<Flight> filter = new Filter<>(rules);
        Filtration<Flight> filtration = new Filtration<>(flights, List.of(filter));
        Map<Specification<Flight>, List<Flight>> result = filtration.startFilter();
        for (Map.Entry<Specification<Flight>, List<Flight>> entry : result.entrySet()) {
            key = entry.getKey();
            value = entry.getValue();
            String format = String.format("Rule: %s, flights: %s", entry.getKey().getClass().getSimpleName(), entry.getValue().toString());
            System.out.println(format);
        }
        Flight flightInList = value.getFirst();
        Assertions.assertEquals(flightInList, flight);
        Assertions.assertEquals(flightInList.getSegments(), segments);
        Assertions.assertEquals(ArrivalBeforeDepartureSpecification.class.getSimpleName(), key.getClass().getSimpleName());
    }

    @Test
    void FiltrationWhereFilterIsNullTest() {
        List<Specification<Flight>> rules = List.of(new ArrivalBeforeDepartureSpecification());
        Filter<Flight> filter = new Filter<>(rules);
        List<Filter<Flight>> filters = new ArrayList<>();
        filters.add(filter);
        filters.add(null);
        Filtration<Flight> filtration = new Filtration<>(flights, filters);
        Assertions.assertThrows(NullPointerException.class, filtration::startFilter);
    }

    @Test
    void FiltrationWhereRuleIsNullTest() {
        List<Specification<Flight>> rules = new ArrayList<>();
        rules.add(null);
        Filter<Flight> filter = new Filter<>(rules);
        List<Filter<Flight>> filters = new ArrayList<>();
        filters.add(filter);
        Filtration<Flight> filtration = new Filtration<>(flights, filters);
        Assertions.assertThrows(NullPointerException.class, filtration::startFilter);
    }
}
