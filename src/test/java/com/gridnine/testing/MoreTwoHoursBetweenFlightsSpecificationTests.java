package com.gridnine.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class MoreTwoHoursBetweenFlightsSpecificationTests {
    private List<Segment> segments;
    private Flight flight;
    private final MoreTwoHoursBetweenFlightsSpecification moreTwoHoursBetweenFlightsSpecification = new MoreTwoHoursBetweenFlightsSpecification();

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
    void validMoreTwoHoursBetweenFlightsSpecificationTest() {
        Segment segmentOne = new Segment(LocalDateTime.now(),LocalDateTime.now().plusMinutes(30));
        Segment segmentTwo = new Segment(LocalDateTime.now().plusHours(1),LocalDateTime.now().plusHours(2));
        segments.add(segmentOne);
        segments.add(segmentTwo);

        boolean satisfiedBy = moreTwoHoursBetweenFlightsSpecification.isSatisfiedBy(flight);
        Assertions.assertTrue(satisfiedBy);
    }

    @Test
    void invalidMoreTwoHoursBetweenFlightsSpecificationTest() {
        Segment segmentOne = new Segment(LocalDateTime.now(),LocalDateTime.now().plusMinutes(30));
        Segment segmentTwo = new Segment(LocalDateTime.now().plusHours(3),LocalDateTime.now().plusHours(4));
        segments.add(segmentOne);
        segments.add(segmentTwo);

        boolean satisfiedBy = moreTwoHoursBetweenFlightsSpecification.isSatisfiedBy(flight);
        Assertions.assertFalse(satisfiedBy);
    }

    @Test
    void moreTwoHoursBetweenFlightsSpecificationWithNullTest() {
        boolean satisfiedBy = moreTwoHoursBetweenFlightsSpecification.isSatisfiedBy(null);
        Assertions.assertFalse(satisfiedBy);
    }
}
