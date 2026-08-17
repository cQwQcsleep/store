package io.github.rosemoe.sora.event;

import io.github.rosemoe.sora.event.Event;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface EventReceiver<T extends Event> {
    void onReceive(T t, Unsubscribe unsubscribe);
}
