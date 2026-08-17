package com.intellij.util.messages.impl;

import com.intellij.codeWithMe.ClientId;
import com.intellij.util.messages.Topic;
import java.lang.invoke.MethodHandle;
import java.util.Arrays;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001BM\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0010\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t\u0012\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0014\u001a\u00020\u0007H\u0016R\u0014\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u001a\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\t8\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/intellij/util/messages/impl/Message;", "", "topic", "Lcom/intellij/util/messages/Topic;", "method", "Ljava/lang/invoke/MethodHandle;", "methodName", "", "args", "", "handlers", "bus", "Lcom/intellij/util/messages/impl/MessageBusImpl;", "<init>", "(Lcom/intellij/util/messages/Topic;Ljava/lang/invoke/MethodHandle;Ljava/lang/String;[Ljava/lang/Object;[Ljava/lang/Object;Lcom/intellij/util/messages/impl/MessageBusImpl;)V", "[Ljava/lang/Object;", "clientId", "Lcom/intellij/codeWithMe/ClientId;", "currentHandlerIndex", "", "toString", "intellij.platform.core"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class Message {
    public final Object[] args;
    public final MessageBusImpl bus;
    public final ClientId clientId;
    public int currentHandlerIndex;
    public final Object[] handlers;
    public final MethodHandle method;
    public final String methodName;
    public final Topic<?> topic;

    public Message(Topic<?> topic, MethodHandle methodHandle, String str, Object[] objArr, Object[] objArr2, MessageBusImpl messageBusImpl) {
        topic.getClass();
        methodHandle.getClass();
        str.getClass();
        objArr2.getClass();
        messageBusImpl.getClass();
        this.topic = topic;
        this.method = methodHandle;
        this.methodName = str;
        this.args = objArr;
        this.handlers = objArr2;
        this.bus = messageBusImpl;
        this.clientId = ClientId.Companion.getCurrentOrNull();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Message(topic=");
        sb.append(this.topic);
        sb.append(", method=");
        sb.append(this.methodName);
        sb.append(", args=");
        String string = Arrays.toString(this.args);
        string.getClass();
        sb.append(string);
        sb.append(", handlers=");
        String string2 = Arrays.toString(this.handlers);
        string2.getClass();
        sb.append(string2);
        sb.append(')');
        return sb.toString();
    }
}
