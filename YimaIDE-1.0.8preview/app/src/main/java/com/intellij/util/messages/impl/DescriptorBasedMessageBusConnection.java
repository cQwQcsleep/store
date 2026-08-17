package com.intellij.util.messages.impl;

import com.intellij.openapi.extensions.PluginDescriptor;
import com.intellij.util.messages.Topic;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ$\u0010\u000b\u001a\u00020\f2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u000e\u0010\r\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u000eH\u0016J\u001a\u0010\u000f\u001a\u00020\f2\u0010\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/intellij/util/messages/impl/DescriptorBasedMessageBusConnection;", "Lcom/intellij/util/messages/impl/MessageBusImpl$MessageHandlerHolder;", "module", "Lcom/intellij/openapi/extensions/PluginDescriptor;", "topic", "Lcom/intellij/util/messages/Topic;", "handlers", "", "", "<init>", "(Lcom/intellij/openapi/extensions/PluginDescriptor;Lcom/intellij/util/messages/Topic;Ljava/util/List;)V", "collectHandlers", "", "result", "", "disconnectIfNeeded", "predicate", "Ljava/util/function/Predicate;", "Ljava/lang/Class;", "isDisposed", "", "()Z", "toString", "", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DescriptorBasedMessageBusConnection implements MessageBusImpl.MessageHandlerHolder {
    public final List<Object> handlers;
    public final PluginDescriptor module;
    public final Topic<?> topic;

    public DescriptorBasedMessageBusConnection(PluginDescriptor pluginDescriptor, Topic<?> topic, List<? extends Object> list) {
        pluginDescriptor.getClass();
        topic.getClass();
        list.getClass();
        this.module = pluginDescriptor;
        this.topic = topic;
        this.handlers = list;
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl.MessageHandlerHolder
    public void collectHandlers(Topic<?> topic, List<? super Object> result) {
        topic.getClass();
        result.getClass();
        if (this.topic == topic) {
            result.addAll(this.handlers);
        }
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl.MessageHandlerHolder
    public boolean isDisposed() {
        return false;
    }

    public String toString() {
        return "DescriptorBasedMessageBusConnection(handlers=" + this.handlers + ')';
    }
}
