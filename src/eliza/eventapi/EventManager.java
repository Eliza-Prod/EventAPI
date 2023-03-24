package eliza.eventapi;

import eliza.eventapi.api.EventListener;
import eliza.eventapi.api.attributes.EventPriority;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EventManager {

    private final List<EventListener> eventListeners = new CopyOnWriteArrayList<>();

    public void register(Object object) {
        eventListeners.addAll(getListeners(object));
        sort();
    }

    public void unregister(Object object) {
        for (EventListener eventListener : getListeners(object)) eventListeners.remove(eventListener);
    }

    public void handle(Event event) {
        for (EventListener eventListener : eventListeners)
            if (eventListener.isListenable()) eventListener.onEvent(event);
    }

    private void sort() {

        final List<EventListener> cached = new ArrayList<>(eventListeners);
        eventListeners.clear();

        for (EventPriority priority : EventPriority.values())
            for (EventListener eventListener : cached)
                if (eventListener.getPriority() == priority)
                    eventListeners.add(eventListener);
    }

    private List<EventListener> getListeners(Object object) {
        final List<EventListener> result = new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields())
            if (field.getType() == EventListener.class) {

                final boolean accessible = field.isAccessible();

                if (!accessible) field.setAccessible(true);

                try {
                    result.add((EventListener) field.get(object));
                } catch (Exception ignored) {
                }

                field.setAccessible(accessible);
            }
        return result;
    }
}
