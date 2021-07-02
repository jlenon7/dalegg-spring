package com.demomvc.curso.boot.publishers;

import com.demomvc.curso.boot.domain.Invocador;
import com.demomvc.curso.boot.listeners.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<String, List<EventListener>> listeners = new HashMap<>();

    public EventManager(String... operations) {
        for (String operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public void subscribe(String eventType, EventListener listener) {
        List<EventListener> events = listeners.get(eventType);

        events.add(listener);
    }

    public void unsubscribe(String eventType, EventListener listener) {
        List<EventListener> events = listeners.get(eventType);

        events.remove(listener);
    }

    public void notify(String eventType, Invocador invocador) throws Exception {
        List<EventListener> events = listeners.get(eventType);

        for (EventListener listener : events) {
            listener.run(eventType, invocador);
        }
    }
}
