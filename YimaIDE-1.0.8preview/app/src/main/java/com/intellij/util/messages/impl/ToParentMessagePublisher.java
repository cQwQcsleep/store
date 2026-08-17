package com.intellij.util.messages.impl;

import com.intellij.util.messages.Topic;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u001d\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\n\u001a\u00020\u000bH\u0016J3\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0010¢\u0006\u0004\b\u0015\u0010\u0016J\u001d\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00112\u0006\u0010\u0018\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/intellij/util/messages/impl/ToParentMessagePublisher;", "L", "Lcom/intellij/util/messages/impl/MessagePublisher;", "Ljava/lang/reflect/InvocationHandler;", "topic", "Lcom/intellij/util/messages/Topic;", "bus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "<init>", "(Lcom/intellij/util/messages/Topic;Lcom/intellij/util/messages/impl/MessageBusImpl;)V", "preload", "", "publish", "", "method", "Ljava/lang/reflect/Method;", "args", "", "", "queue", "Lcom/intellij/util/messages/impl/MessageQueue;", "publish$intellij_platform_core", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;Lcom/intellij/util/messages/impl/MessageQueue;)Z", "getOrComputeHandlers", "parentBus", "(Lcom/intellij/util/messages/impl/MessageBusImpl;)[Ljava/lang/Object;", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class ToParentMessagePublisher<L> extends MessagePublisher<L> implements InvocationHandler {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToParentMessagePublisher(Topic<L> topic, MessageBusImpl messageBusImpl) {
        super(topic, messageBusImpl);
        topic.getClass();
        messageBusImpl.getClass();
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    private final Object[] getOrComputeHandlers(MessageBusImpl parentBus) {
        Object[] objArrPutIfAbsent;
        Object[] objArrComputeSubscribers$intellij_platform_core = parentBus.subscriberCache.get(this.topic);
        return (objArrComputeSubscribers$intellij_platform_core != null || (objArrPutIfAbsent = parentBus.subscriberCache.putIfAbsent((Topic<?>) this.topic, (objArrComputeSubscribers$intellij_platform_core = parentBus.computeSubscribers$intellij_platform_core(this.topic)))) == null) ? objArrComputeSubscribers$intellij_platform_core : objArrPutIfAbsent;
    }

    @Override // com.intellij.util.messages.impl.MessagePublisher
    public boolean publish$intellij_platform_core(Method method, Object[] args, MessageQueue queue) throws Throwable {
        Method method2;
        Object[] objArr;
        MessageQueue messageQueue;
        method.getClass();
        MessageBusImpl messageBusImpl = this.bus;
        Throwable thExecuteOrAddToQueue = null;
        boolean z = false;
        while (true) {
            Object[] orComputeHandlers = getOrComputeHandlers(messageBusImpl);
            if (orComputeHandlers.length == 0) {
                method2 = method;
                objArr = args;
                messageQueue = queue;
            } else {
                method2 = method;
                objArr = args;
                messageQueue = queue;
                thExecuteOrAddToQueue = MessageBusImplKt.executeOrAddToQueue(this.topic, method2, objArr, orComputeHandlers, messageQueue, thExecuteOrAddToQueue, this.bus);
                z = true;
            }
            messageBusImpl = messageBusImpl.parentBus;
            if (messageBusImpl == null) {
                break;
            }
            method = method2;
            args = objArr;
            queue = messageQueue;
        }
        if (thExecuteOrAddToQueue != null) {
            MessageBusImplKt.throwError(thExecuteOrAddToQueue);
        }
        return z;
    }
}
