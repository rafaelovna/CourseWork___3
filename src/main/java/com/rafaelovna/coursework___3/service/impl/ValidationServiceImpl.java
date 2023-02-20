package com.rafaelovna.coursework___3.service.impl;

import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;
import com.rafaelovna.coursework___3.service.ValidationService;
import org.springframework.stereotype.Service;

/**
 * Проверяем наши запросы на корректность
 * для этого создаем сервис валидации
 */
@Service
public class ValidationServiceImpl  implements ValidationService {


    @Override
    public boolean validate(SocksBatch socksBatch) {
        return socksBatch.getSocks() != null
                && socksBatch.getQuantity()>0
                && socksBatch.getSocks().getSize() != null
                && socksBatch.getSocks().getColor() != null
                && checkCotton(socksBatch.getSocks().getCottonPart(), socksBatch.getSocks().getCottonPart());
    }

    @Override
    public boolean validate(SocksColor socksColor, SocksSize socksSize, int cottonMin, int cottonMax) {
        return socksColor != null
                && socksSize != null
                && checkCotton(cottonMin, cottonMax);
    }


    /**
     * Проверяем содержание товара по параметрам:
     * @param cottonMin минимального хлопка
     * @param cottonMax максимального хлопка
     * @return возвращаем true или false
     */
    private boolean checkCotton(int cottonMin, int cottonMax) {
        return cottonMin >= 0 && cottonMax <= 100;
    }
}
