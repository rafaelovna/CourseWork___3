package com.rafaelovna.coursework___3.repository;

import com.rafaelovna.coursework___3.model.Socks;
import com.rafaelovna.coursework___3.model.SocksBatch;

import java.util.List;
import java.util.Map;

public interface SocksRepository {

    /**
     * Если товар есть мы к имующейся группе носков добавляем товар с такими же свойствами
     * иначе, просто добавляем партию.
     * @param socksBatch параметр для сохранения партии
     */
    void save(SocksBatch socksBatch);

    /**
     * Списание или выдача товара
     * Ищем по ключу товар, если количество возвращаемого товара больше количества товара в партии
     * мы отнимаем из первого второе и возвращаем количество партии.
     * Иначе просто удаляем, а если при запросе товаров нет, возвращаем 0.
     * @param socksBatch параметр для удаления из партии товара
     * @return возвращаем количество товара.
     */
    int remove(SocksBatch socksBatch);

    /**
     * @return возвращаем нашу мапу
     */
    Map<Socks, Integer> getAll();

    /**
     * @return Возвращает список товаров партии
     */
    List<SocksBatch> getList();

    /**
     * На вход принимает json-файл с данными и заменяет ими данные в памяти
     * @param socksBatches
     */
    void replace(List<SocksBatch> socksBatches);
}
