package com.rafaelovna.coursework___3.service;

import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;

public interface ValidationService {

    boolean validate(SocksBatch socksBatch);

    boolean validate(SocksColor socksColor, SocksSize socksSize, int cottonMin, int cottonMax);
}
