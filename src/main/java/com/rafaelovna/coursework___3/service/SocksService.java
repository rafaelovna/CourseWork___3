package com.rafaelovna.coursework___3.service;

import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface SocksService {


    /**
     * Если пришла партия товара мы вызываем наш репозиторий и сохраняем партию.
     * @param socksBatch параметр для добавления.
     */
    void purchasesOfGoods(SocksBatch socksBatch);


    /**
     * Метод который позволяет выдать товар.
     * @param socksBatch параметр для выдачи
     * @return возвращаем число
     */
    int editOfGoods(SocksBatch socksBatch);


    /**
     * Метод который позволяет списать брак
     * @param socksBatch параметр для списания
     * @return возвращаем число
     */
    int deleteOffOfDefectiveGoods (SocksBatch socksBatch);

    /**
     * Подсчет общего количества товара по параметрам:
     * @param socksColor цвет,
     * @param socksSize размер,
     * @param cottonMin содержание минимального хлопка,
     * @param cottonMax и содержание максимального хлопка
     * пробегаемся по нашей мапе,
     * @return возвращаем количество товара по параметрам
     */
    int getOfGoods(SocksColor socksColor, SocksSize socksSize, int cottonMin, int cottonMax);



}
