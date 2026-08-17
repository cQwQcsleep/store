package com.intellij.util.messages;

import com.intellij.openapi.Disposable;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0001H&J\u0010\u0010\u0005\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH&J%\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H&¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\r0\u0010H'¢\u0006\u0002\u0010\u0011J\b\u0010\u0013\u001a\u00020\u0014H&J\u0014\u0010\u0018\u001a\u00020\u00162\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u0010H&R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004R\u0012\u0010\u0015\u001a\u00020\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0017ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0001"}, d2 = {"Lcom/intellij/util/messages/MessageBus;", "Lcom/intellij/openapi/Disposable;", "parent", "getParent", "()Lcom/intellij/util/messages/MessageBus;", "connect", "Lcom/intellij/util/messages/MessageBusConnection;", "simpleConnect", "Lcom/intellij/util/messages/SimpleMessageBusConnection;", "parentDisposable", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "syncPublisher", "L", "", "topic", "Lcom/intellij/util/messages/Topic;", "(Lcom/intellij/util/messages/Topic;)Ljava/lang/Object;", "syncAndPreloadPublisher", "dispose", "", "isDisposed", "", "()Z", "hasUndeliveredEvents", "intellij.platform.extensions"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface MessageBus extends Disposable {
    MessageBusConnection connect();

    MessageBusConnection connect(Disposable parentDisposable);

    SimpleMessageBusConnection connect(CoroutineScope coroutineScope);

    void dispose();

    boolean isDisposed();

    SimpleMessageBusConnection simpleConnect();

    <L> L syncPublisher(Topic<L> topic);
}
