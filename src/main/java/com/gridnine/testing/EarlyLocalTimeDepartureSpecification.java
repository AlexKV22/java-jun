package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

class EarlyLocalTimeDepartureSpecification implements Specification<Flight> {
    private final LocalDateTime now;

    public EarlyLocalTimeDepartureSpecification(final LocalDateTime now) {
        this.now = Objects.requireNonNull(now);
    }

    @Override
    public boolean isSatisfiedBy(Flight candidate) {
        if (candidate == null) {
            return false;
        }
        List<Segment> segments = candidate.getSegments();
        for (Segment segment : segments) {
            LocalDateTime departureDate = segment.getDepartureDate();
            if (departureDate.isBefore(now)) {
                return false;
            }
        }
        return true;
    }
}
