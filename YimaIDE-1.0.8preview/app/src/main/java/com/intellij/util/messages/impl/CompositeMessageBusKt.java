package com.intellij.util.messages.impl;

import com.intellij.util.messages.ListenerDescriptor;
import com.intellij.util.messages.Topic;
import com.intellij.util.messages.impl.CompositeMessageBusKt;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002\u001a.\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000fH\u0002\u001a#\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0015H\u0002¢\u0006\u0002\u0010\u0016\"6\u0010\u0000\u001a*\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001j\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003`\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"EMPTY_MAP", "Ljava/util/HashMap;", "", "", "Lcom/intellij/util/messages/ListenerDescriptor;", "Lkotlin/collections/HashMap;", "childrenListChanged", "", "changedBus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "computeNewHandlers", "", "", "handlers", "excludeClassNames", "", "nullizeHandlersFromMessage", "", "message", "Lcom/intellij/util/messages/impl/Message;", "topicAndHandlerPairs", "", "(Lcom/intellij/util/messages/impl/Message;[Ljava/lang/Object;)Z", "intellij.platform.core"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class CompositeMessageBusKt {
    private static final HashMap<String, List<ListenerDescriptor>> EMPTY_MAP = new HashMap<>();

    public static boolean a(Topic topic) {
        return topic.getBroadcastDirection() == Topic.BroadcastDirection.TO_CHILDREN;
    }

    public static boolean b(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void childrenListChanged(MessageBusImpl messageBusImpl) {
        do {
            Set<Topic<?>> setKeySet = messageBusImpl.subscriberCache.keySet();
            final Function1 function1 = new Function1() { // from class: ko2
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(CompositeMessageBusKt.a((Topic) obj));
                }
            };
            setKeySet.removeIf(new Predicate() { // from class: lo2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return CompositeMessageBusKt.b(function1, obj);
                }
            });
            messageBusImpl = messageBusImpl.parentBus;
        } while (messageBusImpl != null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean nullizeHandlersFromMessage(Message message, Object[] objArr) {
        Object[] objArr2;
        int length = message.handlers.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            objArr2 = message.handlers;
            if (i >= length) {
                break;
            }
            Object obj = objArr2[i];
            if (obj == null) {
                i2++;
            }
            for (int i3 = 0; i3 < objArr.length; i3 += 2) {
                if (message.topic == objArr[i3] && obj == objArr[i3 + 1]) {
                    message.handlers[i] = null;
                    i2++;
                }
            }
            i++;
        }
        return i2 == objArr2.length;
    }
}
