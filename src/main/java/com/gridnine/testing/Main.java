package com.gridnine.testing;

import com.gridnine.testing.filter.Filter;
import com.gridnine.testing.filtration.Filtration;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        List<Specification<Flight>> rules = List.of(new ArrivalBeforeDepartureSpecification(), new EarlyLocalTimeDepartureSpecification(LocalDateTime.now()), new MoreTwoHoursBetweenFlightsSpecification());
        Filter<Flight> filter = new Filter<>(rules);

        Filtration<Flight> filtration = new Filtration<>(flights, List.of(filter));
        Map<Specification<Flight>, List<Flight>> result = filtration.startFilter();
        for (Map.Entry<Specification<Flight>, List<Flight>> entry : result.entrySet()) {
            String format = String.format("Rule: %s, flights: %s", entry.getKey().getClass().getSimpleName(), entry.getValue().toString());
            System.out.println(format);
        }
    }
}
