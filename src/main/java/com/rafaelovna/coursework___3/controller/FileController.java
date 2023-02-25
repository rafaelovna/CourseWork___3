package com.rafaelovna.coursework___3.controller;


import com.rafaelovna.coursework___3.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/api/socks/files")
@RestController
@Tag(name = "РАБОТА С ФАЙЛАМИ. ", description = "Импорт и экспорт файла товаров")
public class FileController {

    private final FileService fileService;

    @GetMapping("/export")
    @Operation(summary = "Выгрузка файла товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выгрузка файла товаров прошла успешно"),
            @ApiResponse(responseCode = "400", description = "Неверный(плохой) запрос!")
    })
    public ResponseEntity<InputStreamResource> downloadDataFile() {
        try {
            File file = fileService.exportFile();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"TheApplicationForTheAccountingOfSocks.json\"")
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Загрузка файла товаров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Загрузка файла товаров прошла успешно"),
            @ApiResponse(responseCode = "400", description = "Неверный(плохой) запрос!")
    })
    public ResponseEntity<String> uploadDatafile(@RequestParam MultipartFile file) {
        try {
            fileService.importFile(file);
            return ResponseEntity.ok("Файл успешно импортирован.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Ошибка при загрузке файла.");
        }
    }


}
