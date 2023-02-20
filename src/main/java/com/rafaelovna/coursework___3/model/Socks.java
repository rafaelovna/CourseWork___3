package com.rafaelovna.coursework___3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Носки
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socks {

    private SocksColor color;
    private SocksSize size;
    private int cottonPart;

}
