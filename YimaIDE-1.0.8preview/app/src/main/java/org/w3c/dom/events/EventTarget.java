package org.w3c.dom.events;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface EventTarget {
    void addEventListener(String str, EventListener eventListener, boolean z);

    boolean dispatchEvent(Event event) throws EventException;

    void removeEventListener(String str, EventListener eventListener, boolean z);
}
