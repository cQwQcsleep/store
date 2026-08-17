package com.intellij.util.messages.impl;

import com.intellij.util.messages.Topic;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public /* synthetic */ class MessagePublisher$publish$handlers$1 extends FunctionReferenceImpl implements Function1<Topic<?>, Object[]> {
    public MessagePublisher$publish$handlers$1(Object obj) {
        super(1, obj, MessageBusImpl.class, "computeSubscribers", "computeSubscribers$intellij_platform_core(Lcom/intellij/util/messages/Topic;)[Ljava/lang/Object;", 0);
    }

    public final Object[] invoke(Topic<?> topic) {
        topic.getClass();
        return ((MessageBusImpl) ((CallableReference) this).receiver).computeSubscribers$intellij_platform_core(topic);
    }
}
