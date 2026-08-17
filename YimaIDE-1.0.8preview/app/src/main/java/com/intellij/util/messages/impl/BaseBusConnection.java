package com.intellij.util.messages.impl;

import com.intellij.util.ArrayUtilRt;
import com.intellij.util.messages.Topic;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001B\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J+\u0010\u000e\u001a\u00020\u000f\"\b\b\u0000\u0010\u0010*\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00100\u00122\u0006\u0010\u0013\u001a\u0002H\u0010¢\u0006\u0002\u0010\u0014J$\u0010\u0015\u001a\u00020\u000f2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0000\u0012\u00020\t0\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u000f2\u0010\u0010\u0019\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001b0\u001aH\u0016J\b\u0010\u001c\u001a\u00020\u000fH$J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R<\u0010\u0006\u001a.\u0012*\u0012(\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t \n*\u0014\u0012\u000e\b\u0001\u0012\n \n*\u0004\u0018\u00010\t0\t\u0018\u00010\b0\b0\u00078\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\r\u0082\u0001\u0002\u001f ¨\u0006!"}, d2 = {"Lcom/intellij/util/messages/impl/BaseBusConnection;", "Lcom/intellij/util/messages/impl/MessageBusImpl$MessageHandlerHolder;", "bus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "<init>", "(Lcom/intellij/util/messages/impl/MessageBusImpl;)V", "subscriptions", "Ljava/util/concurrent/atomic/AtomicReference;", "", "", "kotlin.jvm.PlatformType", "isDisposed", "", "()Z", "subscribe", "", "L", "topic", "Lcom/intellij/util/messages/Topic;", "handler", "(Lcom/intellij/util/messages/Topic;Ljava/lang/Object;)V", "collectHandlers", "result", "", "disconnectIfNeeded", "predicate", "Ljava/util/function/Predicate;", "Ljava/lang/Class;", "disconnect", "toString", "", "Lcom/intellij/util/messages/impl/MessageBusConnectionImpl;", "Lcom/intellij/util/messages/impl/SimpleMessageBusConnectionImpl;", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class BaseBusConnection implements MessageBusImpl.MessageHandlerHolder {
    public MessageBusImpl bus;
    protected final AtomicReference<Object[]> subscriptions;

    private BaseBusConnection(MessageBusImpl messageBusImpl) {
        this.bus = messageBusImpl;
        this.subscriptions = new AtomicReference<>(ArrayUtilRt.EMPTY_OBJECT_ARRAY);
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl.MessageHandlerHolder
    public void collectHandlers(Topic<?> topic, List<? super Object> result) {
        topic.getClass();
        result.getClass();
        Object[] objArr = this.subscriptions.get();
        int length = objArr.length;
        for (int i = 0; i < length; i += 2) {
            if (objArr[i] == topic) {
                Object obj = objArr[i + 1];
                obj.getClass();
                result.add(obj);
            }
        }
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl.MessageHandlerHolder
    public boolean isDisposed() {
        return this.bus == null;
    }

    public final <L> void subscribe(Topic<L> topic, L handler) {
        Object[] objArr;
        Object[] objArr2;
        topic.getClass();
        handler.getClass();
        do {
            objArr = this.subscriptions.get();
            if (objArr.length == 0) {
                objArr2 = new Object[]{topic, handler};
            } else {
                int length = objArr.length;
                Object[] objArrCopyOf = Arrays.copyOf(objArr, length + 2);
                objArrCopyOf[length] = topic;
                objArrCopyOf[length + 1] = handler;
                objArr2 = objArrCopyOf;
            }
        } while (!this.subscriptions.compareAndSet(objArr, objArr2));
        MessageBusImpl messageBusImpl = this.bus;
        messageBusImpl.getClass();
        messageBusImpl.notifyOnSubscription$intellij_platform_core(topic);
    }

    public String toString() {
        String string = Arrays.toString(this.subscriptions.get());
        string.getClass();
        return string;
    }

    public /* synthetic */ BaseBusConnection(MessageBusImpl messageBusImpl, DefaultConstructorMarker defaultConstructorMarker) {
        this(messageBusImpl);
    }
}
