package com.intellij.util.messages.impl;

import com.intellij.util.ArrayUtilRt;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.messages.MessageHandler;
import com.intellij.util.messages.Topic;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J \u0010\t\u001a\u00020\n\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\b\u0010\u0012\u001a\u00020\nH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u0010\u001a\u00020\fR\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/intellij/util/messages/impl/MessageBusConnectionImpl;", "Lcom/intellij/util/messages/impl/BaseBusConnection;", "Lcom/intellij/util/messages/MessageBusConnection;", "bus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "<init>", "(Lcom/intellij/util/messages/impl/MessageBusImpl;)V", "defaultHandler", "Lcom/intellij/util/messages/MessageHandler;", "subscribe", "", "L", "", "topic", "Lcom/intellij/util/messages/Topic;", "setDefaultHandler", "handler", "dispose", "disconnect", "deliverImmediately", "isMyHandler", "", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MessageBusConnectionImpl extends BaseBusConnection implements MessageBusConnection {
    private MessageHandler defaultHandler;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MessageBusConnectionImpl(MessageBusImpl messageBusImpl) {
        super(messageBusImpl, null);
        messageBusImpl.getClass();
    }

    @Override // com.intellij.util.messages.MessageBusConnection
    public void deliverImmediately() throws Throwable {
        MessageBusImpl messageBusImpl = this.bus;
        if (messageBusImpl == null) {
            MessageBusImpl.LOG.error("Bus is already disposed");
        } else {
            messageBusImpl.deliverImmediately$intellij_platform_core(this);
        }
    }

    public void dispose() {
        MessageBusImpl messageBusImpl = this.bus;
        if (messageBusImpl == null) {
            return;
        }
        this.bus = null;
        this.defaultHandler = null;
        Object[] andSet = this.subscriptions.getAndSet(ArrayUtilRt.EMPTY_OBJECT_ARRAY);
        andSet.getClass();
        messageBusImpl.notifyConnectionTerminated$intellij_platform_core(andSet);
    }

    public final boolean isMyHandler(Topic<?> topic, Object handler) {
        topic.getClass();
        handler.getClass();
        if (this.defaultHandler == handler) {
            return true;
        }
        Object[] objArr = this.subscriptions.get();
        int length = objArr.length;
        for (int i = 0; i < length; i += 2) {
            if (topic == objArr[i] && handler == objArr[i + 1]) {
                return true;
            }
        }
        return false;
    }
}
