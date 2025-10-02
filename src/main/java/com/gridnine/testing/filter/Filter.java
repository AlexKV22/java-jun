package com.gridnine.testing.filter;

import com.gridnine.testing.Specification;

import java.util.List;
import java.util.Objects;

public class Filter<T> {
    private List<Specification<T>> rules;

    public Filter(final List<Specification<T>> rules) {
        this.rules = Objects.requireNonNull(rules);
    }

    public List<Specification<T>> getRules() {
        return rules;
    }

    public void setRules(List<Specification<T>> rules) {
        this.rules = rules;
    }

    public void addRule(Specification<T> rule) {
        rules.add(rule);
    }

    public void deleteRule(Specification<T> rule) {
        rules.remove(rule);
    }
}
