package eliza.example;

import eliza.eventapi.EventManager;
import eliza.example.event.ExampleEvent;

public class Main {

    private static final EventManager eventManager = new EventManager();

    public static void main(String[] args) {
        eventManager.register(ExampleClass.getInstance());
        eventManager.handle(new ExampleEvent());
    }
}
