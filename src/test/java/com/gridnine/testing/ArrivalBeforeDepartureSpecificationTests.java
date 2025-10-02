package com.gridnine.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class ArrivalBeforeDepartureSpecificationTests {
    private List<Segment> segments;
    private Flight flight;
    private final ArrivalBeforeDepartureSpecification arrivalBeforeDepartureSpecification = new ArrivalBeforeDepartureSpecification();

    @BeforeEach
    void setUp() {
        segments = new ArrayList<>();
        flight = new Flight(segments);
    }

    @AfterEach
    void tearDown() {
        segments.clear();
        flight = null;
    }


    @Test
    void validArrivalBeforeDepartureSpecificationTest() {
        Segment segment = new Segment(LocalDateTime.of(2025,10,1, 16, 24),LocalDateTime.of(2025,10,1, 18, 58));
        segments.add(segment);

        boolean satisfiedBy = arrivalBeforeDepartureSpecification.isSatisfiedBy(flight);
        Assertions.assertTrue(satisfiedBy);
    }

    @Test
    void invalidArrivalBeforeDepartureSpecificationTest() {
        Segment segment = new Segment(LocalDateTime.of(2025,10,1, 16, 24),LocalDateTime.of(2025,10,1, 9, 58));
        segments.add(segment);

        boolean satisfiedBy = arrivalBeforeDepartureSpecification.isSatisfiedBy(flight);
        Assertions.assertFalse(satisfiedBy);
    }

    @Test
    void arrivalBeforeDepartureSpecificationWithNullTest() {
        boolean satisfiedBy = arrivalBeforeDepartureSpecification.isSatisfiedBy(null);
        Assertions.assertFalse(satisfiedBy);
    }
}
