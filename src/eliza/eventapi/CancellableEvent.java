package eliza.eventapi;

public class CancellableEvent {

    private boolean cancelled;

    public boolean isCancelled() {
        return cancelled;
    }

    public void cancel() {
        cancelled = true;
    }
}
