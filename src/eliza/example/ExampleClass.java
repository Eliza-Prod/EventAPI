package eliza.example;

import eliza.eventapi.Event;
import eliza.eventapi.api.EventListener;
import eliza.example.event.ExampleEvent;

public class ExampleClass {

    public static final ExampleClass instance = new ExampleClass();

    public static ExampleClass getInstance() {
        return instance;
    }

    private final EventListener eventListener = new EventListener() {
        @Override
        public void onEvent(Event e) {
            if (e instanceof ExampleEvent) {
                System.out.println("Hello World");
            }
        }
    };
}
