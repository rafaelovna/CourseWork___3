package com.rafaelovna.coursework___3.repository;

import com.rafaelovna.coursework___3.model.Socks;
import com.rafaelovna.coursework___3.model.SocksBatch;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Репозиторий для группирования товара с определенными свойствами
 * (цвет, размер, содержание хлопка) и количеством
 */
@Repository
public class SocksRepositoryImpl implements SocksRepository {

    private final HashMap<Socks, Integer> socksMap = new HashMap<>();

    /**
     * Если товар есть мы к имующейся группе носков добавляем товар с такими же свойствами
     * иначе, просто добавляем партию.
     * @param socksBatch параметр для сохранения партии
     */
    @Override
    public void save(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            socksMap.replace(socks, socksMap.get(socks) + socksBatch.getQuantity());
        } else {
            socksMap.put(socks, socksBatch.getQuantity());
        }
    }

    /**
     * Списание или выдача товара
     * Ищем по ключу товар, если количество возвращаемого товара больше количества товара в партии
     * мы отнимаем из первого второе и возвращаем количество партии.
     * Иначе просто удаляем, а если при запросе товаров нет, возвращаем 0.
     * @param socksBatch параметр для удаления из партии товара
     * @return возвращаем количество товара.
     */
    @Override
    public int remove(SocksBatch socksBatch) {
        Socks socks = socksBatch.getSocks();
        if (socksMap.containsKey(socks)) {
            int quantity = socksMap.get(socks);
            if (quantity > socksBatch.getQuantity()) {
                socksMap.replace(socks, socksMap.get(socks) - socksBatch.getQuantity());
                return socksBatch.getQuantity();
            } else {
                socksMap.remove(socks);
                return quantity;
            }
        }
        return 0;
    }

    /**
     * @return возвращаем нашу мапу
     */
    @Override
    public Map<Socks, Integer> getAll() {
        return socksMap;
    }


}
