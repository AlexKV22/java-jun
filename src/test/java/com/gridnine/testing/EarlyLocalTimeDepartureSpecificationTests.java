package com.gridnine.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class EarlyLocalTimeDepartureSpecificationTests {
    private List<Segment> segments;
    private Flight flight;
    private final EarlyLocalTimeDepartureSpecification earlyLocalTimeDepartureSpecification = new EarlyLocalTimeDepartureSpecification(LocalDateTime.now());

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
    void validEarlyLocalTimeDepartureSpecificationTest() {
        Segment segment = new Segment(LocalDateTime.now().plusHours(10),LocalDateTime.now().plusHours(15));
        segments.add(segment);

        boolean satisfiedBy = earlyLocalTimeDepartureSpecification.isSatisfiedBy(flight);
        Assertions.assertTrue(satisfiedBy);
    }

    @Test
    void invalidEarlyLocalTimeDepartureSpecificationTest() {
        Segment segment = new Segment(LocalDateTime.now().minusHours(15),LocalDateTime.now().minusHours(10));
        segments.add(segment);

        boolean satisfiedBy = earlyLocalTimeDepartureSpecification.isSatisfiedBy(flight);
        Assertions.assertFalse(satisfiedBy);
    }

    @Test
    void earlyLocalTimeDepartureSpecificationWithNullTest() {
        boolean satisfiedBy = earlyLocalTimeDepartureSpecification.isSatisfiedBy(null);
        Assertions.assertFalse(satisfiedBy);
    }
}
