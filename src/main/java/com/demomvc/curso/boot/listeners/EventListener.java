package com.demomvc.curso.boot.listeners;

import com.demomvc.curso.boot.domain.Invocador;

public interface EventListener {
    void run(String eventType, Invocador invocador) throws Exception;
}
