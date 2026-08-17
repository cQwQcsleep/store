package com.intellij.util.messages;

import com.intellij.openapi.Disposable;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u00012\u00020\u0002J \u0010\u0003\u001a\u00020\u0004\"\b\b\u0000\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bH&J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH&J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0004H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lcom/intellij/util/messages/MessageBusConnection;", "Lcom/intellij/util/messages/SimpleMessageBusConnection;", "Lcom/intellij/openapi/Disposable;", "subscribe", "", "L", "", "topic", "Lcom/intellij/util/messages/Topic;", "setDefaultHandler", "handler", "Lcom/intellij/util/messages/MessageHandler;", "runnable", "Ljava/lang/Runnable;", "deliverImmediately", "intellij.platform.extensions"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MessageBusConnection extends Disposable, SimpleMessageBusConnection {
    void deliverImmediately();
}
