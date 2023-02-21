package com.rafaelovna.coursework___3.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rafaelovna.coursework___3.exception.ValidationException;
import com.rafaelovna.coursework___3.model.Socks;
import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;
import com.rafaelovna.coursework___3.repository.SocksRepository;
import com.rafaelovna.coursework___3.service.FileService;
import com.rafaelovna.coursework___3.service.SocksService;
import com.rafaelovna.coursework___3.service.ValidationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {

    @Value("${path.to.data.file}")
    public String dataFilePath;

    @Value("${name.of.data.file}")
    public String dataFileName;


    private final SocksRepository socksRepository;
    private final ValidationService validationService;
    private final FileService fileService;
    private final Path path = Path.of(dataFilePath, dataFileName);

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

    @Override
    public File exportFile() throws IOException {
        return fileService.saveToFile(socksRepository.getList(), path).toFile();
    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        List<SocksBatch> socksBatches = fileService.uploadFromFile(file, path, new TypeReference<List<SocksBatch>>() {});
    }

    @Override
    public File prepareRecipesTxt() throws IOException {
        return null;
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
