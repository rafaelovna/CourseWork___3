package com.rafaelovna.coursework___3.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.repository.SocksRepository;
import com.rafaelovna.coursework___3.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final ObjectMapper objectMapper;
    private final SocksRepository socksRepository;

    @Value("${path.to.data.file}")
    public String dataFilePath;

    @Value("${name.of.data.file}")
    public String dataFileName;


    @Override
    public <T> Path saveToFile(T content, Path path) throws IOException {
        String json = objectMapper.writeValueAsString(content);
        cleanDataFile(path);
        return Files.writeString(path, json);
    }


    @Override
    public <T> List<T> uploadFromFile(MultipartFile file, Path path, TypeReference<List<T>> typeReference) throws IOException {
        uploadFile(file, path);
        return readFromFile(path, typeReference);
    }


    @Override
    public <T> List<T> readFromFile(Path path, TypeReference <List<T>> typeReference) {
        try {
            String json = Files.readString(path);
            if (json.isEmpty()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(json, typeReference);
        } catch (NoSuchFileException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void uploadFile(MultipartFile file, Path path) throws IOException {
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(path, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)) {
            bis.transferTo(bos);
        }
    }


    @Override
    public void cleanDataFile(Path path) {
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File exportFile() throws IOException {
        return saveToFile(socksRepository.getList(), Path.of(dataFilePath, dataFileName)).toFile();
    }

    @Override
    public void importFile(MultipartFile file) throws IOException {
        List<SocksBatch> socksBatches = uploadFromFile(file, Path.of(dataFilePath, dataFileName), new TypeReference<List<SocksBatch>>() {});
        socksRepository.replace(socksBatches);
    }

}
