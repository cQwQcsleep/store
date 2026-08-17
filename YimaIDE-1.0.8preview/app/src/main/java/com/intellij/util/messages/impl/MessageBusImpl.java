package com.intellij.util.messages.impl;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.RuntimeFlagsKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.Disposer;
import com.intellij.serviceContainer.AlreadyDisposedException;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.messages.MessageBusOwner;
import com.intellij.util.messages.SimpleMessageBusConnection;
import com.intellij.util.messages.Topic;
import com.intellij.util.messages.impl.Message;
import com.intellij.util.messages.impl.MessageBusImpl;
import java.lang.reflect.Proxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0017\u0018\u0000 a2\u00020\u0001:\u0002`aB\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0010\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\bJ\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0017H\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0010\u0010 \u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J%\u0010'\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020\f2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0\u000bH\u0016¢\u0006\u0002\u0010*J%\u0010+\u001a\u0002H(\"\b\b\u0000\u0010(*\u00020\f2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0\u000bH\u0016¢\u0006\u0002\u0010*J/\u0010,\u001a\b\u0012\u0004\u0012\u0002H(0-\"\u0004\b\u0000\u0010(2\f\u0010)\u001a\b\u0012\u0004\u0012\u0002H(0\u000b2\u0006\u0010.\u001a\u00020/H\u0010¢\u0006\u0002\b0J\u0006\u00101\u001a\u000202J\u0006\u00103\u001a\u000202J\b\u00104\u001a\u000202H\u0016J\r\u00105\u001a\u000202H\u0010¢\u0006\u0002\b6J\u0014\u0010:\u001a\u0002082\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0016J\r\u0010;\u001a\u000202H\u0000¢\u0006\u0002\b<J\b\u0010=\u001a\u000202H\u0002J\b\u0010>\u001a\u000202H\u0002J1\u0010?\u001a\u0002022\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u000b2\u000e\u0010@\u001a\n\u0012\u0006\b\u0000\u0012\u00020\f0A2\u0006\u0010B\u001a\u000208H\u0010¢\u0006\u0002\bCJ#\u0010D\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00112\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0010¢\u0006\u0004\bE\u0010FJ\r\u0010G\u001a\u000208H\u0010¢\u0006\u0002\bHJ\u0019\u0010I\u001a\u0002022\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0000¢\u0006\u0002\bJJ\u0019\u0010K\u001a\u0002022\n\u0010)\u001a\u0006\u0012\u0002\b\u00030\u000bH\u0010¢\u0006\u0002\bLJ\b\u0010M\u001a\u000202H\u0016J\u001d\u0010N\u001a\u0002082\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H\u0010¢\u0006\u0004\bP\u0010QJ\u001d\u0010R\u001a\u0002022\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\f0\u0011H\u0010¢\u0006\u0004\bS\u0010TJ\u0015\u0010U\u001a\u0002022\u0006\u0010V\u001a\u00020WH\u0000¢\u0006\u0002\bXJ\u000e\u0010Y\u001a\u0002022\u0006\u0010Z\u001a\u00020\u001aJ\u000e\u0010[\u001a\u0002022\u0006\u0010Z\u001a\u00020\u001aJ\u001a\u0010\\\u001a\u0002022\u0010\u0010]\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030_0^H\u0016R \u0010\t\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\u0004\u0012\u00020\f0\n8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R(\u0010\u0010\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u00110\n8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00138\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00198\u0000@\u0000X\u0081\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u00107\u001a\u0002088VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u00109¨\u0006b"}, d2 = {"Lcom/intellij/util/messages/impl/MessageBusImpl;", "Lcom/intellij/util/messages/MessageBus;", "owner", "Lcom/intellij/util/messages/MessageBusOwner;", "parentBus", "Lcom/intellij/util/messages/impl/CompositeMessageBus;", "<init>", "(Lcom/intellij/util/messages/MessageBusOwner;Lcom/intellij/util/messages/impl/CompositeMessageBus;)V", "(Lcom/intellij/util/messages/MessageBusOwner;)V", "publisherCache", "Ljava/util/concurrent/ConcurrentMap;", "Lcom/intellij/util/messages/Topic;", "", "subscribers", "Ljava/util/concurrent/ConcurrentLinkedQueue;", "Lcom/intellij/util/messages/impl/MessageBusImpl$MessageHandlerHolder;", "subscriberCache", "", "rootBus", "Lcom/intellij/util/messages/impl/RootBus;", "disposeState", "Lcom/intellij/util/messages/impl/BusState;", "connectionDisposable", "Lcom/intellij/openapi/Disposable;", "messageDeliveryListeners", "", "Lcom/intellij/util/messages/impl/MessageDeliveryListener;", "parent", "getParent", "()Lcom/intellij/util/messages/MessageBus;", "toString", "", "connect", "Lcom/intellij/util/messages/MessageBusConnection;", "parentDisposable", "simpleConnect", "Lcom/intellij/util/messages/SimpleMessageBusConnection;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "syncPublisher", "L", "topic", "(Lcom/intellij/util/messages/Topic;)Ljava/lang/Object;", "syncAndPreloadPublisher", "createPublisher", "Lcom/intellij/util/messages/impl/MessagePublisher;", "direction", "Lcom/intellij/util/messages/Topic$BroadcastDirection;", "createPublisher$intellij_platform_core", "disposeConnectionChildren", "", "disposeConnection", "dispose", "disposeChildren", "disposeChildren$intellij_platform_core", "isDisposed", "", "()Z", "hasUndeliveredEvents", "checkDisposed", "checkDisposed$intellij_platform_core", "checkBusDisposed", "checkOwnerDisposed", "doComputeSubscribers", "result", "", "subscribeLazyListeners", "doComputeSubscribers$intellij_platform_core", "computeSubscribers", "computeSubscribers$intellij_platform_core", "(Lcom/intellij/util/messages/Topic;)[Ljava/lang/Object;", "hasChildren", "hasChildren$intellij_platform_core", "notifyOnSubscription", "notifyOnSubscription$intellij_platform_core", "notifyOnSubscriptionToTopicToChildren", "notifyOnSubscriptionToTopicToChildren$intellij_platform_core", "removeEmptyConnectionsRecursively", "notifyConnectionTerminated", "topicAndHandlerPairs", "notifyConnectionTerminated$intellij_platform_core", "([Ljava/lang/Object;)Z", "clearSubscriberCache", "clearSubscriberCache$intellij_platform_core", "([Ljava/lang/Object;)V", "deliverImmediately", "connection", "Lcom/intellij/util/messages/impl/MessageBusConnectionImpl;", "deliverImmediately$intellij_platform_core", "addMessageDeliveryListener", "listener", "removeMessageDeliveryListener", "disconnectPluginConnections", "predicate", "Ljava/util/function/Predicate;", "Ljava/lang/Class;", "MessageHandlerHolder", "Companion", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class MessageBusImpl implements MessageBus {
    public static final Logger LOG;
    private Disposable connectionDisposable;
    private BusState disposeState;
    public Set<MessageDeliveryListener> messageDeliveryListeners;
    public final MessageBusOwner owner;
    public final CompositeMessageBus parentBus;
    public final ConcurrentMap<Topic<?>, Object> publisherCache;
    public final RootBus rootBus;
    public final ConcurrentMap<Topic<?>, Object[]> subscriberCache;
    public final ConcurrentLinkedQueue<MessageHandlerHolder> subscribers;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J$\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u000e\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00010\nH&J\u001a\u0010\u000b\u001a\u00020\u00062\u0010\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\rH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/intellij/util/messages/impl/MessageBusImpl$MessageHandlerHolder;", "", "isDisposed", "", "()Z", "collectHandlers", "", "topic", "Lcom/intellij/util/messages/Topic;", "result", "", "disconnectIfNeeded", "predicate", "Ljava/util/function/Predicate;", "Ljava/lang/Class;", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public interface MessageHandlerHolder {
        void collectHandlers(Topic<?> topic, List<? super Object> result);

        boolean isDisposed();
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Topic.BroadcastDirection.values().length];
            try {
                iArr[Topic.BroadcastDirection.TO_PARENT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Topic.BroadcastDirection.TO_DIRECT_CHILDREN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        Logger logger = Logger.getInstance(MessageBusImpl.class);
        logger.getClass();
        LOG = logger;
    }

    public MessageBusImpl(MessageBusOwner messageBusOwner) {
        messageBusOwner.getClass();
        this.publisherCache = new ConcurrentHashMap();
        this.subscribers = new ConcurrentLinkedQueue<>();
        this.subscriberCache = new ConcurrentHashMap();
        this.disposeState = BusState.Alive.INSTANCE;
        this.connectionDisposable = Disposer.newDisposable();
        ConcurrentHashMap.KeySetView keySetViewNewKeySet = ConcurrentHashMap.newKeySet();
        keySetViewNewKeySet.getClass();
        this.messageDeliveryListeners = keySetViewNewKeySet;
        this.owner = messageBusOwner;
        this.rootBus = (RootBus) this;
        this.parentBus = null;
    }

    public static boolean a(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static Object b(MessageBusImpl messageBusImpl, Topic topic) {
        Class listenerClass = topic.getListenerClass();
        listenerClass.getClass();
        Topic.BroadcastDirection broadcastDirection = topic.getBroadcastDirection();
        broadcastDirection.getClass();
        return Proxy.newProxyInstance(listenerClass.getClassLoader(), new Class[]{listenerClass}, messageBusImpl.createPublisher$intellij_platform_core(topic, broadcastDirection));
    }

    public static Object c(Function1 function1, Object obj) {
        return function1.invoke(obj);
    }

    private final void checkBusDisposed() throws Throwable {
        BusState busState = this.disposeState;
        if (busState instanceof BusState.Disposed) {
            if (RuntimeFlagsKt.isMessageBusThrowsWhenDisposed()) {
                throw new AlreadyDisposedException(toString()).initCause(((BusState.Disposed) busState).where);
            }
            LOG.error("Already disposed: " + this, ((BusState.Disposed) busState).where);
        }
    }

    private final void checkOwnerDisposed() {
        if (this.owner.isDisposed()) {
            if (RuntimeFlagsKt.isMessageBusThrowsWhenDisposed()) {
                throw new AlreadyDisposedException(toString());
            }
            LOG.error("Already disposed: " + this);
        }
    }

    public static boolean d(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static Unit e(SimpleMessageBusConnectionImpl simpleMessageBusConnectionImpl, Throwable th) {
        simpleMessageBusConnectionImpl.disconnect();
        return Unit.INSTANCE;
    }

    public static boolean f(MessageHandlerHolder messageHandlerHolder) {
        return messageHandlerHolder.isDisposed();
    }

    public static boolean g(MessageBusImpl messageBusImpl, Message message) {
        return message.bus == messageBusImpl;
    }

    public final void checkDisposed$intellij_platform_core() throws Throwable {
        checkBusDisposed();
        checkOwnerDisposed();
    }

    public void clearSubscriberCache$intellij_platform_core(Object[] topicAndHandlerPairs) {
        topicAndHandlerPairs.getClass();
        for (int i = 0; i < topicAndHandlerPairs.length; i += 2) {
            ConcurrentMap<Topic<?>, Object[]> concurrentMap = this.subscriberCache;
            TypeIntrinsics.asMutableMap(concurrentMap).remove(topicAndHandlerPairs[i]);
        }
    }

    public Object[] computeSubscribers$intellij_platform_core(Topic<?> topic) {
        topic.getClass();
        ArrayList arrayList = new ArrayList();
        doComputeSubscribers$intellij_platform_core(topic, arrayList, true);
        if (!arrayList.isEmpty()) {
            return arrayList.toArray(new Object[0]);
        }
        Object[] objArr = ArrayUtilRt.EMPTY_OBJECT_ARRAY;
        objArr.getClass();
        return objArr;
    }

    @Override // com.intellij.util.messages.MessageBus
    public SimpleMessageBusConnection connect(CoroutineScope coroutineScope) throws Throwable {
        coroutineScope.getClass();
        Job job = JobKt.getJob(coroutineScope.getCoroutineContext());
        JobKt.ensureActive(job);
        checkDisposed$intellij_platform_core();
        final SimpleMessageBusConnectionImpl simpleMessageBusConnectionImpl = new SimpleMessageBusConnectionImpl(this);
        try {
            this.subscribers.add(simpleMessageBusConnectionImpl);
            return simpleMessageBusConnectionImpl;
        } finally {
            job.invokeOnCompletion(new Function1() { // from class: com.intellij.util.messages.impl.a
                public final Object invoke(Object obj) {
                    return MessageBusImpl.e(simpleMessageBusConnectionImpl, (Throwable) obj);
                }
            });
        }
    }

    public <L> MessagePublisher<L> createPublisher$intellij_platform_core(Topic<L> topic, Topic.BroadcastDirection direction) {
        topic.getClass();
        direction.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[direction.ordinal()];
        if (i == 1) {
            return new ToParentMessagePublisher(topic, this);
        }
        if (i == 2) {
            w01.a("Broadcast direction TO_DIRECT_CHILDREN is allowed only for app level message bus. Please publish to app level message bus or change topic broadcast direction to NONE or TO_PARENT");
            return null;
        }
        LOG.error("Topic " + topic.getListenerClass().getName() + " broadcast direction TO_CHILDREN is not allowed for module level message bus. Please change to NONE or TO_PARENT");
        return new MessagePublisher<>(topic, this);
    }

    public final void deliverImmediately$intellij_platform_core(MessageBusConnectionImpl connection) throws Throwable {
        List<Message> listDeliverImmediately;
        connection.getClass();
        checkBusDisposed();
        if (this.owner.isDisposed()) {
            return;
        }
        MessageQueue queue$intellij_platform_core = this.rootBus.getQueue$intellij_platform_core();
        ArrayDeque<Message> arrayDeque = queue$intellij_platform_core.queue;
        if (arrayDeque.isEmpty() || (listDeliverImmediately = MessageBusImplKt.deliverImmediately(connection, arrayDeque)) == null) {
            return;
        }
        int size = listDeliverImmediately.size() - 1;
        if (size >= 0) {
            while (true) {
                int i = size - 1;
                arrayDeque.addFirst((Message) listDeliverImmediately.get(size));
                if (i < 0) {
                    break;
                } else {
                    size = i;
                }
            }
        }
        Throwable thDeliverMessage = null;
        for (Message message : listDeliverImmediately) {
            arrayDeque.removeFirstOccurrence(message);
            thDeliverMessage = MessageBusImplKt.deliverMessage(message, queue$intellij_platform_core, thDeliverMessage);
        }
        if (thDeliverMessage != null) {
            MessageBusImplKt.throwError(thDeliverMessage);
        }
    }

    @Override // com.intellij.util.messages.MessageBus
    public void dispose() throws Throwable {
        checkBusDisposed();
        this.disposeState = new BusState.Disposed(new Throwable());
        disposeChildren$intellij_platform_core();
        Disposable disposable = this.connectionDisposable;
        if (disposable != null) {
            Disposer.dispose(disposable);
        }
        ArrayDeque<Message> arrayDeque = this.rootBus.getQueue$intellij_platform_core().queue;
        final Function1 function1 = new Function1() { // from class: pz9
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MessageBusImpl.g(this.b, (Message) obj));
            }
        };
        arrayDeque.removeIf(new Predicate() { // from class: qz9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MessageBusImpl.d(function1, obj);
            }
        });
        CompositeMessageBus compositeMessageBus = this.parentBus;
        if (compositeMessageBus != null) {
            compositeMessageBus.onChildBusDisposed$intellij_platform_core(this);
        }
    }

    public void disposeChildren$intellij_platform_core() {
    }

    public void doComputeSubscribers$intellij_platform_core(Topic<?> topic, List<? super Object> result, boolean subscribeLazyListeners) {
        topic.getClass();
        result.getClass();
        Iterator<MessageHandlerHolder> it = this.subscribers.iterator();
        it.getClass();
        while (it.hasNext()) {
            MessageHandlerHolder next = it.next();
            if (!next.isDisposed()) {
                next.collectHandlers(topic, result);
            }
        }
    }

    public boolean hasChildren$intellij_platform_core() {
        return false;
    }

    @Override // com.intellij.util.messages.MessageBus
    public boolean isDisposed() {
        return (this.disposeState instanceof BusState.Disposed) || this.owner.isDisposed();
    }

    public boolean notifyConnectionTerminated$intellij_platform_core(Object[] topicAndHandlerPairs) {
        topicAndHandlerPairs.getClass();
        if (!Intrinsics.areEqual(this.disposeState, BusState.Alive.INSTANCE)) {
            return false;
        }
        this.rootBus.scheduleEmptyConnectionRemoving();
        return MessageBusImplKt.clearSubscriberCacheOnConnectionTerminated(topicAndHandlerPairs, this);
    }

    public final void notifyOnSubscription$intellij_platform_core(Topic<?> topic) {
        topic.getClass();
        this.subscriberCache.remove(topic);
        if (topic.getBroadcastDirection() != Topic.BroadcastDirection.TO_CHILDREN) {
            return;
        }
        MessageBusImpl messageBusImpl = this;
        while (true) {
            messageBusImpl = messageBusImpl.parentBus;
            if (messageBusImpl == null) {
                break;
            } else {
                messageBusImpl.subscriberCache.remove(topic);
            }
        }
        if (hasChildren$intellij_platform_core()) {
            notifyOnSubscriptionToTopicToChildren$intellij_platform_core(topic);
        }
    }

    public void notifyOnSubscriptionToTopicToChildren$intellij_platform_core(Topic<?> topic) {
        topic.getClass();
    }

    public void removeEmptyConnectionsRecursively() {
        ConcurrentLinkedQueue<MessageHandlerHolder> concurrentLinkedQueue = this.subscribers;
        final Function1 function1 = new Function1() { // from class: rz9
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MessageBusImpl.f((MessageBusImpl.MessageHandlerHolder) obj));
            }
        };
        concurrentLinkedQueue.removeIf(new Predicate() { // from class: sz9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return MessageBusImpl.a(function1, obj);
            }
        });
    }

    @Override // com.intellij.util.messages.MessageBus
    public SimpleMessageBusConnection simpleConnect() throws Throwable {
        checkDisposed$intellij_platform_core();
        SimpleMessageBusConnectionImpl simpleMessageBusConnectionImpl = new SimpleMessageBusConnectionImpl(this);
        this.subscribers.add(simpleMessageBusConnectionImpl);
        return simpleMessageBusConnectionImpl;
    }

    @Override // com.intellij.util.messages.MessageBus
    public <L> L syncPublisher(Topic<L> topic) throws Throwable {
        topic.getClass();
        checkDisposed$intellij_platform_core();
        ConcurrentMap<Topic<?>, Object> concurrentMap = this.publisherCache;
        final Function1 function1 = new Function1() { // from class: tz9
            public final Object invoke(Object obj) {
                return MessageBusImpl.b(this.b, (Topic) obj);
            }
        };
        L l = (L) concurrentMap.computeIfAbsent(topic, new Function() { // from class: uz9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MessageBusImpl.c(function1, obj);
            }
        });
        l.getClass();
        return l;
    }

    public String toString() {
        return "MessageBus(owner=" + this.owner + ", disposeState= " + this.disposeState + ')';
    }

    @Override // com.intellij.util.messages.MessageBus
    public MessageBusConnection connect(Disposable parentDisposable) throws Throwable {
        parentDisposable.getClass();
        checkDisposed$intellij_platform_core();
        MessageBusConnectionImpl messageBusConnectionImpl = new MessageBusConnectionImpl(this);
        this.subscribers.add(messageBusConnectionImpl);
        Disposer.register(parentDisposable, messageBusConnectionImpl);
        return messageBusConnectionImpl;
    }

    @Override // com.intellij.util.messages.MessageBus
    public MessageBusConnection connect() {
        Disposable disposable = this.connectionDisposable;
        disposable.getClass();
        return connect(disposable);
    }
}
