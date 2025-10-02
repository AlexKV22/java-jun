package com.gridnine.testing;

public interface Specification<T> {
    boolean isSatisfiedBy(T candidate);
}
