package com.intellij.util.messages.impl;

import com.intellij.util.concurrency.AppExecutorUtil;
import com.intellij.util.messages.MessageBusOwner;
import com.intellij.util.messages.Topic;
import com.intellij.util.messages.impl.Message;
import com.intellij.util.messages.impl.RootBus;
import java.util.ArrayDeque;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u001a\u0010\u0015\u001a\u00020\u00132\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u000e8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001a"}, d2 = {"Lcom/intellij/util/messages/impl/RootBus;", "Lcom/intellij/util/messages/impl/CompositeMessageBus;", "owner", "Lcom/intellij/util/messages/MessageBusOwner;", "<init>", "(Lcom/intellij/util/messages/MessageBusOwner;)V", "compactionFutureRef", "Ljava/util/concurrent/atomic/AtomicReference;", "Ljava/util/concurrent/CompletableFuture;", "compactionRequest", "Ljava/util/concurrent/atomic/AtomicInteger;", "emptyConnectionCounter", "queueThreadLocal", "Ljava/lang/ThreadLocal;", "Lcom/intellij/util/messages/impl/MessageQueue;", "queue", "getQueue$intellij_platform_core", "()Lcom/intellij/util/messages/impl/MessageQueue;", "scheduleEmptyConnectionRemoving", "", "dispose", "removeDisposedHandlers", "topic", "Lcom/intellij/util/messages/Topic;", "handler", "", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RootBus extends CompositeMessageBus {
    private final AtomicReference<CompletableFuture<?>> compactionFutureRef;
    private final AtomicInteger compactionRequest;
    private final AtomicInteger emptyConnectionCounter;
    private final ThreadLocal<MessageQueue> queueThreadLocal;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RootBus(MessageBusOwner messageBusOwner) {
        super(messageBusOwner);
        messageBusOwner.getClass();
        this.compactionFutureRef = new AtomicReference<>();
        this.compactionRequest = new AtomicInteger();
        this.emptyConnectionCounter = new AtomicInteger();
        ThreadLocal<MessageQueue> threadLocalWithInitial = ThreadLocal.withInitial(new Supplier() { // from class: wkc
            @Override // java.util.function.Supplier
            public final Object get() {
                return RootBus.p();
            }
        });
        threadLocalWithInitial.getClass();
        this.queueThreadLocal = threadLocalWithInitial;
    }

    public static boolean n(Topic topic, Object obj, Message message) {
        int length = message.handlers.length;
        for (int i = 0; i < length; i++) {
            Object[] objArr = message.handlers;
            Object obj2 = objArr[i];
            if (obj2 == null) {
                break;
            }
            if (message.topic == topic && obj2 == obj) {
                objArr[i] = null;
                if (objArr.length == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean o(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static MessageQueue p() {
        return new MessageQueue();
    }

    public static void q(RootBus rootBus) {
        int i;
        do {
            i = rootBus.compactionRequest.get();
            rootBus.removeEmptyConnectionsRecursively();
        } while (!rootBus.compactionRequest.compareAndSet(i, 0));
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl, com.intellij.util.messages.MessageBus
    public void dispose() throws Throwable {
        CompletableFuture<?> andSet = this.compactionFutureRef.getAndSet(null);
        if (andSet != null) {
            andSet.cancel(false);
        }
        this.compactionRequest.set(0);
        super.dispose();
    }

    public final MessageQueue getQueue$intellij_platform_core() {
        MessageQueue messageQueue = this.queueThreadLocal.get();
        messageQueue.getClass();
        return messageQueue;
    }

    public final void removeDisposedHandlers(final Topic<?> topic, final Object handler) {
        topic.getClass();
        handler.getClass();
        ArrayDeque<Message> arrayDeque = getQueue$intellij_platform_core().queue;
        if (arrayDeque.isEmpty()) {
            return;
        }
        final Function1 function1 = new Function1() { // from class: ukc
            public final Object invoke(Object obj) {
                return Boolean.valueOf(RootBus.n(topic, handler, (Message) obj));
            }
        };
        arrayDeque.removeIf(new Predicate() { // from class: vkc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return RootBus.o(function1, obj);
            }
        });
    }

    public final void scheduleEmptyConnectionRemoving() {
        int iIncrementAndGet = this.emptyConnectionCounter.incrementAndGet();
        if (iIncrementAndGet >= 128 && this.emptyConnectionCounter.compareAndSet(iIncrementAndGet, 0) && this.compactionRequest.incrementAndGet() == 1) {
            this.compactionFutureRef.set(CompletableFuture.runAsync(new Runnable() { // from class: tkc
                @Override // java.lang.Runnable
                public final void run() {
                    RootBus.q(this.b);
                }
            }, AppExecutorUtil.getAppExecutorService()));
        }
    }
}
