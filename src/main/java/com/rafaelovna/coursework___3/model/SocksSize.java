package com.rafaelovna.coursework___3.model;

/**
 * Размер носков
 */
public enum SocksSize {

    S("36-40"),
    M("38-42"),
    L("40-44"),
    Xl("42-48");

    private String size;

    SocksSize(String size) {
        this.size = size;
    }
}
