package com.intellij.util.messages.impl;

import java.util.ArrayDeque;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/intellij/util/messages/impl/MessageQueue;", "", "<init>", "()V", "queue", "Ljava/util/ArrayDeque;", "Lcom/intellij/util/messages/impl/Message;", "current", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class MessageQueue {
    public Message current;
    public final ArrayDeque<Message> queue = new ArrayDeque<>();
}
