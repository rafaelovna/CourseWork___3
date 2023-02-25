package com.rafaelovna.coursework___3.service.impl;

import com.rafaelovna.coursework___3.exception.ValidationException;
import com.rafaelovna.coursework___3.model.Socks;
import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;
import com.rafaelovna.coursework___3.repository.SocksRepository;
import com.rafaelovna.coursework___3.service.SocksService;
import com.rafaelovna.coursework___3.service.ValidationService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {


    private final SocksRepository socksRepository;
    private final ValidationService validationService;

    @Override
    public void purchasesOfGoods(SocksBatch socksBatch) {
        checkSocksButch(socksBatch);
        socksRepository.save(socksBatch);
    }

    @Override
    public int editOfGoods(SocksBatch socksBatch) {
        checkSocksButch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int deleteOffOfDefectiveGoods(SocksBatch socksBatch) {
        checkSocksButch(socksBatch);
        return socksRepository.remove(socksBatch);
    }

    @Override
    public int getOfGoods(SocksColor socksColor, SocksSize socksSize, int cottonMin, int cottonMax) {
        if (!validationService.validate(socksColor, socksSize, cottonMin, cottonMax)) {
            throw new ValidationException();
        }

        Map<Socks, Integer> socksMap = socksRepository.getAll();
        int count = 0;
        for (Map.Entry<Socks, Integer> entry : socksMap.entrySet()) {
            Socks socks = entry.getKey();
            if (socks.getColor().equals(socksColor)
                    && socks.getSize().equals(socksSize)
                    && socks.getCottonPart() >= cottonMin
                    && socks.getCottonPart() <= cottonMax) {
                count += entry.getValue();
            }
        }
        return count;
    }

    /**
     * Вынесли валидацию в отдельный метод для удобства
     */
    private void checkSocksButch(SocksBatch socksBatch) {
        if (!validationService.validate(socksBatch)) {
            throw new ValidationException();
        }
    }
}
