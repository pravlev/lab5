package com.lev_prav.client.utility;

import com.lev_prav.client.exceptions.IllegalValueException;

@FunctionalInterface
public interface Reader<T> {
    T read() throws IllegalValueException;
}
