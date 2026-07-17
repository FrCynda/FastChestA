package foundationgames.enhancedblockentities.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public enum EBEEvents {;
    public static final Event<Runnable> RESOURCE_RELOAD = new Event<>(callbacks -> () -> {
        for (var event : callbacks) {
            event.run();
        }
    });

    public static final class Event<T> {
        private final List<T> callbacks = new CopyOnWriteArrayList<>();
        private final T invoker;

        public Event(Function<List<T>, T> combiner) {
            this.invoker = combiner.apply(this.callbacks);
        }

        public void register(T callback) {
            this.callbacks.add(callback);
        }

        public T invoker() {
            return this.invoker;
        }
    }
}
