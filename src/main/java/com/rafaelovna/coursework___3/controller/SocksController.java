package com.rafaelovna.coursework___3.controller;

import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/socks")
@Tag(name = "API для учета носков", description = "Регистрация прихода, отпуска со склада, списание брака, подсчет количества")
public class SocksController {


    @Operation(summary = "Регистрация прихода товара на склад.")
    @ApiResponse(responseCode = "200", description = "Удалось добавить приход.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    @PostMapping("/")
    public ResponseEntity<Void> purchasesOfGoods(@RequestBody SocksBatch socksBatch) {
        return null;
    }

    @Operation(summary = "Регистрация отпуска товара со склада.")
    @ApiResponse(responseCode = "200", description = "Удалось произвести отпуск носков со склада.")
    @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат.")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    @PutMapping("/")
    public ResponseEntity<Void> editOfGoods(@RequestBody SocksBatch socksBatch) {
        return null;
    }

    @Operation(summary = "Общее количество носков на складе.")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    @GetMapping("/")
    public ResponseEntity<Void> getOfGoods(@RequestParam SocksColor socksColor,
                                               @RequestParam SocksSize socksSize,
                                               @RequestParam int cottonMin,
                                               @RequestParam int cottonMax) {
        return null;
    }

    @Operation(summary = "Регистрация списания испорченных (бракованных) носков.")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    @DeleteMapping("/")
    public ResponseEntity<Void> deleteOffOfDefectiveGoods(@RequestBody SocksBatch socksBatch) {
        return null;
    }


}
