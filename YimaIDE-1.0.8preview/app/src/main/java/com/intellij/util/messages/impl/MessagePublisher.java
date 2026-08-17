package com.intellij.util.messages.impl;

import com.intellij.util.EventDispatcher;
import com.intellij.util.messages.Topic;
import com.intellij.util.messages.impl.MessagePublisher;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ2\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0010\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u000fH\u0086\u0002¢\u0006\u0002\u0010\u0010J3\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\r2\u0010\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u000f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0010¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0004X\u0085\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/intellij/util/messages/impl/MessagePublisher;", "L", "Ljava/lang/reflect/InvocationHandler;", "topic", "Lcom/intellij/util/messages/Topic;", "bus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "<init>", "(Lcom/intellij/util/messages/Topic;Lcom/intellij/util/messages/impl/MessageBusImpl;)V", "invoke", "", "proxy", "method", "Ljava/lang/reflect/Method;", "args", "", "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;", "publish", "", "queue", "Lcom/intellij/util/messages/impl/MessageQueue;", "publish$intellij_platform_core", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;Lcom/intellij/util/messages/impl/MessageQueue;)Z", "preload", "", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class MessagePublisher<L> implements InvocationHandler {
    protected final MessageBusImpl bus;
    protected final Topic<L> topic;

    public MessagePublisher(Topic<L> topic, MessageBusImpl messageBusImpl) {
        topic.getClass();
        messageBusImpl.getClass();
        this.topic = topic;
        this.bus = messageBusImpl;
    }

    public static Object[] a(Function1 function1, Object obj) {
        return (Object[]) function1.invoke(obj);
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        proxy.getClass();
        method.getClass();
        if (Intrinsics.areEqual(method.getDeclaringClass(), Object.class)) {
            return EventDispatcher.handleObjectMethod(proxy, args, method.getName());
        }
        boolean zIsImmediateDelivery = this.topic.isImmediateDelivery();
        MessageBusImpl messageBusImpl = this.bus;
        if (zIsImmediateDelivery) {
            messageBusImpl.checkDisposed$intellij_platform_core();
            publish$intellij_platform_core(method, args, null);
            return MessageBusImplKt.NA;
        }
        MessageQueue queue$intellij_platform_core = messageBusImpl.rootBus.getQueue$intellij_platform_core();
        if (!queue$intellij_platform_core.queue.isEmpty()) {
            MessageBusImplKt.pumpWaiting(queue$intellij_platform_core);
        }
        if (publish$intellij_platform_core(method, args, queue$intellij_platform_core)) {
            MessageBusImplKt.pumpWaiting(queue$intellij_platform_core);
        }
        return MessageBusImplKt.NA;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public boolean publish$intellij_platform_core(Method method, Object[] args, MessageQueue queue) throws Throwable {
        method.getClass();
        ConcurrentMap<Topic<?>, Object[]> concurrentMap = this.bus.subscriberCache;
        Topic<L> topic = this.topic;
        final MessagePublisher$publish$handlers$1 messagePublisher$publish$handlers$1 = new MessagePublisher$publish$handlers$1(this.bus);
        Object[] objArrComputeIfAbsent = concurrentMap.computeIfAbsent((Topic<?>) topic, new Function() { // from class: vz9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MessagePublisher.a(messagePublisher$publish$handlers$1, obj);
            }
        });
        objArrComputeIfAbsent.getClass();
        if (objArrComputeIfAbsent.length == 0) {
            return false;
        }
        Throwable thExecuteOrAddToQueue = MessageBusImplKt.executeOrAddToQueue(this.topic, method, args, objArrComputeIfAbsent, queue, null, this.bus);
        if (thExecuteOrAddToQueue == null) {
            return true;
        }
        MessageBusImplKt.throwError(thExecuteOrAddToQueue);
        return true;
    }
}
