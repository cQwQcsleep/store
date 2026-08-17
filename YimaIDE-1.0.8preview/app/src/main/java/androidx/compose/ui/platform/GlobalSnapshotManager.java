package androidx.compose.ui.platform;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/GlobalSnapshotManager;", "", "<init>", "()V", "started", "Ljava/util/concurrent/atomic/AtomicBoolean;", "sent", "ensureStarted", "", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class GlobalSnapshotManager {
    public static final GlobalSnapshotManager INSTANCE = new GlobalSnapshotManager();
    private static final AtomicBoolean started = new AtomicBoolean(false);
    private static final AtomicBoolean sent = new AtomicBoolean(false);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1", f = "GlobalSnapshotManager.android.kt", i = {0}, l = {64}, m = "invokeSuspend", n = {"$this$consume$iv$iv"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Channel<Unit> $channel;
        Object L$0;
        Object L$1;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Channel<Unit> channel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$channel = channel;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$channel, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0036 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:19:0x003f A[Catch: all -> 0x0018, TryCatch #0 {all -> 0x0018, blocks: (B:6:0x0014, B:17:0x0037, B:19:0x003f, B:14:0x002a, B:20:0x0053, B:13:0x0025), top: B:27:0x0008 }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0034 -> B:17:0x0037). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r7) {
            /*
                r6 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r6.label
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L20
                if (r1 != r2) goto L1a
                java.lang.Object r1 = r6.L$1
                kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
                java.lang.Object r4 = r6.L$0
                kotlinx.coroutines.channels.ReceiveChannel r4 = (kotlinx.coroutines.channels.ReceiveChannel) r4
                kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L18
                goto L37
            L18:
                r6 = move-exception
                goto L5b
            L1a:
                java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                k2d.a(r6)
                return r3
            L20:
                kotlin.ResultKt.throwOnFailure(r7)
                kotlinx.coroutines.channels.Channel<kotlin.Unit> r4 = r6.$channel
                kotlinx.coroutines.channels.ChannelIterator r7 = r4.iterator()     // Catch: java.lang.Throwable -> L18
                r1 = r7
            L2a:
                r6.L$0 = r4     // Catch: java.lang.Throwable -> L18
                r6.L$1 = r1     // Catch: java.lang.Throwable -> L18
                r6.label = r2     // Catch: java.lang.Throwable -> L18
                java.lang.Object r7 = r1.hasNext(r6)     // Catch: java.lang.Throwable -> L18
                if (r7 != r0) goto L37
                return r0
            L37:
                java.lang.Boolean r7 = (java.lang.Boolean) r7     // Catch: java.lang.Throwable -> L18
                boolean r7 = r7.booleanValue()     // Catch: java.lang.Throwable -> L18
                if (r7 == 0) goto L53
                java.lang.Object r7 = r1.next()     // Catch: java.lang.Throwable -> L18
                kotlin.Unit r7 = (kotlin.Unit) r7     // Catch: java.lang.Throwable -> L18
                java.util.concurrent.atomic.AtomicBoolean r7 = androidx.compose.ui.platform.GlobalSnapshotManager.access$getSent$p()     // Catch: java.lang.Throwable -> L18
                r5 = 0
                r7.set(r5)     // Catch: java.lang.Throwable -> L18
                androidx.compose.runtime.snapshots.Snapshot$Companion r7 = androidx.compose.runtime.snapshots.Snapshot.INSTANCE     // Catch: java.lang.Throwable -> L18
                r7.sendApplyNotifications()     // Catch: java.lang.Throwable -> L18
                goto L2a
            L53:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L18
                kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r3)
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                return r6
            L5b:
                throw r6     // Catch: java.lang.Throwable -> L5c
            L5c:
                r7 = move-exception
                kotlinx.coroutines.channels.ChannelsKt.cancelConsumed(r4, r6)
                throw r7
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.GlobalSnapshotManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private GlobalSnapshotManager() {
    }

    public final void ensureStarted() {
        if (started.compareAndSet(false, true)) {
            final Channel channelChannel$default = ChannelKt.Channel$default(1, (BufferOverflow) null, (Function1) null, 6, (Object) null);
            BuildersKt.launch$default(CoroutineScopeKt.CoroutineScope(AndroidUiDispatcher.INSTANCE.getMain()), (CoroutineContext) null, (CoroutineStart) null, new AnonymousClass1(channelChannel$default, null), 3, (Object) null);
            Snapshot.INSTANCE.registerGlobalWriteObserver(new Function1<Object, Unit>() { // from class: androidx.compose.ui.platform.GlobalSnapshotManager.ensureStarted.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m5168invoke(Object obj) {
                    if (GlobalSnapshotManager.sent.compareAndSet(false, true)) {
                        channelChannel$default.trySend-JP2dKIU(Unit.INSTANCE);
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    m5168invoke(obj);
                    return Unit.INSTANCE;
                }
            });
        }
    }
}
