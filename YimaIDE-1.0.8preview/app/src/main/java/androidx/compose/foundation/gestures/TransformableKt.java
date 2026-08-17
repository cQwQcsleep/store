package androidx.compose.foundation.gestures;

import androidx.compose.foundation.gestures.TransformableKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.Channel;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a&\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a(\u0010\f\u001a\u00020\r*\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0014\u001a\u001a\u0010\u0015\u001a\u00020\t*\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0017\u001a\u001c\u0010\u0018\u001a\u0004\u0018\u00010\t*\u00020\u00162\u0006\u0010\u0012\u001a\u00020\u0013H\u0082@¢\u0006\u0002\u0010\u0017\u001a<\u0010\u0019\u001a\u00020\r*\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\bH\u0082@¢\u0006\u0002\u0010\u001b\"\u000e\u0010\n\u001a\u00020\u000bX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"transformable", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/gestures/TransformableState;", "lockRotationOnZoomPan", "", "enabled", "canPan", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "SCROLL_FACTOR", "", "detectZoomByCtrlMouseScroll", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "channel", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/compose/foundation/gestures/TransformEvent;", "scrollConfig", "Landroidx/compose/foundation/gestures/ScrollConfig;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlinx/coroutines/channels/Channel;Landroidx/compose/foundation/gestures/ScrollConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstCtrlMouseScroll", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/gestures/ScrollConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitCtrlMouseScrollOrNull", "detectZoom", "panZoomLock", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLkotlinx/coroutines/channels/Channel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TransformableKt {
    public static final float SCROLL_FACTOR = 545.0f;

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$awaitCtrlMouseScrollOrNull$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt", f = "Transformable.kt", i = {0, 0}, l = {312}, m = "awaitCtrlMouseScrollOrNull", n = {"$this$awaitCtrlMouseScrollOrNull", "scrollConfig"}, s = {"L$0", "L$1"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformableKt.awaitCtrlMouseScrollOrNull(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt", f = "Transformable.kt", i = {0, 0}, l = {299}, m = "awaitFirstCtrlMouseScroll", n = {"$this$awaitFirstCtrlMouseScroll", "scrollConfig"}, s = {"L$0", "L$1"}, v = 1)
    public static final class C01331 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C01331(Continuation<? super C01331> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformableKt.awaitFirstCtrlMouseScroll(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$detectZoom$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt", f = "Transformable.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, l = {337, 339, 385}, m = "detectZoom", n = {"$this$detectZoom", "channel", "canPan", "panZoomLock", "rotation", "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom", "$this$detectZoom", "channel", "canPan", "panZoomLock", "rotation", "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom", "$this$detectZoom", "channel", "canPan", "event", "panZoomLock", "rotation", "zoom", "pan", "pastTouchSlop", "touchSlop", "lockedToPanZoom", "canceled"}, s = {"L$0", "L$1", "L$2", "Z$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1", "L$0", "L$1", "L$2", "Z$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1", "L$0", "L$1", "L$2", "L$3", "Z$0", "F$0", "F$1", "J$0", "I$0", "F$2", "I$1", "I$2"}, v = 1)
    public static final class C01341 extends ContinuationImpl {
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C01341(Continuation<? super C01341> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TransformableKt.detectZoom(null, false, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TransformableKt$detectZoomByCtrlMouseScroll$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TransformableKt$detectZoomByCtrlMouseScroll$2", f = "Transformable.kt", i = {0, 1}, l = {272, 284}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "$this$awaitPointerEventScope"}, s = {"L$0", "L$0"}, v = 1)
    public static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Channel<TransformEvent> $channel;
        final /* synthetic */ CoroutineContext $currentContext;
        final /* synthetic */ ScrollConfig $scrollConfig;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(CoroutineContext coroutineContext, ScrollConfig scrollConfig, Channel<TransformEvent> channel, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$currentContext = coroutineContext;
            this.$scrollConfig = scrollConfig;
            this.$channel = channel;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$currentContext, this.$scrollConfig, this.$channel, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:19:0x003a A[Catch: all -> 0x0017, TRY_ENTER, TryCatch #0 {all -> 0x0017, blocks: (B:7:0x0012, B:26:0x008a, B:28:0x008e, B:23:0x0054, B:19:0x003a, B:22:0x0047, B:14:0x0026), top: B:35:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:21:0x0046  */
        /* JADX WARN: Code duplicated, block: B:22:0x0047 A[Catch: all -> 0x0017, PHI: r1 r13
          0x0047: PHI (r1v2 androidx.compose.ui.input.pointer.AwaitPointerEventScope) = 
          (r1v3 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
          (r1v7 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
         binds: [B:20:0x0044, B:14:0x0026] A[DONT_GENERATE, DONT_INLINE]
          0x0047: PHI (r13v4 java.lang.Object) = (r13v10 java.lang.Object), (r13v0 java.lang.Object) binds: [B:20:0x0044, B:14:0x0026] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x0017, blocks: (B:7:0x0012, B:26:0x008a, B:28:0x008e, B:23:0x0054, B:19:0x003a, B:22:0x0047, B:14:0x0026), top: B:35:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:33:0x00a3  */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0087, code lost:
        
            if (r13 == r0) goto L25;
         */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0087 -> B:26:0x008a). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            AwaitPointerEventScope awaitPointerEventScope;
            long j;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    if (JobKt.isActive(this.$currentContext)) {
                        return Unit.INSTANCE;
                    }
                    ScrollConfig scrollConfig = this.$scrollConfig;
                    this.L$0 = awaitPointerEventScope;
                    this.label = 1;
                    obj = TransformableKt.awaitFirstCtrlMouseScroll(awaitPointerEventScope, scrollConfig, this);
                    if (obj == coroutine_suspended) {
                        j = ((Offset) obj).unbox-impl();
                        this.$channel.trySend-JP2dKIU(TransformEvent.TransformStarted.INSTANCE);
                        this.$channel.trySend-JP2dKIU(new TransformEvent.TransformDelta((float) Math.pow(2.0d, Float.intBitsToFloat((int) (j & 4294967295L)) / 545.0f), Offset.Companion.getZero-F1C5BW0(), 0.0f, null));
                        ScrollConfig scrollConfig2 = this.$scrollConfig;
                        this.L$0 = awaitPointerEventScope;
                        this.label = 2;
                        obj = TransformableKt.awaitCtrlMouseScrollOrNull(awaitPointerEventScope, scrollConfig2, this);
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    j = ((Offset) obj).unbox-impl();
                    this.$channel.trySend-JP2dKIU(TransformEvent.TransformStarted.INSTANCE);
                    this.$channel.trySend-JP2dKIU(new TransformEvent.TransformDelta((float) Math.pow(2.0d, Float.intBitsToFloat((int) (j & 4294967295L)) / 545.0f), Offset.Companion.getZero-F1C5BW0(), 0.0f, null));
                    ScrollConfig scrollConfig3 = this.$scrollConfig;
                    this.L$0 = awaitPointerEventScope;
                    this.label = 2;
                    obj = TransformableKt.awaitCtrlMouseScrollOrNull(awaitPointerEventScope, scrollConfig3, this);
                } else {
                    if (i != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                }
                Offset offset = (Offset) obj;
                if (offset == null) {
                    this.$channel.trySend-JP2dKIU(TransformEvent.TransformStopped.INSTANCE);
                    if (JobKt.isActive(this.$currentContext)) {
                        return Unit.INSTANCE;
                    }
                    ScrollConfig scrollConfig4 = this.$scrollConfig;
                    this.L$0 = awaitPointerEventScope;
                    this.label = 1;
                    obj = TransformableKt.awaitFirstCtrlMouseScroll(awaitPointerEventScope, scrollConfig4, this);
                    if (obj == coroutine_suspended) {
                        j = ((Offset) obj).unbox-impl();
                        this.$channel.trySend-JP2dKIU(TransformEvent.TransformStarted.INSTANCE);
                    }
                    return coroutine_suspended;
                }
                j = offset.unbox-impl();
                this.$channel.trySend-JP2dKIU(new TransformEvent.TransformDelta((float) Math.pow(2.0d, Float.intBitsToFloat((int) (j & 4294967295L)) / 545.0f), Offset.Companion.getZero-F1C5BW0(), 0.0f, null));
                ScrollConfig scrollConfig5 = this.$scrollConfig;
                this.L$0 = awaitPointerEventScope;
                this.label = 2;
                obj = TransformableKt.awaitCtrlMouseScrollOrNull(awaitPointerEventScope, scrollConfig5, this);
            } catch (Throwable th) {
                this.$channel.trySend-JP2dKIU(TransformEvent.TransformStopped.INSTANCE);
                throw th;
            }
        }
    }

    public static boolean a(Offset offset) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object awaitCtrlMouseScrollOrNull(AwaitPointerEventScope awaitPointerEventScope, ScrollConfig scrollConfig, Continuation<? super Offset> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object objAwaitPointerEvent$default = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objAwaitPointerEvent$default);
            anonymousClass1.L$0 = awaitPointerEventScope;
            anonymousClass1.L$1 = scrollConfig;
            anonymousClass1.label = 1;
            objAwaitPointerEvent$default = AwaitPointerEventScope.awaitPointerEvent$default(awaitPointerEventScope, (PointerEventPass) null, anonymousClass1, 1, (Object) null);
            if (objAwaitPointerEvent$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            scrollConfig = (ScrollConfig) anonymousClass1.L$1;
            awaitPointerEventScope = (AwaitPointerEventScope) anonymousClass1.L$0;
            ResultKt.throwOnFailure(objAwaitPointerEvent$default);
        }
        PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent$default;
        if (!PointerEvent_androidKt.isCtrlPressed-5xRPYO0(pointerEvent.getKeyboardModifiers-k7X9c1A()) || !PointerEventType.equals-impl0(pointerEvent.getType-7fucELk(), PointerEventType.Companion.getScroll-7fucELk())) {
            return null;
        }
        long jMo523calculateMouseWheelScroll8xgXZGE = scrollConfig.mo523calculateMouseWheelScroll8xgXZGE(awaitPointerEventScope, pointerEvent, awaitPointerEventScope.getSize-YbymL2g());
        if (Offset.equals-impl0(jMo523calculateMouseWheelScroll8xgXZGE, Offset.Companion.getZero-F1C5BW0())) {
            return null;
        }
        List changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((PointerInputChange) changes.get(i3)).consume();
        }
        return Offset.box-impl(jMo523calculateMouseWheelScroll8xgXZGE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x004a A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x004f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0048 -> B:18:0x004b). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:0:?
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitFirstCtrlMouseScroll(androidx.compose.ui.input.pointer.AwaitPointerEventScope r5, androidx.compose.foundation.gestures.ScrollConfig r6, kotlin.coroutines.Continuation<? super androidx.compose.ui.geometry.Offset> r7) {
        /*
            boolean r0 = r7 instanceof androidx.compose.foundation.gestures.TransformableKt.C01331
            if (r0 == 0) goto L13
            r0 = r7
            androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1 r0 = (androidx.compose.foundation.gestures.TransformableKt.C01331) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1 r0 = new androidx.compose.foundation.gestures.TransformableKt$awaitFirstCtrlMouseScroll$1
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3b
            if (r2 != r3) goto L34
            java.lang.Object r5 = r0.L$1
            androidx.compose.foundation.gestures.ScrollConfig r5 = (androidx.compose.foundation.gestures.ScrollConfig) r5
            java.lang.Object r6 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r6 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r6
            kotlin.ResultKt.throwOnFailure(r7)
            r4 = r6
            r6 = r5
            r5 = r4
            goto L4b
        L34:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r5)
            r5 = 0
            return r5
        L3b:
            kotlin.ResultKt.throwOnFailure(r7)
        L3e:
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r7 = awaitCtrlMouseScrollOrNull(r5, r6, r0)
            if (r7 != r1) goto L4b
            return r1
        L4b:
            androidx.compose.ui.geometry.Offset r7 = (androidx.compose.ui.geometry.Offset) r7
            if (r7 == 0) goto L3e
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt.awaitFirstCtrlMouseScroll(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.gestures.ScrollConfig, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:106:0x0161 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:107:0x015c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x0150  */
    /* JADX WARN: Code duplicated, block: B:32:0x015e A[LOOP:2: B:28:0x014e->B:32:0x015e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:8:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x0290 -> B:84:0x029e). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope r30, boolean r31, kotlinx.coroutines.channels.Channel<androidx.compose.foundation.gestures.TransformEvent> r32, kotlin.jvm.functions.Function1<? super androidx.compose.ui.geometry.Offset, java.lang.Boolean> r33, kotlin.coroutines.Continuation<? super kotlin.Unit> r34) {
        /*
            Method dump skipped, instruction units count: 753
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TransformableKt.detectZoom(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, kotlinx.coroutines.channels.Channel, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object detectZoomByCtrlMouseScroll(PointerInputScope pointerInputScope, Channel<TransformEvent> channel, ScrollConfig scrollConfig, Continuation<? super Unit> continuation) {
        Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new AnonymousClass2(continuation.getContext(), scrollConfig, channel, null), continuation);
        return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
    }

    public static final Modifier transformable(Modifier modifier, TransformableState transformableState, boolean z, boolean z2) {
        return transformable(modifier, transformableState, new Function1() { // from class: pje
            public final Object invoke(Object obj) {
                return Boolean.valueOf(TransformableKt.a((Offset) obj));
            }
        }, z, z2);
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        return transformable(modifier, transformableState, z, z2);
    }

    public static final Modifier transformable(Modifier modifier, TransformableState transformableState, Function1<? super Offset, Boolean> function1, boolean z, boolean z2) {
        return modifier.then(new TransformableElement(transformableState, function1, z, z2));
    }

    public static /* synthetic */ Modifier transformable$default(Modifier modifier, TransformableState transformableState, Function1 function1, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            z2 = true;
        }
        return transformable(modifier, transformableState, function1, z, z2);
    }
}
