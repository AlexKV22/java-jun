package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;

class ArrivalBeforeDepartureSpecification implements Specification<Flight> {
    @Override
    public boolean isSatisfiedBy(Flight candidate) {
        if (candidate == null) {
            return false;
        }
        List<Segment> segments = candidate.getSegments();
        for (Segment segment : segments) {
            LocalDateTime departureDate = segment.getDepartureDate();
            LocalDateTime arrivalDate = segment.getArrivalDate();
            if (arrivalDate.isBefore(departureDate)) {
                return false;
            }
        }
        return true;
    }
}
