package eliza.eventapi.api;

import eliza.eventapi.Event;
import eliza.eventapi.api.attributes.EventPriority;

import java.util.function.Supplier;

public abstract class EventListener {

    protected Supplier<Boolean> listenable = () -> true;

    public void setListenable(Supplier<Boolean> listenable) {
        this.listenable = listenable;
    }

    protected EventPriority priority = EventPriority.Normal;

    public EventPriority getPriority() {
        return priority;
    }

    public void setPriority(EventPriority priority) {
        this.priority = priority;
    }

    public EventListener(Supplier<Boolean> listenable, EventPriority priority) {
        this.listenable = listenable;
        this.priority = priority;
    }

    public EventListener(EventPriority priority) {
        this.priority = priority;
    }

    public EventListener() {
    }

    public boolean isListenable() {
        return listenable.get();
    }

    public abstract void onEvent(Event e);
}
