package org.eclipse.tm4e.core.internal.utils;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class AbstractListeners<LISTENER, EVENT> {
    private final Set<LISTENER> listeners = new CopyOnWriteArraySet();

    public boolean add(LISTENER listener) {
        return this.listeners.add(listener);
    }

    public int count() {
        return this.listeners.size();
    }

    public void dispatchEvent(final EVENT event) {
        this.listeners.forEach(new Consumer() { // from class: no
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.lambda$dispatchEvent$0(event, obj);
            }
        });
    }

    /* JADX INFO: renamed from: dispatchEvent, reason: merged with bridge method [inline-methods] */
    public abstract void lambda$dispatchEvent$0(EVENT event, LISTENER listener);

    public boolean isEmpty() {
        return this.listeners.isEmpty();
    }

    public boolean isNotEmpty() {
        return !this.listeners.isEmpty();
    }

    public boolean remove(LISTENER listener) {
        return this.listeners.remove(listener);
    }

    public void removeAll() {
        this.listeners.clear();
    }
}
