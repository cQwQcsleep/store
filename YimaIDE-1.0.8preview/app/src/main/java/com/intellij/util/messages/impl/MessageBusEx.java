package com.intellij.util.messages.impl;

import com.intellij.util.messages.ListenerDescriptor;
import com.intellij.util.messages.MessageBus;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u001e\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH&J\u001a\u0010\n\u001a\u00020\u00032\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\fH&J\b\u0010\u000e\u001a\u00020\u0003H'J\"\u0010\u000f\u001a\u00020\u00032\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00130\u0011H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lcom/intellij/util/messages/impl/MessageBusEx;", "Lcom/intellij/util/messages/MessageBus;", "clearPublisherCache", "", "unsubscribeLazyListeners", "module", "Lcom/intellij/ide/plugins/IdeaPluginDescriptor;", "listenerDescriptors", "", "Lcom/intellij/util/messages/ListenerDescriptor;", "disconnectPluginConnections", "predicate", "Ljava/util/function/Predicate;", "Ljava/lang/Class;", "clearAllSubscriberCache", "setLazyListeners", "map", "Ljava/util/concurrent/ConcurrentMap;", "", "", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MessageBusEx extends MessageBus {
    void setLazyListeners(ConcurrentMap<String, List<ListenerDescriptor>> map);
}
