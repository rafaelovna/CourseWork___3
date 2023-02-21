package com.rafaelovna.coursework___3.controller;

import com.rafaelovna.coursework___3.controller.dto.ResponseDto;
import com.rafaelovna.coursework___3.model.SocksBatch;
import com.rafaelovna.coursework___3.model.SocksColor;
import com.rafaelovna.coursework___3.model.SocksSize;
import com.rafaelovna.coursework___3.service.SocksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/socks")
@RequiredArgsConstructor
@RestController
@Tag(name = "ПРИЛОЖЕНИЕ ДЛЯ ВЕДЕНИЯ УЧЕТА НОСОЧНЫХ ИЗДЕЛИЙ. ", description = "Регистрация прихода, отпуска со склада, списание брака, подсчет количества товаров")
public class SocksController {

    private final SocksService socksService;


    @PostMapping
    @Operation(summary = "Регистрация прихода товара на склад.")
    @ApiResponse(responseCode = "200", description = "Удалось добавить приход.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponseDto> purchasesOfGoods(@RequestBody SocksBatch socksBatch) {
        socksService.purchasesOfGoods(socksBatch);
        return ResponseEntity.ok(new ResponseDto("Товар успешно добавлен на склад"));
    }


    @PutMapping
    @Operation(summary = "Регистрация отпуска товара со склада.")
    @ApiResponse(responseCode = "200", description = "Удалось произвести отпуск носков со склада.")
    @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве или параметры запроса имеют некорректный формат.")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponseDto> editOfGoods(@RequestBody SocksBatch socksBatch) {
        int goods = socksService.editOfGoods(socksBatch);
        return ResponseEntity.ok(new ResponseDto("Количество успешно списаного товара: " + goods));
    }


    @GetMapping
    @Operation(summary = "Общее количество носков на складе.")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа в виде строкового представления целого числа.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponseDto> getOfGoods(@RequestParam SocksColor socksColor,
                                               @RequestParam SocksSize socksSize,
                                               @RequestParam int cottonMin,
                                               @RequestParam int cottonMax) {
        int ofGoods = socksService.getOfGoods(socksColor, socksSize, cottonMin, cottonMax);
        return ResponseEntity.ok(new ResponseDto("Количество товара: " + ofGoods));
    }


    @DeleteMapping
    @Operation(summary = "Регистрация списания испорченных (бракованных) носков.")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада.")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны.")
    public ResponseEntity<ResponseDto> deleteOffOfDefectiveGoods(@RequestBody SocksBatch socksBatch) {
        int defectiveGoods = socksService.deleteOffOfDefectiveGoods(socksBatch);
        return ResponseEntity.ok(new ResponseDto("Количество товара списано со склада: " + defectiveGoods));
    }
}
