package com.gridnine.testing.filtration;

import com.gridnine.testing.Specification;
import com.gridnine.testing.filter.Filter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Filtration<T> {
    private final List<T> flightList;
    private final List<Filter<T>> filterList;

    public Filtration(final List<T> flightList, final List<Filter<T>> filterList) {
        this.flightList = Objects.requireNonNull(flightList);
        this.filterList = Objects.requireNonNull(filterList);
    }

    public Map<Specification<T>, List<T>> startFilter() {
        ConcurrentHashMap<Specification<T>, List<T>> result = new ConcurrentHashMap<>();
       for (Filter<T> filter : filterList) {
           if (filter == null) {
               throw new NullPointerException("filter is null");
           }
           for (Specification<T> rule : filter.getRules()) {
               if (rule == null) {
                   throw new NullPointerException("rule is null");
               }
               List<T> list = flightList.stream().parallel().filter(flight -> rule.isSatisfiedBy(flight)).toList();
               result.put(rule, list);
           }
       }
       return result;
    }
}

