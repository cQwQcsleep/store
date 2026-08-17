package com.intellij.util.messages;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface MessageBusOwner {
    Object createListener(ListenerDescriptor listenerDescriptor);

    boolean isDisposed();

    default boolean isParentLazyListenersIgnored() {
        return false;
    }
}
