package com.rafaelovna.coursework___3.service.impl;

import com.rafaelovna.coursework___3.exception.ValidationException;
import com.rafaelovna.coursework___3.model.Socks;
import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;
import com.rafaelovna.coursework___3.repository.SocksRepository;
import com.rafaelovna.coursework___3.service.SocksService;
import com.rafaelovna.coursework___3.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class SocksServiceImpl implements SocksService {

    private final SocksRepository socksRepository;
    private final ValidationService validationService;

    /**
     * Если пришла партия товара мы вызываем наш репозиторий и сохраняем партию.
     * @param socksBatch параметр для добавления.
     */
    @Override
    public void purchasesOfGoods(SocksBatch socksBatch) {
        checkSocksButch(socksBatch);
       socksRepository.save(socksBatch);
    }

    /**
     * Метод который позволяет выдать товар.
     * @param socksBatch параметр для выдачи
     * @return возвращаем число
     */
    @Override
    public int editOfGoods(SocksBatch socksBatch) {
        checkSocksButch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    /**
     * Метод который позволяет списать брак
     * @param socksBatch параметр для списания
     * @return возвращаем число
     */
    @Override
    public int deleteOffOfDefectiveGoods(SocksBatch socksBatch) {
        checkSocksButch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    /**
     * Подсчет общего количества товара по параметрам:
     * @param socksColor цвет,
     * @param socksSize размер,
     * @param cottonMin содержание минимального хлопка,
     * @param cottonMax и содержание максимального хлопка
     * пробегаемся по нашей мапе,
     * @return возвращаем количество товара по параметрам
     */
    @Override
    public int getOfGoods(SocksColor socksColor, SocksSize socksSize, int cottonMin, int cottonMax) {
        if (!validationService.validate(socksColor, socksSize,cottonMin, cottonMax)) {
            throw new ValidationException();
        }
        Map<Socks, Integer> socksMap = socksRepository.getAll();
        for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
            Socks socks = entry.getKey();
            if (socks.getColor().equals(socksColor)
                    && socks.getSize().equals(socksSize)
                    && socks.getCottonPart() >= cottonMin
                    && socks.getCottonPart() <= cottonMax) {
                return entry.getValue();
            }
        }
        return 0;
    }

    /**
     * Вынесли валидацию в отдельный метод для удобства
     * @param socksBatch параметр
     */
    private void checkSocksButch(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}
