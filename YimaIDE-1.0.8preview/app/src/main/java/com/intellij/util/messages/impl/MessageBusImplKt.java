package com.intellij.util.messages.impl;

import com.intellij.codeWithMe.ClientId;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.application.RuntimeFlagsKt;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.messages.MessageHandler;
import com.intellij.util.messages.Topic;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jdk7.AutoCloseableKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a$\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0002\u001aa\u0010\u000b\u001a\u0004\u0018\u00010\u00072\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0010\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00112\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u00112\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0002\u0010\u0015\u001a#\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0002\u0010\u0019\u001a7\u0010\u001a\u001a\u00020\u00032\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00010\u00112\u0006\u0010\u001b\u001a\u00020\u001c2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002¢\u0006\u0002\u0010\u001d\u001a&\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\t0#H\u0002\u001a]\u0010$\u001a\u0004\u0018\u00010\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0010\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00112\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010)\u001a\u00020\u00012\f\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+2\b\u0010\n\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010-\u001a\u001a\u0010.\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010/\u001a\u00020\u0007H\u0002\u001a/\u00100\u001a\u00020\u00032\u0006\u0010)\u001a\u00020\u00012\u0010\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00112\u0006\u0010%\u001a\u00020&H\u0002¢\u0006\u0002\u00101\u001a\u0010\u00102\u001a\u00020\u00032\u0006\u00103\u001a\u00020\u0007H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"NA", "", "pumpWaiting", "", "jobQueue", "Lcom/intellij/util/messages/impl/MessageQueue;", "deliverMessage", "", "job", "Lcom/intellij/util/messages/impl/Message;", "prevError", "executeOrAddToQueue", "topic", "Lcom/intellij/util/messages/Topic;", "method", "Ljava/lang/reflect/Method;", "args", "", "handlers", "bus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "(Lcom/intellij/util/messages/Topic;Ljava/lang/reflect/Method;[Ljava/lang/Object;[Ljava/lang/Object;Lcom/intellij/util/messages/impl/MessageQueue;Ljava/lang/Throwable;Lcom/intellij/util/messages/impl/MessageBusImpl;)Ljava/lang/Throwable;", "clearSubscriberCacheOnConnectionTerminated", "", "topicAndHandlerPairs", "([Ljava/lang/Object;Lcom/intellij/util/messages/impl/MessageBusImpl;)Z", "removeDisposedHandlers", "index", "", "([Ljava/lang/Object;ILcom/intellij/util/messages/Topic;Lcom/intellij/util/messages/impl/MessageBusImpl;)V", "deliverImmediately", "", "connection", "Lcom/intellij/util/messages/impl/MessageBusConnectionImpl;", "jobs", "Ljava/util/Deque;", "invokeListener", "methodHandle", "Ljava/lang/invoke/MethodHandle;", "methodName", "", "handler", "messageDeliveryListeners", "", "Lcom/intellij/util/messages/impl/MessageDeliveryListener;", "(Ljava/lang/invoke/MethodHandle;Ljava/lang/String;[Ljava/lang/Object;Lcom/intellij/util/messages/Topic;Ljava/lang/Object;Ljava/util/Set;Ljava/lang/Throwable;)Ljava/lang/Throwable;", "mergeErrors", "newError", "invokeMethod", "(Ljava/lang/Object;[Ljava/lang/Object;Ljava/lang/invoke/MethodHandle;)V", "throwError", "error", "intellij.platform.core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class MessageBusImplKt {
    private static final Object NA = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean clearSubscriberCacheOnConnectionTerminated(Object[] objArr, MessageBusImpl messageBusImpl) {
        boolean z = false;
        for (int i = 0; i < objArr.length; i += 2) {
            Object obj = objArr[i];
            obj.getClass();
            Topic topic = (Topic) obj;
            removeDisposedHandlers(objArr, i, topic, messageBusImpl);
            Topic.BroadcastDirection broadcastDirection = topic.getBroadcastDirection();
            broadcastDirection.getClass();
            if (broadcastDirection == Topic.BroadcastDirection.TO_CHILDREN) {
                MessageBusImpl messageBusImpl2 = messageBusImpl;
                while (true) {
                    messageBusImpl2 = messageBusImpl2.parentBus;
                    if (messageBusImpl2 == null) {
                        break;
                    }
                    removeDisposedHandlers(objArr, i, topic, messageBusImpl2);
                }
                if (messageBusImpl.hasChildren$intellij_platform_core()) {
                    z = true;
                }
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Message> deliverImmediately(MessageBusConnectionImpl messageBusConnectionImpl, Deque<Message> deque) {
        Iterator<Message> it = deque.iterator();
        it.getClass();
        ArrayList arrayList = null;
        while (it.hasNext()) {
            Message next = it.next();
            Object[] objArr = next.handlers;
            int length = objArr.length;
            ArrayList arrayList2 = null;
            for (int i = 0; i < length; i++) {
                Object obj = objArr[i];
                if (obj != null && messageBusConnectionImpl.bus == next.bus && messageBusConnectionImpl.isMyHandler(next.topic, obj)) {
                    objArr[i] = null;
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    arrayList2.add(obj);
                }
            }
            if (arrayList2 != null) {
                if (objArr.length == arrayList2.size()) {
                    it.remove();
                }
                Message message = new Message(next.topic, next.method, next.methodName, next.args, arrayList2.toArray(new Object[0]), next.bus);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(message);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Throwable deliverMessage(Message message, MessageQueue messageQueue, Throwable th) {
        AccessToken accessTokenWithExplicitClientId = ClientId.Companion.withExplicitClientId(message.clientId);
        try {
            messageQueue.current = message;
            Object[] objArr = message.handlers;
            int i = message.currentHandlerIndex;
            int length = objArr.length;
            int i2 = length - 1;
            Throwable thInvokeListener = th;
            while (i < length) {
                if (i == i2) {
                    messageQueue.current = null;
                }
                message.currentHandlerIndex++;
                Object obj = objArr[i];
                if (obj != null) {
                    thInvokeListener = invokeListener(message.method, message.methodName, message.args, message.topic, obj, message.bus.messageDeliveryListeners, thInvokeListener);
                }
                i++;
                if (i != message.currentHandlerIndex) {
                    AutoCloseableKt.closeFinally(accessTokenWithExplicitClientId, (Throwable) null);
                    return thInvokeListener;
                }
            }
            AutoCloseableKt.closeFinally(accessTokenWithExplicitClientId, (Throwable) null);
            return thInvokeListener;
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                AutoCloseableKt.closeFinally(accessTokenWithExplicitClientId, th2);
                throw th3;
            }
        }
    }

    public static final Throwable executeOrAddToQueue(Topic<?> topic, Method method, Object[] objArr, Object[] objArr2, MessageQueue messageQueue, Throwable th, MessageBusImpl messageBusImpl) {
        Object[] objArr3;
        Topic<?> topic2;
        topic.getClass();
        method.getClass();
        objArr2.getClass();
        messageBusImpl.getClass();
        MethodHandle methodHandleCompute = MethodHandleCache.compute(method, objArr);
        methodHandleCompute.getClass();
        if (messageQueue != null) {
            ArrayDeque<Message> arrayDeque = messageQueue.queue;
            String name = method.getName();
            name.getClass();
            arrayDeque.offerLast(new Message(topic, methodHandleCompute, name, objArr, objArr2, messageBusImpl));
            return th;
        }
        int length = objArr2.length;
        Throwable th2 = th;
        int i = 0;
        while (i < length) {
            Object obj = objArr2[i];
            String name2 = method.getName();
            name2.getClass();
            if (obj == null) {
                topic2 = topic;
                objArr3 = objArr;
            } else {
                Topic<?> topic3 = topic;
                objArr3 = objArr;
                Throwable thInvokeListener = invokeListener(methodHandleCompute, name2, objArr3, topic3, obj, messageBusImpl.messageDeliveryListeners, th2);
                topic2 = topic3;
                th2 = thInvokeListener;
            }
            i++;
            topic = topic2;
            objArr = objArr3;
        }
        return th2;
    }

    private static final Throwable invokeListener(MethodHandle methodHandle, String str, Object[] objArr, Topic<?> topic, Object obj, Set<? extends MessageDeliveryListener> set, Throwable th) {
        String str2;
        Topic<?> topic2;
        Object obj2;
        RuntimeException runtimeException;
        RuntimeException runtimeException2;
        try {
            try {
                try {
                    if (obj instanceof MessageHandler) {
                        MessageHandler messageHandler = (MessageHandler) obj;
                        if (objArr == null) {
                            objArr = ArrayUtilRt.EMPTY_OBJECT_ARRAY;
                        }
                        messageHandler.handle(methodHandle, Arrays.copyOf(objArr, objArr.length));
                        return th;
                    }
                    if (set.isEmpty()) {
                        invokeMethod(obj, objArr, methodHandle);
                        return th;
                    }
                    long jNanoTime = System.nanoTime();
                    invokeMethod(obj, objArr, methodHandle);
                    Iterator<? extends MessageDeliveryListener> it = set.iterator();
                    while (it.hasNext()) {
                        str2 = str;
                        topic2 = topic;
                        obj2 = obj;
                        try {
                            it.next().messageDelivered(topic2, str2, obj2, System.nanoTime() - jNanoTime);
                            topic = topic2;
                            str = str2;
                            obj = obj2;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    return th;
                } catch (Throwable th3) {
                    runtimeException = th3;
                    str2 = str;
                    topic2 = topic;
                    obj2 = obj;
                }
            } catch (Throwable th4) {
                th = th4;
                str2 = str;
                topic2 = topic;
                obj2 = obj;
            }
        } catch (AbstractMethodError unused) {
        } catch (CancellationException e) {
            if (RuntimeFlagsKt.isMessageBusErrorPropagationEnabled()) {
                return mergeErrors(th, e);
            }
        }
        runtimeException = th;
        if (runtimeException instanceof AssertionError) {
            runtimeException2 = runtimeException;
        } else {
            runtimeException2 = new RuntimeException("Cannot invoke (class=" + obj2.getClass().getSimpleName() + ", method=" + str2 + ", topic=" + topic2.getDisplayName() + ')', runtimeException);
        }
        if (RuntimeFlagsKt.isMessageBusErrorPropagationEnabled()) {
            return mergeErrors(th, runtimeException2);
        }
        MessageBusImpl.LOG.error(runtimeException);
        return th;
    }

    private static final void invokeMethod(Object obj, Object[] objArr, MethodHandle methodHandle) {
        if (objArr == null) {
            (void) methodHandle.invoke(obj);
        } else {
            (void) methodHandle.bindTo(obj).invokeExact(objArr);
        }
    }

    private static final Throwable mergeErrors(Throwable th, Throwable th2) {
        if (th == null) {
            return th2;
        }
        ExceptionsKt.addSuppressed(th, th2);
        return th;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void pumpWaiting(MessageQueue messageQueue) throws Throwable {
        Message message = messageQueue.current;
        Throwable thDeliverMessage = null;
        if (message != null) {
            if (message.bus.isDisposed()) {
                MessageBusImpl.LOG.error("Accessing disposed message bus " + message.bus + " (job=" + message + ')');
            } else {
                thDeliverMessage = deliverMessage(message, messageQueue, null);
            }
        }
        while (true) {
            Message messagePollFirst = messageQueue.queue.pollFirst();
            if (messagePollFirst == null) {
                break;
            }
            if (messagePollFirst.bus.isDisposed()) {
                MessageBusImpl.LOG.error("Accessing disposed message bus " + messagePollFirst.bus + " (job=" + messagePollFirst + ')');
            } else {
                thDeliverMessage = deliverMessage(messagePollFirst, messageQueue, thDeliverMessage);
            }
        }
        if (thDeliverMessage != null) {
            throwError(thDeliverMessage);
        }
    }

    private static final void removeDisposedHandlers(Object[] objArr, int i, Topic<?> topic, MessageBusImpl messageBusImpl) {
        Object[] objArrRemove = messageBusImpl.subscriberCache.remove(topic);
        if (objArrRemove == null) {
            return;
        }
        Object obj = objArr[i + 1];
        if (topic.isImmediateDelivery()) {
            int length = objArrRemove.length;
            for (int i2 = 0; i2 < length; i2++) {
                if (objArrRemove[i2] == obj) {
                    objArrRemove[i2] = null;
                }
            }
        }
        messageBusImpl.rootBus.removeDisposedHandlers(topic, obj);
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0029  */
    /* JADX WARN: Code duplicated, block: B:19:? A[SYNTHETIC] */
    public static final void throwError(Throwable th) throws Throwable {
        th.getClass();
        MessageBusImpl.LOG.assertTrue(RuntimeFlagsKt.isMessageBusErrorPropagationEnabled());
        Throwable[] suppressed = th.getSuppressed();
        if (suppressed.length <= 1) {
            throw th;
        }
        for (Throwable th2 : suppressed) {
            if ((th2 instanceof ProcessCanceledException) || (th2 instanceof CancellationException)) {
                if (th2 != null) {
                    throw th;
                }
                throw th2;
            }
        }
        th2 = null;
        if (th2 != null) {
            throw th;
        }
        throw th2;
    }
}
