package com.rafaelovna.coursework___3.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "ИНФОРМАЦИОННЫЙ БЛОК",
        description = "Общая информация о проекте. ФИО ученика. Описание. Дата.")

public class FirstController {

    @GetMapping("/")
    public String helloWorld() {
        return "<h1 style=\"text-align:center\">Приложение запущено</h1>";
    }

    @GetMapping("/info")
    @Operation(
            summary = "Получение информации о проекте."
    )
    public String printInfo() {
        return """
                Ученик: Арутюнян Айкануш Рафаеловна. </br>
                Проект: Приложение для учета закупки и продаж носочных изделий. The application for the accounting of socks. </br>
                Дата создания проекта: 21.02.2023. </br>
                Описание проекта: Простое и удобное веб-приложение, с помощью которого склад можно учитывать и автоматизировать учет товаров на складе интернет-магазина носков.\s
                Пользователь (работник склада имеет возможность:\s

                - учитывать приход и отпуск носков;\s
                - узнать общее количество носков определенного цвета и состава в данный момент времени;\s
                - дополнительно имеет возможность парсить (читать и преобразовывать данные) файлы с данными по товару.""";
    }
}
