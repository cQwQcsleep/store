package io.github.rosemoe.sora.event;

import io.github.rosemoe.sora.event.Event;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class SubscriptionReceipt<R extends Event> {
    private final Class<R> clazz;
    private final EventManager manager;
    private final WeakReference<EventReceiver<R>> receiver;

    public SubscriptionReceipt(EventManager eventManager, Class<R> cls, EventReceiver<R> eventReceiver) {
        this.clazz = cls;
        this.receiver = new WeakReference<>(eventReceiver);
        this.manager = eventManager;
    }

    public void unsubscribe() {
        EventManager.Receivers receivers = this.manager.getReceivers(this.clazz);
        receivers.lock.writeLock().lock();
        try {
            EventReceiver<R> eventReceiver = this.receiver.get();
            if (eventReceiver != null) {
                receivers.receivers.remove(eventReceiver);
            }
        } finally {
            receivers.lock.writeLock().unlock();
        }
    }
}
