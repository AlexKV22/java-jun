package com.gridnine.testing;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;

class MoreTwoHoursBetweenFlightsSpecification implements Specification<Flight> {

    @Override
    public boolean isSatisfiedBy(Flight candidate) {
        if (candidate == null) {
            return false;
        }
        long count = 0;
        Segment prev = null;
        List<Segment> segments = candidate.getSegments();
        segments.sort(Comparator.comparing(Segment::getDepartureDate));
        for (Segment segment : segments) {
            if (prev != null) {
                Duration duration = Duration.between(prev.getArrivalDate(), segment.getDepartureDate());
                count += duration.toMinutes();
                if (count > 120) {
                    return false;
                }
            }
            prev = segment;
        }
        return true;
    }
}