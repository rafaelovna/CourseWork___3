package com.rafaelovna.coursework___3.model;

/**
 * Цвет носков
 */
public enum SocksColor {
    WHITE("БЕЛЫЙ"),
    BLACK("ЧЕРНЫЙ"),
    RED("КРАСНЫЙ"),
    BLUE("СИНИЙ"),
    YELLOW("ЖЕЛТЫЙ");

    private String text;

    SocksColor(String text) {
        this.text = text;
    }
}
