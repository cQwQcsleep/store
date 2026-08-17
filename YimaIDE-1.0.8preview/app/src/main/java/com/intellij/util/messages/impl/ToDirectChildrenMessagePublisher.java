package com.intellij.util.messages.impl;

import com.intellij.util.ArrayUtilRt;
import com.intellij.util.messages.Topic;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ3\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0010\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0010¢\u0006\u0004\b\u0016\u0010\u0017R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/intellij/util/messages/impl/ToDirectChildrenMessagePublisher;", "L", "Lcom/intellij/util/messages/impl/MessagePublisher;", "Ljava/lang/reflect/InvocationHandler;", "topic", "Lcom/intellij/util/messages/Topic;", "bus", "Lcom/intellij/util/messages/impl/CompositeMessageBus;", "childBuses", "", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "<init>", "(Lcom/intellij/util/messages/Topic;Lcom/intellij/util/messages/impl/CompositeMessageBus;Ljava/util/List;)V", "publish", "", "method", "Ljava/lang/reflect/Method;", "args", "", "", "queue", "Lcom/intellij/util/messages/impl/MessageQueue;", "publish$intellij_platform_core", "(Ljava/lang/reflect/Method;[Ljava/lang/Object;Lcom/intellij/util/messages/impl/MessageQueue;)Z", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class ToDirectChildrenMessagePublisher<L> extends MessagePublisher<L> implements InvocationHandler {
    private final List<MessageBusImpl> childBuses;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ToDirectChildrenMessagePublisher(Topic<L> topic, CompositeMessageBus compositeMessageBus, List<? extends MessageBusImpl> list) {
        super(topic, compositeMessageBus);
        topic.getClass();
        compositeMessageBus.getClass();
        list.getClass();
        this.childBuses = list;
    }

    public static Object[] b(MessageBusImpl messageBusImpl, Topic topic) {
        ArrayList arrayList = new ArrayList();
        topic.getClass();
        messageBusImpl.doComputeSubscribers$intellij_platform_core(topic, arrayList, !messageBusImpl.owner.isParentLazyListenersIgnored());
        return arrayList.isEmpty() ? ArrayUtilRt.EMPTY_OBJECT_ARRAY : arrayList.toArray(new Object[0]);
    }

    public static Object[] c(Function1 function1, Object obj) {
        return (Object[]) function1.invoke(obj);
    }

    public static Object[] d(Function1 function1, Object obj) {
        return (Object[]) function1.invoke(obj);
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // com.intellij.util.messages.impl.MessagePublisher
    public boolean publish$intellij_platform_core(Method method, Object[] args, MessageQueue queue) throws Throwable {
        Method method2;
        Object[] objArr;
        MessageQueue messageQueue;
        Throwable thExecuteOrAddToQueue;
        method.getClass();
        ConcurrentMap<Topic<?>, Object[]> concurrentMap = this.bus.subscriberCache;
        Topic<L> topic = this.topic;
        final ToDirectChildrenMessagePublisher$publish$handlers$1 toDirectChildrenMessagePublisher$publish$handlers$1 = new ToDirectChildrenMessagePublisher$publish$handlers$1(this.bus);
        Object[] objArrComputeIfAbsent = concurrentMap.computeIfAbsent((Topic<?>) topic, new Function() { // from class: com.intellij.util.messages.impl.c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ToDirectChildrenMessagePublisher.c(toDirectChildrenMessagePublisher$publish$handlers$1, obj);
            }
        });
        objArrComputeIfAbsent.getClass();
        boolean z = false;
        if (objArrComputeIfAbsent.length == 0) {
            method2 = method;
            objArr = args;
            messageQueue = queue;
            thExecuteOrAddToQueue = null;
        } else {
            method2 = method;
            objArr = args;
            thExecuteOrAddToQueue = MessageBusImplKt.executeOrAddToQueue(this.topic, method2, objArr, objArrComputeIfAbsent, queue, null, this.bus);
            messageQueue = queue;
            z = true;
        }
        Throwable thExecuteOrAddToQueue2 = thExecuteOrAddToQueue;
        for (final MessageBusImpl messageBusImpl : this.childBuses) {
            if (!messageBusImpl.owner.isDisposed()) {
                ConcurrentMap<Topic<?>, Object[]> concurrentMap2 = messageBusImpl.subscriberCache;
                Topic<L> topic2 = this.topic;
                final Function1 function1 = new Function1() { // from class: com.intellij.util.messages.impl.d
                    public final Object invoke(Object obj) {
                        return ToDirectChildrenMessagePublisher.b(messageBusImpl, (Topic) obj);
                    }
                };
                Object[] objArrComputeIfAbsent2 = concurrentMap2.computeIfAbsent((Topic<?>) topic2, new Function() { // from class: com.intellij.util.messages.impl.e
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ToDirectChildrenMessagePublisher.d(function1, obj);
                    }
                });
                objArrComputeIfAbsent2.getClass();
                if (objArrComputeIfAbsent2.length != 0) {
                    Method method3 = method2;
                    Object[] objArr2 = objArr;
                    thExecuteOrAddToQueue2 = MessageBusImplKt.executeOrAddToQueue(this.topic, method3, objArr2, objArrComputeIfAbsent2, messageQueue, thExecuteOrAddToQueue2, messageBusImpl);
                    objArr = objArr2;
                    method2 = method3;
                    z = true;
                }
            }
        }
        if (thExecuteOrAddToQueue2 != null) {
            MessageBusImplKt.throwError(thExecuteOrAddToQueue2);
        }
        return z;
    }
}
