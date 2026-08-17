package com.intellij.util.messages.impl;

import com.intellij.openapi.extensions.ExtensionNotApplicableException;
import com.intellij.openapi.extensions.PluginDescriptor;
import com.intellij.openapi.progress.Cancellation;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.messages.ListenerDescriptor;
import com.intellij.util.messages.MessageBusOwner;
import com.intellij.util.messages.Topic;
import com.intellij.util.messages.impl.CompositeMessageBus;
import com.intellij.util.messages.impl.Message;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\bJ \u0010\u0013\u001a\u00020\u00142\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u0016J\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0001J\u0015\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u001eJ/\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H!0 \"\u0004\b\u0000\u0010!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H!0#2\u0006\u0010$\u001a\u00020%H\u0000¢\u0006\u0002\b&J#\u0010'\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010)0(2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#H\u0000¢\u0006\u0004\b*\u0010+J1\u0010,\u001a\u00020\u00142\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#2\u000e\u0010-\u001a\n\u0012\u0006\b\u0000\u0012\u00020)0\n2\u0006\u0010.\u001a\u00020\u0018H\u0000¢\u0006\u0002\b/J\u001c\u0010.\u001a\u00020\u0014\"\u0004\b\u0000\u0010!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H!0#H\u0002J\u0019\u00100\u001a\u00020\u00142\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030#H\u0000¢\u0006\u0002\b1J\u001d\u00102\u001a\u00020\u00182\f\u00103\u001a\b\u0012\u0004\u0012\u00020)0(H\u0000¢\u0006\u0004\b4\u00105J\u001d\u00106\u001a\u00020\u00142\f\u00103\u001a\b\u0012\u0004\u0012\u00020)0(H\u0000¢\u0006\u0004\b7\u00108J\u0006\u00109\u001a\u00020\u0014J\u0006\u0010:\u001a\u00020\u0014J\u001c\u0010;\u001a\u00020\u00142\u0006\u0010<\u001a\u00020=2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00120\rJ\u0018\u0010?\u001a\u00020\u00142\u0010\u0010@\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030B0AJ\b\u0010C\u001a\u00020\u0014H\u0007J\r\u0010D\u001a\u00020\u0014H\u0000¢\u0006\u0002\bER<\u0010\t\u001a.\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00010\u0001 \u000b*\u0015\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\u00010\u00010\r¢\u0006\u0002\b\f0\n¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR \u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\n0\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/intellij/util/messages/impl/CompositeMessageBus;", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "Lcom/intellij/util/messages/impl/MessageBusEx;", "owner", "Lcom/intellij/util/messages/MessageBusOwner;", "parentBus", "<init>", "(Lcom/intellij/util/messages/MessageBusOwner;Lcom/intellij/util/messages/impl/CompositeMessageBus;)V", "(Lcom/intellij/util/messages/MessageBusOwner;)V", "childBuses", "", "kotlin.jvm.PlatformType", "Lorg/jetbrains/annotations/NotNull;", "", "Ljava/util/List;", "topicClassToListenerDescriptor", "", "", "Lcom/intellij/util/messages/ListenerDescriptor;", "setLazyListeners", "", "map", "Ljava/util/concurrent/ConcurrentMap;", "hasChildren", "", "hasChildren$intellij_platform_core", "addChild", "bus", "onChildBusDisposed", "childBus", "onChildBusDisposed$intellij_platform_core", "createPublisher", "Lcom/intellij/util/messages/impl/MessagePublisher;", "L", "topic", "Lcom/intellij/util/messages/Topic;", "direction", "Lcom/intellij/util/messages/Topic$BroadcastDirection;", "createPublisher$intellij_platform_core", "computeSubscribers", "", "", "computeSubscribers$intellij_platform_core", "(Lcom/intellij/util/messages/Topic;)[Ljava/lang/Object;", "doComputeSubscribers", "result", "subscribeLazyListeners", "doComputeSubscribers$intellij_platform_core", "notifyOnSubscriptionToTopicToChildren", "notifyOnSubscriptionToTopicToChildren$intellij_platform_core", "notifyConnectionTerminated", "topicAndHandlerPairs", "notifyConnectionTerminated$intellij_platform_core", "([Ljava/lang/Object;)Z", "clearSubscriberCache", "clearSubscriberCache$intellij_platform_core", "([Ljava/lang/Object;)V", "removeEmptyConnectionsRecursively", "clearPublisherCache", "unsubscribeLazyListeners", "module", "Lcom/intellij/ide/plugins/IdeaPluginDescriptor;", "listenerDescriptors", "disconnectPluginConnections", "predicate", "Ljava/util/function/Predicate;", "Ljava/lang/Class;", "clearAllSubscriberCache", "disposeChildren", "disposeChildren$intellij_platform_core", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class CompositeMessageBus extends MessageBusImpl implements MessageBusEx {
    private final List<MessageBusImpl> childBuses;
    private volatile Map<String, List<ListenerDescriptor>> topicClassToListenerDescriptor;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CompositeMessageBus(MessageBusOwner messageBusOwner) {
        super(messageBusOwner);
        messageBusOwner.getClass();
        List<MessageBusImpl> listCreateLockFreeCopyOnWriteList = ContainerUtil.createLockFreeCopyOnWriteList();
        listCreateLockFreeCopyOnWriteList.getClass();
        this.childBuses = listCreateLockFreeCopyOnWriteList;
        this.topicClassToListenerDescriptor = CompositeMessageBusKt.EMPTY_MAP;
    }

    public static boolean h(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static boolean i(Object[] objArr, Message message) {
        message.getClass();
        return CompositeMessageBusKt.nullizeHandlersFromMessage(message, objArr);
    }

    public static List j(PluginDescriptor pluginDescriptor) {
        pluginDescriptor.getClass();
        return new ArrayList();
    }

    public static Unit k(Map map, final Topic topic, final CompositeMessageBus compositeMessageBus) {
        List<ListenerDescriptor> list = (List) map.remove(topic.getListenerClass().getName());
        if (list == null) {
            return Unit.INSTANCE;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ListenerDescriptor listenerDescriptor : list) {
            try {
                PluginDescriptor pluginDescriptor = listenerDescriptor.pluginDescriptor;
                final Function1 function1 = new Function1() { // from class: fo2
                    public final Object invoke(Object obj) {
                        return CompositeMessageBus.j((PluginDescriptor) obj);
                    }
                };
                List list2 = (List) linkedHashMap.computeIfAbsent(pluginDescriptor, new Function() { // from class: go2
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return CompositeMessageBus.l(function1, obj);
                    }
                });
                Object objCreateListener = compositeMessageBus.owner.createListener(listenerDescriptor);
                objCreateListener.getClass();
                list2.add(objCreateListener);
            } catch (ProcessCanceledException e) {
                throw e;
            } catch (ExtensionNotApplicableException unused) {
            } catch (Throwable th) {
                MessageBusImpl.LOG.error("Cannot create listener", th);
            }
        }
        linkedHashMap.forEach(new BiConsumer() { // from class: ho2
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                CompositeMessageBus.m(this.a, topic, (PluginDescriptor) obj, (List) obj2);
            }
        });
        return Unit.INSTANCE;
    }

    public static List l(Function1 function1, Object obj) {
        return (List) function1.invoke(obj);
    }

    public static void m(CompositeMessageBus compositeMessageBus, Topic topic, PluginDescriptor pluginDescriptor, List list) {
        pluginDescriptor.getClass();
        list.getClass();
        compositeMessageBus.subscribers.add(new DescriptorBasedMessageBusConnection(pluginDescriptor, topic, list));
    }

    private final <L> void subscribeLazyListeners(final Topic<L> topic) {
        final Map<String, List<ListenerDescriptor>> map;
        if (topic.getListenerClass() == Runnable.class || (map = this.topicClassToListenerDescriptor) == CompositeMessageBusKt.EMPTY_MAP) {
            return;
        }
        Cancellation.computeInNonCancelableSection(new ThrowableComputable() { // from class: eo2
            public final Object compute() {
                return CompositeMessageBus.k(map, topic, this);
            }
        });
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final void clearSubscriberCache$intellij_platform_core(Object[] topicAndHandlerPairs) {
        topicAndHandlerPairs.getClass();
        super.clearSubscriberCache$intellij_platform_core(topicAndHandlerPairs);
        Iterator<MessageBusImpl> it = this.childBuses.iterator();
        while (it.hasNext()) {
            it.next().clearSubscriberCache$intellij_platform_core(topicAndHandlerPairs);
        }
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final Object[] computeSubscribers$intellij_platform_core(Topic<?> topic) {
        topic.getClass();
        if (!this.owner.isDisposed()) {
            return super.computeSubscribers$intellij_platform_core(topic);
        }
        Object[] objArr = ArrayUtilRt.EMPTY_OBJECT_ARRAY;
        objArr.getClass();
        return objArr;
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final <L> MessagePublisher<L> createPublisher$intellij_platform_core(Topic<L> topic, Topic.BroadcastDirection direction) {
        topic.getClass();
        direction.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[direction.ordinal()];
        if (i == 1) {
            return new ToParentMessagePublisher(topic, this);
        }
        if (i != 2) {
            return new MessagePublisher<>(topic, this);
        }
        if (this.parentBus == null) {
            return new ToDirectChildrenMessagePublisher(topic, this, this.childBuses);
        }
        wec.a("Broadcast direction TO_DIRECT_CHILDREN is allowed only for app level message bus. Please publish to app level message bus or change topic ", topic.getListenerClass(), " broadcast direction to NONE or TO_PARENT");
        return null;
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final void disposeChildren$intellij_platform_core() {
        Iterator<MessageBusImpl> it = this.childBuses.iterator();
        while (it.hasNext()) {
            Disposer.dispose(it.next());
        }
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final void doComputeSubscribers$intellij_platform_core(Topic<?> topic, List<? super Object> result, boolean subscribeLazyListeners) {
        topic.getClass();
        result.getClass();
        if (subscribeLazyListeners) {
            subscribeLazyListeners(topic);
        }
        super.doComputeSubscribers$intellij_platform_core(topic, result, subscribeLazyListeners);
        if (topic.getBroadcastDirection() == Topic.BroadcastDirection.TO_CHILDREN) {
            for (MessageBusImpl messageBusImpl : this.childBuses) {
                if (!messageBusImpl.isDisposed()) {
                    messageBusImpl.doComputeSubscribers$intellij_platform_core(topic, result, !messageBusImpl.owner.isParentLazyListenersIgnored());
                }
            }
        }
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final boolean hasChildren$intellij_platform_core() {
        return !this.childBuses.isEmpty();
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final boolean notifyConnectionTerminated$intellij_platform_core(final Object[] topicAndHandlerPairs) {
        topicAndHandlerPairs.getClass();
        if (!super.notifyConnectionTerminated$intellij_platform_core(topicAndHandlerPairs)) {
            return false;
        }
        Iterator<MessageBusImpl> it = this.childBuses.iterator();
        while (it.hasNext()) {
            it.next().clearSubscriberCache$intellij_platform_core(topicAndHandlerPairs);
        }
        ArrayDeque<Message> arrayDeque = this.rootBus.getQueue$intellij_platform_core().queue;
        final Function1 function1 = new Function1() { // from class: io2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(CompositeMessageBus.i(topicAndHandlerPairs, (Message) obj));
            }
        };
        arrayDeque.removeIf(new Predicate() { // from class: jo2
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return CompositeMessageBus.h(function1, obj);
            }
        });
        return false;
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final void notifyOnSubscriptionToTopicToChildren$intellij_platform_core(Topic<?> topic) {
        topic.getClass();
        for (MessageBusImpl messageBusImpl : this.childBuses) {
            messageBusImpl.subscriberCache.remove(topic);
            messageBusImpl.notifyOnSubscriptionToTopicToChildren$intellij_platform_core(topic);
        }
    }

    public final void onChildBusDisposed$intellij_platform_core(MessageBusImpl childBus) {
        childBus.getClass();
        boolean zRemove = this.childBuses.remove(childBus);
        CompositeMessageBusKt.childrenListChanged(this);
        MessageBusImpl.LOG.assertTrue(zRemove);
    }

    @Override // com.intellij.util.messages.impl.MessageBusImpl
    public final void removeEmptyConnectionsRecursively() {
        super.removeEmptyConnectionsRecursively();
        Iterator<MessageBusImpl> it = this.childBuses.iterator();
        while (it.hasNext()) {
            it.next().removeEmptyConnectionsRecursively();
        }
    }

    @Override // com.intellij.util.messages.impl.MessageBusEx
    public final void setLazyListeners(ConcurrentMap<String, List<ListenerDescriptor>> map) {
        map.getClass();
        Map<String, List<ListenerDescriptor>> map2 = this.topicClassToListenerDescriptor;
        if (map2 == CompositeMessageBusKt.EMPTY_MAP) {
            this.topicClassToListenerDescriptor = map;
            return;
        }
        map2.putAll(map);
        RootBus rootBus = this.rootBus;
        if (rootBus != this) {
            rootBus.subscriberCache.clear();
        }
        this.subscriberCache.clear();
    }
}
