package com.rafaelovna.coursework___3.repository;

import com.rafaelovna.coursework___3.model.Socks;
import com.rafaelovna.coursework___3.model.SocksBatch;

import java.util.Map;

public interface SocksRepository {

    void save(SocksBatch socksBatch);

    int remove(SocksBatch socksBatch);

    Map<Socks, Integer> getAll();
}
