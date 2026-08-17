package com.intellij.util.messages.impl;

import com.intellij.util.ArrayUtilRt;
import com.intellij.util.messages.SimpleMessageBusConnection;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/intellij/util/messages/impl/SimpleMessageBusConnectionImpl;", "Lcom/intellij/util/messages/impl/BaseBusConnection;", "Lcom/intellij/util/messages/SimpleMessageBusConnection;", "bus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "<init>", "(Lcom/intellij/util/messages/impl/MessageBusImpl;)V", "disconnect", "", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SimpleMessageBusConnectionImpl extends BaseBusConnection implements SimpleMessageBusConnection {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimpleMessageBusConnectionImpl(MessageBusImpl messageBusImpl) {
        super(messageBusImpl, null);
        messageBusImpl.getClass();
    }

    public void disconnect() {
        MessageBusImpl messageBusImpl = this.bus;
        if (messageBusImpl == null) {
            return;
        }
        this.bus = null;
        Object[] andSet = this.subscriptions.getAndSet(ArrayUtilRt.EMPTY_OBJECT_ARRAY);
        andSet.getClass();
        messageBusImpl.notifyConnectionTerminated$intellij_platform_core(andSet);
    }
}
