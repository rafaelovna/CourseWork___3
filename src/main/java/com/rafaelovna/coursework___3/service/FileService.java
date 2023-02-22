package com.rafaelovna.coursework___3.service;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;


public interface FileService {

    <T> List<T> uploadFromFile(MultipartFile file, Path path, TypeReference<List<T>> typeReference) throws IOException;

    <T> List<T> readFromFile(Path path, TypeReference <List<T>> typeReference);

    void uploadFile(MultipartFile file, Path path) throws IOException;

    void cleanDataFile(Path path);

    <T> Path saveToFile(T content, Path path) throws IOException;

    File exportFile() throws IOException;

    void importFile(MultipartFile file) throws IOException;

    File prepareRecipesTxt() throws IOException;
}


