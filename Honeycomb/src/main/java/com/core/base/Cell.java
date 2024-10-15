package com.core.base;

public interface Cell {
    default void init() {

    }

    default void update() {

    }

    default boolean isFinished() {
        return false;
    }
}
