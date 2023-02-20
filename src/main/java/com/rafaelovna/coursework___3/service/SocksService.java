package com.rafaelovna.coursework___3.service;

import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;

public interface SocksService {

    void purchasesOfGoods(SocksBatch socksBatch);

    int editOfGoods(SocksBatch socksBatch);

    int deleteOffOfDefectiveGoods (SocksBatch socksBatch);

    int getOfGoods(SocksColor socksColor, SocksSize socksSize, int cottonMin, int CottonMax);
}
