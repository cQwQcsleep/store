package androidx.compose.foundation.gestures;

import androidx.collection.ScatterMapKt;
import androidx.compose.foundation.ComposeFoundationFlags;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventTimeoutCancellationException;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u008b\u0001\u0010\t\u001a\u00020\u0005*\u00020\n2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\f2/\b\u0002\u0010\u000e\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u00072\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\fH\u0086@¢\u0006\u0002\u0010\u0010\u001a\u0012\u0010\u0011\u001a\u00020\u0005*\u00020\u0012H\u0082@¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0015H\u0082@¢\u0006\u0002\u0010\u0017\u001a[\u0010\u0018\u001a\u00020\u0005*\u00020\n2/\b\u0002\u0010\u000e\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u00072\u0016\b\u0002\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0018\u00010\fH\u0080@¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u001a\u001a\u00020\u0015*\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001cH\u0087@¢\u0006\u0002\u0010\u001d\u001a&\u0010\u001a\u001a\u00020\u0015*\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010 \u001a&\u0010!\u001a\u00020\u0015*\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0080@¢\u0006\u0002\u0010 \u001a\u001e\u0010\"\u001a\u00020\u001c*\u00020#2\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010$\u001a\u00020\u001cH\u0000\u001a\u0014\u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u0012H\u0087@¢\u0006\u0002\u0010\u0013\u001a\u001e\u0010%\u001a\u0004\u0018\u00010\u0015*\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010&\u001a\u001c\u0010'\u001a\u00020(*\u00020\u00122\b\b\u0002\u0010\u001e\u001a\u00020\u001fH\u0080@¢\u0006\u0002\u0010&\u001aL\u00106\u001a\u000207*\u0002082\u0006\u00109\u001a\u0002072\b\b\u0002\u0010:\u001a\u0002022'\u0010;\u001a#\b\u0001\u0012\u0004\u0012\u000208\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060<¢\u0006\u0002\b\u0007H\u0002¢\u0006\u0002\u0010=\"7\u0010\u0000\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0001¢\u0006\u0002\b\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\b\"*\u0010*\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001c8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100\"\u001a\u00101\u001a\u0002028BX\u0082\u0004¢\u0006\f\u0012\u0004\b3\u0010,\u001a\u0004\b4\u00105¨\u0006>"}, d2 = {"NoPressGesture", "Lkotlin/Function3;", "Landroidx/compose/foundation/gestures/PressGestureScope;", "Landroidx/compose/ui/geometry/Offset;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "detectTapGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "onDoubleTap", "Lkotlin/Function1;", "onLongPress", "onPress", "onTap", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "consumeUntilUp", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitSecondDown", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "firstUp", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerInputChange;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectTapAndPress", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitFirstDown", "requireUnconsumed", "", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;ZLandroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitPrimaryFirstDown", "isChangedToDown", "Landroidx/compose/ui/input/pointer/PointerEvent;", "onlyPrimaryMouseButton", "waitForUpOrCancellation", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/ui/input/pointer/PointerEventPass;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitForLongPress", "Landroidx/compose/foundation/gestures/LongPressResult;", "value", "DetectTapGesturesEnableNewDispatchingBehavior", "getDetectTapGesturesEnableNewDispatchingBehavior$annotations", "()V", "getDetectTapGesturesEnableNewDispatchingBehavior", "()Z", "setDetectTapGesturesEnableNewDispatchingBehavior", "(Z)V", "coroutineStartForCurrentDispatchBehavior", "Lkotlinx/coroutines/CoroutineStart;", "getCoroutineStartForCurrentDispatchBehavior$annotations", "getCoroutineStartForCurrentDispatchBehavior", "()Lkotlinx/coroutines/CoroutineStart;", "launchAwaitingReset", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "resetJob", "start", "block", "Lkotlin/Function2;", "(Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/Job;Lkotlinx/coroutines/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Job;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TapGestureDetectorKt {
    private static final Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> NoPressGesture = new TapGestureDetectorKt$NoPressGesture$1(null);

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 0}, l = {291}, m = "awaitFirstDown", n = {"$this$awaitFirstDown", "pass", "requireUnconsumed"}, s = {"L$0", "L$1", "Z$0"}, v = 1)
    public static final class AnonymousClass2 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.awaitFirstDown(null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 0}, l = {304}, m = "awaitPrimaryFirstDown", n = {"$this$awaitPrimaryFirstDown", "pass", "requireUnconsumed"}, s = {"L$0", "L$1", "Z$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.awaitPrimaryFirstDown(null, false, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitSecondDown$2", f = "TapGestureDetector.kt", i = {0, 0}, l = {227}, m = "invokeSuspend", n = {"$this$withTimeoutOrNull", "minUptime"}, s = {"L$0", "J$0"}, v = 1)
    public static final class C01252 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super PointerInputChange>, Object> {
        final /* synthetic */ PointerInputChange $firstUp;
        long J$0;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01252(PointerInputChange pointerInputChange, Continuation<? super C01252> continuation) {
            super(2, continuation);
            this.$firstUp = pointerInputChange;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01252 c01252 = new C01252(this.$firstUp, continuation);
            c01252.L$0 = obj;
            return c01252;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super PointerInputChange> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0046 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:14:0x0051 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:15:0x0052  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0044 -> B:12:0x0047). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:15:0x0052
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 1
                if (r1 == 0) goto L1e
                if (r1 != r2) goto L17
                long r3 = r11.J$0
                java.lang.Object r1 = r11.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                kotlin.ResultKt.throwOnFailure(r12)
                r8 = r11
                r5 = r1
                goto L47
            L17:
                java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                k2d.a(r11)
                r11 = 0
                return r11
            L1e:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                androidx.compose.ui.input.pointer.AwaitPointerEventScope r12 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r12
                androidx.compose.ui.input.pointer.PointerInputChange r1 = r11.$firstUp
                long r3 = r1.getUptimeMillis()
                androidx.compose.ui.platform.ViewConfiguration r1 = r12.getViewConfiguration()
                long r5 = r1.getDoubleTapMinTimeMillis()
                long r3 = r3 + r5
                r5 = r12
            L35:
                r11.L$0 = r5
                r11.J$0 = r3
                r11.label = r2
                r6 = 0
                r7 = 0
                r9 = 3
                r10 = 0
                r8 = r11
                java.lang.Object r12 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown$default(r5, r6, r7, r8, r9, r10)
                if (r12 != r0) goto L47
                return r0
            L47:
                androidx.compose.ui.input.pointer.PointerInputChange r12 = (androidx.compose.ui.input.pointer.PointerInputChange) r12
                long r6 = r12.getUptimeMillis()
                int r11 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
                if (r11 < 0) goto L52
                return r12
            L52:
                r11 = r8
                goto L35
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C01252.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0}, l = {209}, m = "consumeUntilUp", n = {"$this$consumeUntilUp"}, s = {"L$0"}, v = 1)
    public static final class C01261 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01261(Continuation<? super C01261> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.consumeUntilUp(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2", f = "TapGestureDetector.kt", i = {}, l = {247}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01272 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
        final /* synthetic */ Function1<Offset, Unit> $onTap;
        final /* synthetic */ PressGestureScopeImpl $pressScope;
        final /* synthetic */ PointerInputScope $this_detectTapAndPress;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1", f = "TapGestureDetector.kt", i = {0, 0, 1}, l = {251, 257}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "resetJob", "resetJob"}, s = {"L$0", "L$1", "L$0"}, v = 1)
        public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
            final /* synthetic */ Function1<Offset, Unit> $onTap;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            private /* synthetic */ Object L$0;
            Object L$1;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$1", f = "TapGestureDetector.kt", i = {}, l = {254}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class C00171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PointerInputChange $down;
                final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00171(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super C00171> continuation) {
                    super(2, continuation);
                    this.$onPress = function3;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$down = pointerInputChange;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00171(this.$onPress, this.$pressScope, this.$down, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        Offset offset = Offset.box-impl(this.$down.getPosition-F1C5BW0());
                        this.label = 1;
                        if (function3.invoke(pressGestureScopeImpl, offset, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$2", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class C00182 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00182(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super C00182> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00182(this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$pressScope.cancel();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2$1$3", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass3> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass3(this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$pressScope.release();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(CoroutineScope coroutineScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.$onPress = function3;
                this.$onTap = function1;
                this.$pressScope = pressGestureScopeImpl;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$onPress, this.$onTap, this.$pressScope, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:23:0x0090  */
            /* JADX WARN: Code duplicated, block: B:24:0x00a0  */
            /* JADX WARN: Code duplicated, block: B:26:0x00b7  */
            public final Object invokeSuspend(Object obj) {
                AwaitPointerEventScope awaitPointerEventScope;
                Object objAwaitFirstDown$default;
                Job job;
                Object objWaitForUpOrCancellation$default;
                Job job2;
                PointerInputChange pointerInputChange;
                Function1<Offset, Unit> function1;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    Job jobLaunch$default = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new TapGestureDetectorKt$detectTapAndPress$2$1$resetJob$1(this.$pressScope, null), 1, (Object) null);
                    this.L$0 = awaitPointerEventScope;
                    this.L$1 = jobLaunch$default;
                    this.label = 1;
                    objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope, false, null, this, 3, null);
                    if (objAwaitFirstDown$default != coroutine_suspended) {
                        job = jobLaunch$default;
                    }
                    return coroutine_suspended;
                }
                if (i == 1) {
                    Job job3 = (Job) this.L$1;
                    AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job = job3;
                    awaitPointerEventScope = awaitPointerEventScope2;
                    objAwaitFirstDown$default = obj;
                } else {
                    if (i != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    Job job4 = (Job) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    job2 = job4;
                    objWaitForUpOrCancellation$default = obj;
                }
                pointerInputChange = (PointerInputChange) objWaitForUpOrCancellation$default;
                if (pointerInputChange == null) {
                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new C00182(this.$pressScope, null), 2, null);
                } else {
                    pointerInputChange.consume();
                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                    function1 = this.$onTap;
                    if (function1 != null) {
                        function1.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                    }
                }
                return Unit.INSTANCE;
                PointerInputChange pointerInputChange2 = (PointerInputChange) objAwaitFirstDown$default;
                pointerInputChange2.consume();
                if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job, null, new C00171(this.$onPress, this.$pressScope, pointerInputChange2, null), 2, null);
                }
                this.L$0 = job;
                this.L$1 = null;
                this.label = 2;
                objWaitForUpOrCancellation$default = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope, null, this, 1, null);
                if (objWaitForUpOrCancellation$default != coroutine_suspended) {
                    job2 = job;
                    pointerInputChange = (PointerInputChange) objWaitForUpOrCancellation$default;
                    if (pointerInputChange == null) {
                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new C00182(this.$pressScope, null), 2, null);
                    } else {
                        pointerInputChange.consume();
                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                        function1 = this.$onTap;
                        if (function1 != null) {
                            function1.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                        }
                    }
                    return Unit.INSTANCE;
                }
                return coroutine_suspended;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01272(PointerInputScope pointerInputScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super C01272> continuation) {
            super(2, continuation);
            this.$this_detectTapAndPress = pointerInputScope;
            this.$onPress = function3;
            this.$onTap = function1;
            this.$pressScope = pressGestureScopeImpl;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01272 c01272 = new C01272(this.$this_detectTapAndPress, this.$onPress, this.$onTap, this.$pressScope, continuation);
            c01272.L$0 = obj;
            return c01272;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                PointerInputScope pointerInputScope = this.$this_detectTapAndPress;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(coroutineScope, this.$onPress, this.$onTap, this.$pressScope, null);
                this.label = 1;
                if (ForEachGestureKt.awaitEachGesture(pointerInputScope, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2", f = "TapGestureDetector.kt", i = {}, l = {104}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    public static final class C01282 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Offset, Unit> $onDoubleTap;
        final /* synthetic */ Function1<Offset, Unit> $onLongPress;
        final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
        final /* synthetic */ Function1<Offset, Unit> $onTap;
        final /* synthetic */ PointerInputScope $this_detectTapGestures;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1", f = "TapGestureDetector.kt", i = {0, 1, 1, 2, 2, 2, 3, 4, 4, 4, 5, 5, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.DefaultScatterCapacity, ScatterMapKt.ClonedMetadataCount}, l = {105, 116, 119, 122, 149, 167, 169, 180}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "resetJob", "$this$awaitEachGesture", "down", "resetJob", "resetJob", "$this$awaitEachGesture", "upOrCancel", "cancelOrReleaseJob", "resetJob", "upOrCancel", "$this$awaitEachGesture", "resetJob", "upOrCancel", "secondDown", "resetJob"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0"}, v = 1)
        public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            final /* synthetic */ Function1<Offset, Unit> $onDoubleTap;
            final /* synthetic */ Function1<Offset, Unit> $onLongPress;
            final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
            final /* synthetic */ Function1<Offset, Unit> $onTap;
            final /* synthetic */ PressGestureScopeImpl $pressScope;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            int label;

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$1", f = "TapGestureDetector.kt", i = {}, l = {110}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class C00191 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PointerInputChange $down;
                final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00191(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super C00191> continuation) {
                    super(2, continuation);
                    this.$onPress = function3;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$down = pointerInputChange;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00191(this.$onPress, this.$pressScope, this.$down, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        Offset offset = Offset.box-impl(this.$down.getPosition-F1C5BW0());
                        this.label = 1;
                        if (function3.invoke(pressGestureScopeImpl, offset, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$2", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class C00202 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00202(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super C00202> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C00202(this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$pressScope.release();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$3", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass3(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass3> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass3(this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$pressScope.cancel();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$4", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass4(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass4> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass4(this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$pressScope.release();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$5", f = "TapGestureDetector.kt", i = {}, l = {157, 158}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Job $cancelOrReleaseJob;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(Job job, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass5> continuation) {
                    super(2, continuation);
                    this.$cancelOrReleaseJob = job;
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass5(this.$cancelOrReleaseJob, this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:14:0x0033, code lost:
                
                    if (r5.reset(r4) == r0) goto L15;
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Job job = this.$cancelOrReleaseJob;
                        this.label = 1;
                        if (job.join(this) != coroutine_suspended) {
                        }
                        return coroutine_suspended;
                    }
                    if (i == 1) {
                        ResultKt.throwOnFailure(obj);
                    } else {
                        if (i != 2) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                    PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                    this.label = 2;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$6", f = "TapGestureDetector.kt", i = {}, l = {161}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> $onPress;
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                final /* synthetic */ PointerInputChange $secondDown;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass6(Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, PressGestureScopeImpl pressGestureScopeImpl, PointerInputChange pointerInputChange, Continuation<? super AnonymousClass6> continuation) {
                    super(2, continuation);
                    this.$onPress = function3;
                    this.$pressScope = pressGestureScopeImpl;
                    this.$secondDown = pointerInputChange;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass6(this.$onPress, this.$pressScope, this.$secondDown, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> function3 = this.$onPress;
                        PressGestureScopeImpl pressGestureScopeImpl = this.$pressScope;
                        Offset offset = Offset.box-impl(this.$secondDown.getPosition-F1C5BW0());
                        this.label = 1;
                        if (function3.invoke(pressGestureScopeImpl, offset, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        if (i != 1) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        ResultKt.throwOnFailure(obj);
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$7", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass7 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass7(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass7> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass7(this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$pressScope.release();
                    return Unit.INSTANCE;
                }
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8, reason: invalid class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapGestures$2$1$8", f = "TapGestureDetector.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
            public static final class AnonymousClass8 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PressGestureScopeImpl $pressScope;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass8(PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass8> continuation) {
                    super(2, continuation);
                    this.$pressScope = pressGestureScopeImpl;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass8(this.$pressScope, continuation);
                }

                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                public final Object invokeSuspend(Object obj) {
                    IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    if (this.label != 0) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                    this.$pressScope.cancel();
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass1(CoroutineScope coroutineScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function2, Function1<? super Offset, Unit> function4, PressGestureScopeImpl pressGestureScopeImpl, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.$onPress = function3;
                this.$onLongPress = function1;
                this.$onDoubleTap = function2;
                this.$onTap = function4;
                this.$pressScope = pressGestureScopeImpl;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, this.$pressScope, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code duplicated, block: B:21:0x00c5  */
            /* JADX WARN: Code duplicated, block: B:24:0x00e1  */
            /* JADX WARN: Code duplicated, block: B:29:0x00f4  */
            /* JADX WARN: Code duplicated, block: B:32:0x0105 A[PHI: r0 r1 r2 r4
              0x0105: PHI (r0v20 kotlinx.coroutines.Job) = (r0v6 kotlinx.coroutines.Job), (r0v22 kotlinx.coroutines.Job) binds: [B:30:0x0101, B:12:0x0060] A[DONT_GENERATE, DONT_INLINE]
              0x0105: PHI (r1v15 androidx.compose.ui.input.pointer.PointerInputChange) = 
              (r1v3 androidx.compose.ui.input.pointer.PointerInputChange)
              (r1v24 androidx.compose.ui.input.pointer.PointerInputChange)
             binds: [B:30:0x0101, B:12:0x0060] A[DONT_GENERATE, DONT_INLINE]
              0x0105: PHI (r2v5 androidx.compose.ui.input.pointer.AwaitPointerEventScope) = 
              (r2v1 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
              (r2v7 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
             binds: [B:30:0x0101, B:12:0x0060] A[DONT_GENERATE, DONT_INLINE]
              0x0105: PHI (r4v9 java.lang.Object) = (r4v4 java.lang.Object), (r4v13 java.lang.Object) binds: [B:30:0x0101, B:12:0x0060] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:34:0x010f  */
            /* JADX WARN: Code duplicated, block: B:39:0x013f  */
            /* JADX WARN: Code duplicated, block: B:41:0x0143  */
            /* JADX WARN: Code duplicated, block: B:42:0x014a  */
            /* JADX WARN: Code duplicated, block: B:44:0x014e  */
            /* JADX WARN: Code duplicated, block: B:46:0x0152  */
            /* JADX WARN: Code duplicated, block: B:47:0x0163  */
            /* JADX WARN: Code duplicated, block: B:49:0x0178  */
            /* JADX WARN: Code duplicated, block: B:51:0x017c  */
            /* JADX WARN: Code duplicated, block: B:53:0x0180  */
            /* JADX WARN: Code duplicated, block: B:54:0x018d  */
            /* JADX WARN: Code duplicated, block: B:57:0x019e A[PHI: r0 r1 r2 r4
              0x019e: PHI (r0v28 kotlinx.coroutines.Job) = (r0v16 kotlinx.coroutines.Job), (r0v36 kotlinx.coroutines.Job) binds: [B:55:0x019a, B:9:0x0043] A[DONT_GENERATE, DONT_INLINE]
              0x019e: PHI (r1v25 androidx.compose.ui.input.pointer.PointerInputChange) = 
              (r1v12 androidx.compose.ui.input.pointer.PointerInputChange)
              (r1v30 androidx.compose.ui.input.pointer.PointerInputChange)
             binds: [B:55:0x019a, B:9:0x0043] A[DONT_GENERATE, DONT_INLINE]
              0x019e: PHI (r2v8 androidx.compose.ui.input.pointer.AwaitPointerEventScope) = 
              (r2v4 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
              (r2v12 androidx.compose.ui.input.pointer.AwaitPointerEventScope)
             binds: [B:55:0x019a, B:9:0x0043] A[DONT_GENERATE, DONT_INLINE]
              0x019e: PHI (r4v14 java.lang.Object) = (r4v8 java.lang.Object), (r4v18 java.lang.Object) binds: [B:55:0x019a, B:9:0x0043] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Code duplicated, block: B:59:0x01a2  */
            /* JADX WARN: Code duplicated, block: B:61:0x01a6  */
            /* JADX WARN: Code duplicated, block: B:62:0x01b3  */
            /* JADX WARN: Code duplicated, block: B:64:0x01cf  */
            /* JADX WARN: Code duplicated, block: B:67:0x01eb  */
            /* JADX WARN: Code duplicated, block: B:70:0x01fb  */
            /* JADX WARN: Code duplicated, block: B:72:0x0205  */
            /* JADX WARN: Code duplicated, block: B:75:0x0217  */
            /* JADX WARN: Code duplicated, block: B:78:0x0227  */
            /* JADX WARN: Code duplicated, block: B:81:0x0247  */
            /* JADX WARN: Code duplicated, block: B:84:0x025a  */
            /* JADX WARN: Code duplicated, block: B:86:0x025e  */
            /* JADX WARN: Code duplicated, block: B:87:0x0268  */
            /* JADX WARN: Code duplicated, block: B:89:0x026c  */
            /* JADX WARN: Code duplicated, block: B:91:0x0271  */
            /* JADX WARN: Code duplicated, block: B:92:0x0291  */
            /* JADX WARN: Code duplicated, block: B:94:0x02a4  */
            /* JADX WARN: Code duplicated, block: B:95:0x02b0  */
            /* JADX WARN: Code duplicated, block: B:99:0x02b7  */
            /* JADX WARN: Code restructure failed: missing block: B:25:0x00ec, code lost:
            
                if (r1 == r6) goto L80;
             */
            /* JADX WARN: Code restructure failed: missing block: B:35:0x0129, code lost:
            
                if (androidx.compose.foundation.gestures.TapGestureDetectorKt.consumeUntilUp(r2, r22) == r6) goto L80;
             */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) {
                AwaitPointerEventScope awaitPointerEventScope;
                Object objAwaitFirstDown$default;
                AwaitPointerEventScope awaitPointerEventScope2;
                PointerInputChange pointerInputChange;
                Job jobLaunch$default;
                Job job;
                Object objWaitForLongPress$default;
                Object objWaitForUpOrCancellation$default;
                PointerInputChange finalUpChange;
                Job job2;
                Job jobLaunchAwaitingReset$default;
                Object objAwaitSecondDown;
                Function1<Offset, Unit> function1;
                LongPressResult longPressResult;
                PointerInputChange pointerInputChange2;
                Job jobLaunch$default2;
                Object objWaitForLongPress$default2;
                Job job3;
                PointerInputChange pointerInputChange3;
                AwaitPointerEventScope awaitPointerEventScope3;
                Object objWaitForUpOrCancellation$default2;
                Job job4;
                PointerInputChange pointerInputChange4;
                Function1<Offset, Unit> function2;
                PointerInputChange finalUpChange2;
                Job job5;
                Function1<Offset, Unit> function3;
                LongPressResult longPressResult2;
                Job job6;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        this.L$0 = awaitPointerEventScope;
                        this.label = 1;
                        objAwaitFirstDown$default = TapGestureDetectorKt.awaitFirstDown$default(awaitPointerEventScope, false, null, this, 3, null);
                        if (objAwaitFirstDown$default != coroutine_suspended) {
                            awaitPointerEventScope2 = awaitPointerEventScope;
                            pointerInputChange = (PointerInputChange) objAwaitFirstDown$default;
                            pointerInputChange.consume();
                            jobLaunch$default = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new TapGestureDetectorKt$detectTapGestures$2$1$resetJob$1(this.$pressScope, null), 1, (Object) null);
                            if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, jobLaunch$default, null, new C00191(this.$onPress, this.$pressScope, pointerInputChange, null), 2, null);
                            }
                            job = jobLaunch$default;
                            if (this.$onLongPress != null) {
                                this.L$0 = awaitPointerEventScope2;
                                this.L$1 = job;
                                this.label = 2;
                                objWaitForUpOrCancellation$default = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope2, null, this, 1, null);
                            } else {
                                this.L$0 = awaitPointerEventScope2;
                                this.L$1 = pointerInputChange;
                                this.L$2 = job;
                                this.label = 3;
                                objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope2, null, this, 1, null);
                                if (objWaitForLongPress$default != coroutine_suspended) {
                                    longPressResult = (LongPressResult) objWaitForLongPress$default;
                                    if (!Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                                        if (longPressResult instanceof LongPressResult.Released) {
                                            finalUpChange = ((LongPressResult.Released) longPressResult).getFinalUpChange();
                                        } else {
                                            if (!(longPressResult instanceof LongPressResult.Canceled)) {
                                                bu8.a();
                                                return null;
                                            }
                                            finalUpChange = null;
                                        }
                                        job2 = job;
                                        if (finalUpChange == null) {
                                            jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                                        } else {
                                            finalUpChange.consume();
                                            jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                                        }
                                        if (finalUpChange != null) {
                                            if (this.$onDoubleTap != null) {
                                                function1 = this.$onTap;
                                                if (function1 != null) {
                                                    function1.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                                                }
                                            } else {
                                                this.L$0 = awaitPointerEventScope2;
                                                this.L$1 = finalUpChange;
                                                this.L$2 = jobLaunchAwaitingReset$default;
                                                this.label = 5;
                                                objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown(awaitPointerEventScope2, finalUpChange, this);
                                                if (objAwaitSecondDown != coroutine_suspended) {
                                                    pointerInputChange2 = (PointerInputChange) objAwaitSecondDown;
                                                    if (pointerInputChange2 != null) {
                                                        function2 = this.$onTap;
                                                        if (function2 != null) {
                                                            function2.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                                                        }
                                                    } else {
                                                        jobLaunch$default2 = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(jobLaunchAwaitingReset$default, this.$pressScope, null), 1, (Object) null);
                                                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, jobLaunch$default2, null, new AnonymousClass6(this.$onPress, this.$pressScope, pointerInputChange2, null), 2, null);
                                                        }
                                                        if (this.$onLongPress == null) {
                                                            this.L$0 = jobLaunch$default2;
                                                            this.L$1 = finalUpChange;
                                                            this.L$2 = null;
                                                            this.label = 6;
                                                            objWaitForUpOrCancellation$default2 = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope2, null, this, 1, null);
                                                            if (objWaitForUpOrCancellation$default2 != coroutine_suspended) {
                                                                PointerInputChange pointerInputChange5 = finalUpChange;
                                                                job4 = jobLaunch$default2;
                                                                pointerInputChange4 = pointerInputChange5;
                                                                finalUpChange2 = (PointerInputChange) objWaitForUpOrCancellation$default2;
                                                                job5 = job4;
                                                                if (finalUpChange2 != null) {
                                                                    finalUpChange2.consume();
                                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                                    this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                                } else {
                                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                                    function3 = this.$onTap;
                                                                    if (function3 != null) {
                                                                        function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                                    }
                                                                }
                                                            }
                                                        } else {
                                                            this.L$0 = awaitPointerEventScope2;
                                                            this.L$1 = jobLaunch$default2;
                                                            this.L$2 = finalUpChange;
                                                            this.L$3 = pointerInputChange2;
                                                            this.label = 7;
                                                            objWaitForLongPress$default2 = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope2, null, this, 1, null);
                                                            if (objWaitForLongPress$default2 != coroutine_suspended) {
                                                                AwaitPointerEventScope awaitPointerEventScope4 = awaitPointerEventScope2;
                                                                job3 = jobLaunch$default2;
                                                                pointerInputChange3 = pointerInputChange2;
                                                                awaitPointerEventScope3 = awaitPointerEventScope4;
                                                                longPressResult2 = (LongPressResult) objWaitForLongPress$default2;
                                                                if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                                                    this.$onLongPress.invoke(Offset.box-impl(pointerInputChange3.getPosition-F1C5BW0()));
                                                                    this.L$0 = job3;
                                                                    this.L$1 = null;
                                                                    this.L$2 = null;
                                                                    this.L$3 = null;
                                                                    this.label = 8;
                                                                    if (TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope3, this) != coroutine_suspended) {
                                                                        job6 = job3;
                                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job6, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                                                        return Unit.INSTANCE;
                                                                    }
                                                                } else {
                                                                    if (longPressResult2 instanceof LongPressResult.Released) {
                                                                        job5 = job3;
                                                                        finalUpChange2 = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                                                        pointerInputChange4 = finalUpChange;
                                                                    } else {
                                                                        if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                                            bu8.a();
                                                                            return null;
                                                                        }
                                                                        pointerInputChange4 = finalUpChange;
                                                                        job5 = job3;
                                                                        finalUpChange2 = null;
                                                                    }
                                                                    if (finalUpChange2 != null) {
                                                                        finalUpChange2.consume();
                                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                                        this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                                    } else {
                                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                                        function3 = this.$onTap;
                                                                        if (function3 != null) {
                                                                            function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        return Unit.INSTANCE;
                                    }
                                    this.$onLongPress.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                                    this.L$0 = job;
                                    this.L$1 = null;
                                    this.L$2 = null;
                                    this.label = 4;
                                }
                            }
                            break;
                        }
                        return coroutine_suspended;
                    case 1:
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objAwaitFirstDown$default = obj;
                        awaitPointerEventScope2 = awaitPointerEventScope;
                        pointerInputChange = (PointerInputChange) objAwaitFirstDown$default;
                        pointerInputChange.consume();
                        jobLaunch$default = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new TapGestureDetectorKt$detectTapGestures$2$1$resetJob$1(this.$pressScope, null), 1, (Object) null);
                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, jobLaunch$default, null, new C00191(this.$onPress, this.$pressScope, pointerInputChange, null), 2, null);
                        }
                        job = jobLaunch$default;
                        if (this.$onLongPress != null) {
                            this.L$0 = awaitPointerEventScope2;
                            this.L$1 = pointerInputChange;
                            this.L$2 = job;
                            this.label = 3;
                            objWaitForLongPress$default = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope2, null, this, 1, null);
                            if (objWaitForLongPress$default != coroutine_suspended) {
                                longPressResult = (LongPressResult) objWaitForLongPress$default;
                                if (!Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                                    if (longPressResult instanceof LongPressResult.Released) {
                                        finalUpChange = ((LongPressResult.Released) longPressResult).getFinalUpChange();
                                    } else {
                                        if (!(longPressResult instanceof LongPressResult.Canceled)) {
                                            bu8.a();
                                            return null;
                                        }
                                        finalUpChange = null;
                                    }
                                    job2 = job;
                                    if (finalUpChange == null) {
                                        jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                                    } else {
                                        finalUpChange.consume();
                                        jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                                    }
                                    if (finalUpChange != null) {
                                        if (this.$onDoubleTap != null) {
                                            function1 = this.$onTap;
                                            if (function1 != null) {
                                                function1.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                                            }
                                        } else {
                                            this.L$0 = awaitPointerEventScope2;
                                            this.L$1 = finalUpChange;
                                            this.L$2 = jobLaunchAwaitingReset$default;
                                            this.label = 5;
                                            objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown(awaitPointerEventScope2, finalUpChange, this);
                                            if (objAwaitSecondDown != coroutine_suspended) {
                                                pointerInputChange2 = (PointerInputChange) objAwaitSecondDown;
                                                if (pointerInputChange2 != null) {
                                                    function2 = this.$onTap;
                                                    if (function2 != null) {
                                                        function2.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                                                    }
                                                } else {
                                                    jobLaunch$default2 = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(jobLaunchAwaitingReset$default, this.$pressScope, null), 1, (Object) null);
                                                    if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, jobLaunch$default2, null, new AnonymousClass6(this.$onPress, this.$pressScope, pointerInputChange2, null), 2, null);
                                                    }
                                                    if (this.$onLongPress == null) {
                                                        this.L$0 = jobLaunch$default2;
                                                        this.L$1 = finalUpChange;
                                                        this.L$2 = null;
                                                        this.label = 6;
                                                        objWaitForUpOrCancellation$default2 = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope2, null, this, 1, null);
                                                        if (objWaitForUpOrCancellation$default2 != coroutine_suspended) {
                                                            PointerInputChange pointerInputChange6 = finalUpChange;
                                                            job4 = jobLaunch$default2;
                                                            pointerInputChange4 = pointerInputChange6;
                                                            finalUpChange2 = (PointerInputChange) objWaitForUpOrCancellation$default2;
                                                            job5 = job4;
                                                            if (finalUpChange2 != null) {
                                                                finalUpChange2.consume();
                                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                                this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                            } else {
                                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                                function3 = this.$onTap;
                                                                if (function3 != null) {
                                                                    function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        this.L$0 = awaitPointerEventScope2;
                                                        this.L$1 = jobLaunch$default2;
                                                        this.L$2 = finalUpChange;
                                                        this.L$3 = pointerInputChange2;
                                                        this.label = 7;
                                                        objWaitForLongPress$default2 = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope2, null, this, 1, null);
                                                        if (objWaitForLongPress$default2 != coroutine_suspended) {
                                                            AwaitPointerEventScope awaitPointerEventScope5 = awaitPointerEventScope2;
                                                            job3 = jobLaunch$default2;
                                                            pointerInputChange3 = pointerInputChange2;
                                                            awaitPointerEventScope3 = awaitPointerEventScope5;
                                                            longPressResult2 = (LongPressResult) objWaitForLongPress$default2;
                                                            if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                                                this.$onLongPress.invoke(Offset.box-impl(pointerInputChange3.getPosition-F1C5BW0()));
                                                                this.L$0 = job3;
                                                                this.L$1 = null;
                                                                this.L$2 = null;
                                                                this.L$3 = null;
                                                                this.label = 8;
                                                                if (TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope3, this) != coroutine_suspended) {
                                                                    job6 = job3;
                                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job6, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                                                    return Unit.INSTANCE;
                                                                }
                                                            } else {
                                                                if (longPressResult2 instanceof LongPressResult.Released) {
                                                                    job5 = job3;
                                                                    finalUpChange2 = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                                                    pointerInputChange4 = finalUpChange;
                                                                } else {
                                                                    if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                                        bu8.a();
                                                                        return null;
                                                                    }
                                                                    pointerInputChange4 = finalUpChange;
                                                                    job5 = job3;
                                                                    finalUpChange2 = null;
                                                                }
                                                                if (finalUpChange2 != null) {
                                                                    finalUpChange2.consume();
                                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                                    this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                                } else {
                                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                                    function3 = this.$onTap;
                                                                    if (function3 != null) {
                                                                        function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    return Unit.INSTANCE;
                                }
                                this.$onLongPress.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                                this.L$0 = job;
                                this.L$1 = null;
                                this.L$2 = null;
                                this.label = 4;
                            }
                            break;
                        } else {
                            this.L$0 = awaitPointerEventScope2;
                            this.L$1 = job;
                            this.label = 2;
                            objWaitForUpOrCancellation$default = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope2, null, this, 1, null);
                            break;
                        }
                        return coroutine_suspended;
                    case 2:
                        job = (Job) this.L$1;
                        AwaitPointerEventScope awaitPointerEventScope6 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        awaitPointerEventScope2 = awaitPointerEventScope6;
                        objWaitForUpOrCancellation$default = obj;
                        finalUpChange = (PointerInputChange) objWaitForUpOrCancellation$default;
                        job2 = job;
                        if (finalUpChange == null) {
                            jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                        } else {
                            finalUpChange.consume();
                            jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                        }
                        if (finalUpChange != null) {
                            if (this.$onDoubleTap != null) {
                                this.L$0 = awaitPointerEventScope2;
                                this.L$1 = finalUpChange;
                                this.L$2 = jobLaunchAwaitingReset$default;
                                this.label = 5;
                                objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown(awaitPointerEventScope2, finalUpChange, this);
                                if (objAwaitSecondDown != coroutine_suspended) {
                                    pointerInputChange2 = (PointerInputChange) objAwaitSecondDown;
                                    if (pointerInputChange2 != null) {
                                        function2 = this.$onTap;
                                        if (function2 != null) {
                                            function2.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                                        }
                                    } else {
                                        jobLaunch$default2 = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(jobLaunchAwaitingReset$default, this.$pressScope, null), 1, (Object) null);
                                        if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, jobLaunch$default2, null, new AnonymousClass6(this.$onPress, this.$pressScope, pointerInputChange2, null), 2, null);
                                        }
                                        if (this.$onLongPress == null) {
                                            this.L$0 = jobLaunch$default2;
                                            this.L$1 = finalUpChange;
                                            this.L$2 = null;
                                            this.label = 6;
                                            objWaitForUpOrCancellation$default2 = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope2, null, this, 1, null);
                                            if (objWaitForUpOrCancellation$default2 != coroutine_suspended) {
                                                PointerInputChange pointerInputChange7 = finalUpChange;
                                                job4 = jobLaunch$default2;
                                                pointerInputChange4 = pointerInputChange7;
                                                finalUpChange2 = (PointerInputChange) objWaitForUpOrCancellation$default2;
                                                job5 = job4;
                                                if (finalUpChange2 != null) {
                                                    finalUpChange2.consume();
                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                    this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                } else {
                                                    TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                    function3 = this.$onTap;
                                                    if (function3 != null) {
                                                        function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                    }
                                                }
                                            }
                                        } else {
                                            this.L$0 = awaitPointerEventScope2;
                                            this.L$1 = jobLaunch$default2;
                                            this.L$2 = finalUpChange;
                                            this.L$3 = pointerInputChange2;
                                            this.label = 7;
                                            objWaitForLongPress$default2 = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope2, null, this, 1, null);
                                            if (objWaitForLongPress$default2 != coroutine_suspended) {
                                                AwaitPointerEventScope awaitPointerEventScope7 = awaitPointerEventScope2;
                                                job3 = jobLaunch$default2;
                                                pointerInputChange3 = pointerInputChange2;
                                                awaitPointerEventScope3 = awaitPointerEventScope7;
                                                longPressResult2 = (LongPressResult) objWaitForLongPress$default2;
                                                if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                                    this.$onLongPress.invoke(Offset.box-impl(pointerInputChange3.getPosition-F1C5BW0()));
                                                    this.L$0 = job3;
                                                    this.L$1 = null;
                                                    this.L$2 = null;
                                                    this.L$3 = null;
                                                    this.label = 8;
                                                    if (TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope3, this) != coroutine_suspended) {
                                                        job6 = job3;
                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job6, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                                        return Unit.INSTANCE;
                                                    }
                                                } else {
                                                    if (longPressResult2 instanceof LongPressResult.Released) {
                                                        job5 = job3;
                                                        finalUpChange2 = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                                        pointerInputChange4 = finalUpChange;
                                                    } else {
                                                        if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                            bu8.a();
                                                            return null;
                                                        }
                                                        pointerInputChange4 = finalUpChange;
                                                        job5 = job3;
                                                        finalUpChange2 = null;
                                                    }
                                                    if (finalUpChange2 != null) {
                                                        finalUpChange2.consume();
                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                        this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                    } else {
                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                        function3 = this.$onTap;
                                                        if (function3 != null) {
                                                            function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                return coroutine_suspended;
                            }
                            function1 = this.$onTap;
                            if (function1 != null) {
                                function1.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                            }
                        }
                        return Unit.INSTANCE;
                    case 3:
                        job = (Job) this.L$2;
                        pointerInputChange = (PointerInputChange) this.L$1;
                        awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objWaitForLongPress$default = obj;
                        longPressResult = (LongPressResult) objWaitForLongPress$default;
                        if (!Intrinsics.areEqual(longPressResult, LongPressResult.Success.INSTANCE)) {
                            if (longPressResult instanceof LongPressResult.Released) {
                                finalUpChange = ((LongPressResult.Released) longPressResult).getFinalUpChange();
                            } else {
                                if (!(longPressResult instanceof LongPressResult.Canceled)) {
                                    bu8.a();
                                    return null;
                                }
                                finalUpChange = null;
                            }
                            job2 = job;
                            if (finalUpChange == null) {
                                jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass3(this.$pressScope, null), 2, null);
                            } else {
                                finalUpChange.consume();
                                jobLaunchAwaitingReset$default = TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job2, null, new AnonymousClass4(this.$pressScope, null), 2, null);
                            }
                            if (finalUpChange != null) {
                                if (this.$onDoubleTap != null) {
                                    function1 = this.$onTap;
                                    if (function1 != null) {
                                        function1.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                                    }
                                } else {
                                    this.L$0 = awaitPointerEventScope2;
                                    this.L$1 = finalUpChange;
                                    this.L$2 = jobLaunchAwaitingReset$default;
                                    this.label = 5;
                                    objAwaitSecondDown = TapGestureDetectorKt.awaitSecondDown(awaitPointerEventScope2, finalUpChange, this);
                                    if (objAwaitSecondDown != coroutine_suspended) {
                                        pointerInputChange2 = (PointerInputChange) objAwaitSecondDown;
                                        if (pointerInputChange2 != null) {
                                            function2 = this.$onTap;
                                            if (function2 != null) {
                                                function2.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                                            }
                                        } else {
                                            jobLaunch$default2 = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(jobLaunchAwaitingReset$default, this.$pressScope, null), 1, (Object) null);
                                            if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, jobLaunch$default2, null, new AnonymousClass6(this.$onPress, this.$pressScope, pointerInputChange2, null), 2, null);
                                            }
                                            if (this.$onLongPress == null) {
                                                this.L$0 = jobLaunch$default2;
                                                this.L$1 = finalUpChange;
                                                this.L$2 = null;
                                                this.label = 6;
                                                objWaitForUpOrCancellation$default2 = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope2, null, this, 1, null);
                                                if (objWaitForUpOrCancellation$default2 != coroutine_suspended) {
                                                    PointerInputChange pointerInputChange8 = finalUpChange;
                                                    job4 = jobLaunch$default2;
                                                    pointerInputChange4 = pointerInputChange8;
                                                    finalUpChange2 = (PointerInputChange) objWaitForUpOrCancellation$default2;
                                                    job5 = job4;
                                                    if (finalUpChange2 != null) {
                                                        finalUpChange2.consume();
                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                        this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                    } else {
                                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                        function3 = this.$onTap;
                                                        if (function3 != null) {
                                                            function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                        }
                                                    }
                                                }
                                            } else {
                                                this.L$0 = awaitPointerEventScope2;
                                                this.L$1 = jobLaunch$default2;
                                                this.L$2 = finalUpChange;
                                                this.L$3 = pointerInputChange2;
                                                this.label = 7;
                                                objWaitForLongPress$default2 = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope2, null, this, 1, null);
                                                if (objWaitForLongPress$default2 != coroutine_suspended) {
                                                    AwaitPointerEventScope awaitPointerEventScope8 = awaitPointerEventScope2;
                                                    job3 = jobLaunch$default2;
                                                    pointerInputChange3 = pointerInputChange2;
                                                    awaitPointerEventScope3 = awaitPointerEventScope8;
                                                    longPressResult2 = (LongPressResult) objWaitForLongPress$default2;
                                                    if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                                        this.$onLongPress.invoke(Offset.box-impl(pointerInputChange3.getPosition-F1C5BW0()));
                                                        this.L$0 = job3;
                                                        this.L$1 = null;
                                                        this.L$2 = null;
                                                        this.L$3 = null;
                                                        this.label = 8;
                                                        if (TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope3, this) != coroutine_suspended) {
                                                            job6 = job3;
                                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job6, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                                            return Unit.INSTANCE;
                                                        }
                                                    } else {
                                                        if (longPressResult2 instanceof LongPressResult.Released) {
                                                            job5 = job3;
                                                            finalUpChange2 = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                                            pointerInputChange4 = finalUpChange;
                                                        } else {
                                                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                                bu8.a();
                                                                return null;
                                                            }
                                                            pointerInputChange4 = finalUpChange;
                                                            job5 = job3;
                                                            finalUpChange2 = null;
                                                        }
                                                        if (finalUpChange2 != null) {
                                                            finalUpChange2.consume();
                                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                                            this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                                        } else {
                                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                                            function3 = this.$onTap;
                                                            if (function3 != null) {
                                                                function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            return Unit.INSTANCE;
                        }
                        this.$onLongPress.invoke(Offset.box-impl(pointerInputChange.getPosition-F1C5BW0()));
                        this.L$0 = job;
                        this.L$1 = null;
                        this.L$2 = null;
                        this.label = 4;
                        break;
                        return coroutine_suspended;
                    case 4:
                        job = (Job) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job, null, new C00202(this.$pressScope, null), 2, null);
                        return Unit.INSTANCE;
                    case 5:
                        jobLaunchAwaitingReset$default = (Job) this.L$2;
                        finalUpChange = (PointerInputChange) this.L$1;
                        awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objAwaitSecondDown = obj;
                        pointerInputChange2 = (PointerInputChange) objAwaitSecondDown;
                        if (pointerInputChange2 != null) {
                            jobLaunch$default2 = BuildersKt.launch$default(this.$$this$coroutineScope, (CoroutineContext) null, TapGestureDetectorKt.getCoroutineStartForCurrentDispatchBehavior(), new AnonymousClass5(jobLaunchAwaitingReset$default, this.$pressScope, null), 1, (Object) null);
                            if (this.$onPress != TapGestureDetectorKt.NoPressGesture) {
                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, jobLaunch$default2, null, new AnonymousClass6(this.$onPress, this.$pressScope, pointerInputChange2, null), 2, null);
                            }
                            if (this.$onLongPress == null) {
                                this.L$0 = jobLaunch$default2;
                                this.L$1 = finalUpChange;
                                this.L$2 = null;
                                this.label = 6;
                                objWaitForUpOrCancellation$default2 = TapGestureDetectorKt.waitForUpOrCancellation$default(awaitPointerEventScope2, null, this, 1, null);
                                if (objWaitForUpOrCancellation$default2 != coroutine_suspended) {
                                    PointerInputChange pointerInputChange9 = finalUpChange;
                                    job4 = jobLaunch$default2;
                                    pointerInputChange4 = pointerInputChange9;
                                    finalUpChange2 = (PointerInputChange) objWaitForUpOrCancellation$default2;
                                    job5 = job4;
                                    if (finalUpChange2 != null) {
                                        finalUpChange2.consume();
                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                        this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                    } else {
                                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                        function3 = this.$onTap;
                                        if (function3 != null) {
                                            function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                        }
                                    }
                                }
                            } else {
                                this.L$0 = awaitPointerEventScope2;
                                this.L$1 = jobLaunch$default2;
                                this.L$2 = finalUpChange;
                                this.L$3 = pointerInputChange2;
                                this.label = 7;
                                objWaitForLongPress$default2 = TapGestureDetectorKt.waitForLongPress$default(awaitPointerEventScope2, null, this, 1, null);
                                if (objWaitForLongPress$default2 != coroutine_suspended) {
                                    AwaitPointerEventScope awaitPointerEventScope9 = awaitPointerEventScope2;
                                    job3 = jobLaunch$default2;
                                    pointerInputChange3 = pointerInputChange2;
                                    awaitPointerEventScope3 = awaitPointerEventScope9;
                                    longPressResult2 = (LongPressResult) objWaitForLongPress$default2;
                                    if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                                        this.$onLongPress.invoke(Offset.box-impl(pointerInputChange3.getPosition-F1C5BW0()));
                                        this.L$0 = job3;
                                        this.L$1 = null;
                                        this.L$2 = null;
                                        this.L$3 = null;
                                        this.label = 8;
                                        if (TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope3, this) != coroutine_suspended) {
                                            job6 = job3;
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job6, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                            return Unit.INSTANCE;
                                        }
                                    } else {
                                        if (longPressResult2 instanceof LongPressResult.Released) {
                                            job5 = job3;
                                            finalUpChange2 = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                                            pointerInputChange4 = finalUpChange;
                                        } else {
                                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                                bu8.a();
                                                return null;
                                            }
                                            pointerInputChange4 = finalUpChange;
                                            job5 = job3;
                                            finalUpChange2 = null;
                                        }
                                        if (finalUpChange2 != null) {
                                            finalUpChange2.consume();
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                                            this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                                        } else {
                                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                                            function3 = this.$onTap;
                                            if (function3 != null) {
                                                function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                                            }
                                        }
                                    }
                                }
                            }
                            return coroutine_suspended;
                        }
                        function2 = this.$onTap;
                        if (function2 != null) {
                            function2.invoke(Offset.box-impl(finalUpChange.getPosition-F1C5BW0()));
                        }
                        return Unit.INSTANCE;
                    case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                        pointerInputChange4 = (PointerInputChange) this.L$1;
                        job4 = (Job) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objWaitForUpOrCancellation$default2 = obj;
                        finalUpChange2 = (PointerInputChange) objWaitForUpOrCancellation$default2;
                        job5 = job4;
                        if (finalUpChange2 != null) {
                            finalUpChange2.consume();
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                            this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                        } else {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                            function3 = this.$onTap;
                            if (function3 != null) {
                                function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                            }
                        }
                        return Unit.INSTANCE;
                    case ScatterMapKt.ClonedMetadataCount /* 7 */:
                        pointerInputChange3 = (PointerInputChange) this.L$3;
                        finalUpChange = (PointerInputChange) this.L$2;
                        job3 = (Job) this.L$1;
                        awaitPointerEventScope3 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objWaitForLongPress$default2 = obj;
                        longPressResult2 = (LongPressResult) objWaitForLongPress$default2;
                        if (Intrinsics.areEqual(longPressResult2, LongPressResult.Success.INSTANCE)) {
                            this.$onLongPress.invoke(Offset.box-impl(pointerInputChange3.getPosition-F1C5BW0()));
                            this.L$0 = job3;
                            this.L$1 = null;
                            this.L$2 = null;
                            this.L$3 = null;
                            this.label = 8;
                            if (TapGestureDetectorKt.consumeUntilUp(awaitPointerEventScope3, this) != coroutine_suspended) {
                                job6 = job3;
                                TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job6, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                                return Unit.INSTANCE;
                            }
                            return coroutine_suspended;
                        }
                        if (longPressResult2 instanceof LongPressResult.Released) {
                            job5 = job3;
                            finalUpChange2 = ((LongPressResult.Released) longPressResult2).getFinalUpChange();
                            pointerInputChange4 = finalUpChange;
                        } else {
                            if (longPressResult2 instanceof LongPressResult.Canceled) {
                                bu8.a();
                                return null;
                            }
                            pointerInputChange4 = finalUpChange;
                            job5 = job3;
                            finalUpChange2 = null;
                        }
                        if (finalUpChange2 != null) {
                            finalUpChange2.consume();
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass7(this.$pressScope, null), 2, null);
                            this.$onDoubleTap.invoke(Offset.box-impl(finalUpChange2.getPosition-F1C5BW0()));
                        } else {
                            TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job5, null, new AnonymousClass8(this.$pressScope, null), 2, null);
                            function3 = this.$onTap;
                            if (function3 != null) {
                                function3.invoke(Offset.box-impl(pointerInputChange4.getPosition-F1C5BW0()));
                            }
                        }
                        return Unit.INSTANCE;
                    case 8:
                        Job job7 = (Job) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        job6 = job7;
                        TapGestureDetectorKt.launchAwaitingReset$default(this.$$this$coroutineScope, job6, null, new TapGestureDetectorKt$detectTapGestures$2$1$secondUp$1(this.$pressScope, null), 2, null);
                        return Unit.INSTANCE;
                    default:
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C01282(PointerInputScope pointerInputScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function2, Function1<? super Offset, Unit> function4, Continuation<? super C01282> continuation) {
            super(2, continuation);
            this.$this_detectTapGestures = pointerInputScope;
            this.$onPress = function3;
            this.$onLongPress = function1;
            this.$onDoubleTap = function2;
            this.$onTap = function4;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01282 c01282 = new C01282(this.$this_detectTapGestures, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, continuation);
            c01282.L$0 = obj;
            return c01282;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                PressGestureScopeImpl pressGestureScopeImpl = new PressGestureScopeImpl(this.$this_detectTapGestures);
                PointerInputScope pointerInputScope = this.$this_detectTapGestures;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(coroutineScope, this.$onPress, this.$onLongPress, this.$onDoubleTap, this.$onTap, pressGestureScopeImpl, null);
                this.label = 1;
                if (ForEachGestureKt.awaitEachGesture(pointerInputScope, anonymousClass1, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$launchAwaitingReset$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$launchAwaitingReset$1", f = "TapGestureDetector.kt", i = {0}, l = {498, 500}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"}, v = 1)
    public static final class C01291 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Unit>, Object> $block;
        final /* synthetic */ Job $resetJob;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01291(Job job, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super C01291> continuation) {
            super(2, continuation);
            this.$resetJob = job;
            this.$block = function2;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01291 c01291 = new C01291(this.$resetJob, this.$block, continuation);
            c01291.L$0 = obj;
            return c01291;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:16:0x0045, code lost:
        
            if (r5.invoke(r1, r4) == r0) goto L17;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i != 0) {
                if (i == 1) {
                    coroutineScope = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (i != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            coroutineScope = (CoroutineScope) this.L$0;
            if (ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled) {
                Job job = this.$resetJob;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (job.join(this) != coroutine_suspended) {
                }
            }
            return coroutine_suspended;
            Function2<CoroutineScope, Continuation<? super Unit>, Object> function2 = this.$block;
            this.L$0 = null;
            this.label = 2;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForLongPress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0}, l = {384}, m = "waitForLongPress", n = {"result"}, s = {"L$0"}, v = 1)
    public static final class C01301 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public C01301(Continuation<? super C01301> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.waitForLongPress(null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForLongPress$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForLongPress$2", f = "TapGestureDetector.kt", i = {0, 1}, l = {386, 409}, m = "invokeSuspend", n = {"$this$withTimeout", "$this$withTimeout"}, s = {"L$0", "L$0"}, v = 1)
    public static final class C01312 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerEventPass $pass;
        final /* synthetic */ Ref.ObjectRef<LongPressResult> $result;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C01312(PointerEventPass pointerEventPass, Ref.ObjectRef<LongPressResult> objectRef, Continuation<? super C01312> continuation) {
            super(2, continuation);
            this.$pass = pointerEventPass;
            this.$result = objectRef;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C01312 c01312 = new C01312(this.$pass, this.$result, continuation);
            c01312.L$0 = obj;
            return c01312;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:17:0x004f  */
        /* JADX WARN: Code duplicated, block: B:21:0x0061  */
        /* JADX WARN: Code duplicated, block: B:22:0x0069  */
        /* JADX WARN: Code duplicated, block: B:26:0x0083  */
        /* JADX WARN: Code duplicated, block: B:41:0x00d2 A[LOOP:1: B:16:0x004d->B:41:0x00d2, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:47:0x00d6 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:48:0x005b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:50:0x0095 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00a6 -> B:34:0x00a9). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                Method dump skipped, instruction units count: 236
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.C01312.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.TapGestureDetectorKt", f = "TapGestureDetector.kt", i = {0, 0, 1, 1}, l = {352, 366}, m = "waitForUpOrCancellation", n = {"$this$waitForUpOrCancellation", "pass", "$this$waitForUpOrCancellation", "pass"}, s = {"L$0", "L$1", "L$0", "L$1"}, v = 1)
    public static final class C01322 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        public C01322(Continuation<? super C01322> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return TapGestureDetectorKt.waitForUpOrCancellation(null, null, this);
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x004f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x005a  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004d -> B:18:0x0050). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:0:?
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, boolean r8, androidx.compose.ui.input.pointer.PointerEventPass r9, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r10) {
        /*
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass2
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L3e
            if (r2 != r4) goto L38
            boolean r7 = r0.Z$0
            java.lang.Object r8 = r0.L$1
            androidx.compose.ui.input.pointer.PointerEventPass r8 = (androidx.compose.ui.input.pointer.PointerEventPass) r8
            java.lang.Object r9 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r9 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r9
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r8
            r8 = r7
            r7 = r9
            r9 = r6
            goto L50
        L38:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r7)
            return r3
        L3e:
            kotlin.ResultKt.throwOnFailure(r10)
        L41:
            r0.L$0 = r7
            r0.L$1 = r9
            r0.Z$0 = r8
            r0.label = r4
            java.lang.Object r10 = r7.awaitPointerEvent(r9, r0)
            if (r10 != r1) goto L50
            return r1
        L50:
            androidx.compose.ui.input.pointer.PointerEvent r10 = (androidx.compose.ui.input.pointer.PointerEvent) r10
            r2 = 2
            r5 = 0
            boolean r2 = isChangedToDown$default(r10, r8, r5, r2, r3)
            if (r2 == 0) goto L41
            java.util.List r7 = r10.getChanges()
            java.lang.Object r7 = r7.get(r5)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitFirstDown(awaitPointerEventScope, z, pointerEventPass, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x004f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0058  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x004d -> B:18:0x0050). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:0:?
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitPrimaryFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r5, boolean r6, androidx.compose.ui.input.pointer.PointerEventPass r7, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitPrimaryFirstDown$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L3e
            if (r2 != r3) goto L37
            boolean r5 = r0.Z$0
            java.lang.Object r6 = r0.L$1
            androidx.compose.ui.input.pointer.PointerEventPass r6 = (androidx.compose.ui.input.pointer.PointerEventPass) r6
            java.lang.Object r7 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
            kotlin.ResultKt.throwOnFailure(r8)
            r4 = r6
            r6 = r5
            r5 = r7
            r7 = r4
            goto L50
        L37:
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r5)
            r5 = 0
            return r5
        L3e:
            kotlin.ResultKt.throwOnFailure(r8)
        L41:
            r0.L$0 = r5
            r0.L$1 = r7
            r0.Z$0 = r6
            r0.label = r3
            java.lang.Object r8 = r5.awaitPointerEvent(r7, r0)
            if (r8 != r1) goto L50
            return r1
        L50:
            androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
            boolean r2 = isChangedToDown(r8, r6, r3)
            if (r2 == 0) goto L41
            java.util.List r5 = r8.getChanges()
            r6 = 0
            java.lang.Object r5 = r5.get(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitPrimaryFirstDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, boolean, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object awaitPrimaryFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return awaitPrimaryFirstDown(awaitPointerEventScope, z, pointerEventPass, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object awaitSecondDown(AwaitPointerEventScope awaitPointerEventScope, PointerInputChange pointerInputChange, Continuation<? super PointerInputChange> continuation) {
        return awaitPointerEventScope.withTimeoutOrNull(awaitPointerEventScope.getViewConfiguration().getDoubleTapTimeoutMillis(), new C01252(pointerInputChange, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0041 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0053 A[LOOP:0: B:19:0x0051->B:20:0x0053, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:23:0x006c  */
    /* JADX WARN: Code duplicated, block: B:26:0x0079 A[LOOP:1: B:22:0x006a->B:26:0x0079, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:31:0x0037 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x003f -> B:18:0x0042). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:23:0x006c
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object consumeUntilUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt.C01261
            if (r0 == 0) goto L13
            r0 = r10
            androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt.C01261) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$consumeUntilUp$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L34
            if (r2 != r4) goto L2e
            java.lang.Object r9 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r9 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L42
        L2e:
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r9)
            return r3
        L34:
            kotlin.ResultKt.throwOnFailure(r10)
        L37:
            r0.L$0 = r9
            r0.label = r4
            java.lang.Object r10 = androidx.compose.ui.input.pointer.AwaitPointerEventScope.awaitPointerEvent$default(r9, r3, r0, r4, r3)
            if (r10 != r1) goto L42
            return r1
        L42:
            androidx.compose.ui.input.pointer.PointerEvent r10 = (androidx.compose.ui.input.pointer.PointerEvent) r10
            java.util.List r2 = r10.getChanges()
            r5 = r2
            java.util.Collection r5 = (java.util.Collection) r5
            int r5 = r5.size()
            r6 = 0
            r7 = r6
        L51:
            if (r7 >= r5) goto L5f
            java.lang.Object r8 = r2.get(r7)
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
            r8.consume()
            int r7 = r7 + 1
            goto L51
        L5f:
            java.util.List r10 = r10.getChanges()
            r2 = r10
            java.util.Collection r2 = (java.util.Collection) r2
            int r2 = r2.size()
        L6a:
            if (r6 >= r2) goto L7c
            java.lang.Object r5 = r10.get(r6)
            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5
            boolean r5 = r5.getPressed()
            if (r5 == 0) goto L79
            goto L37
        L79:
            int r6 = r6 + 1
            goto L6a
        L7c:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.consumeUntilUp(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object detectTapAndPress(PointerInputScope pointerInputScope, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function1, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C01272(pointerInputScope, function3, function1, new PressGestureScopeImpl(pointerInputScope), null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectTapAndPress$default(PointerInputScope pointerInputScope, Function3 function3, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function3 = NoPressGesture;
        }
        if ((i & 2) != 0) {
            function1 = null;
        }
        return detectTapAndPress(pointerInputScope, function3, function1, continuation);
    }

    public static final Object detectTapGestures(PointerInputScope pointerInputScope, Function1<? super Offset, Unit> function1, Function1<? super Offset, Unit> function2, Function3<? super PressGestureScope, ? super Offset, ? super Continuation<? super Unit>, ? extends Object> function3, Function1<? super Offset, Unit> function4, Continuation<? super Unit> continuation) {
        Object objCoroutineScope = CoroutineScopeKt.coroutineScope(new C01282(pointerInputScope, function3, function2, function1, function4, null), continuation);
        return objCoroutineScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCoroutineScope : Unit.INSTANCE;
    }

    public static /* synthetic */ Object detectTapGestures$default(PointerInputScope pointerInputScope, Function1 function1, Function1 function2, Function3 function3, Function1 function4, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = null;
        }
        if ((i & 2) != 0) {
            function2 = null;
        }
        if ((i & 4) != 0) {
            function3 = NoPressGesture;
        }
        if ((i & 8) != 0) {
            function4 = null;
        }
        return detectTapGestures(pointerInputScope, function1, function2, function3, function4, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CoroutineStart getCoroutineStartForCurrentDispatchBehavior() {
        return ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled ? CoroutineStart.UNDISPATCHED : CoroutineStart.DEFAULT;
    }

    private static /* synthetic */ void getCoroutineStartForCurrentDispatchBehavior$annotations() {
    }

    public static final boolean getDetectTapGesturesEnableNewDispatchingBehavior() {
        return ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled;
    }

    @Deprecated(message = "This flag has been moved to ComposeFoundationFlags and renamed to isDetectTapGesturesImmediateCoroutineDispatchEnabled. For compatibility,  DetectTapGesturesEnableNewDispatchingBehavior controls the new flag (isDetectTapGesturesImmediateCoroutineDispatchEnabled). Please use  isDetectTapGesturesImmediateCoroutineDispatchEnabled instead.", replaceWith = @ReplaceWith(expression = "isDetectTapGesturesImmediateCoroutineDispatchEnabled", imports = {"androidx.compose.foundation.ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled"}))
    public static /* synthetic */ void getDetectTapGesturesEnableNewDispatchingBehavior$annotations() {
    }

    public static final boolean isChangedToDown(PointerEvent pointerEvent, boolean z, boolean z2) {
        if (z2) {
            List changes = pointerEvent.getChanges();
            int size = changes.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    if (PointerEvent_androidKt.isPrimaryPressed-aHzCx-E(pointerEvent.getButtons-ry648PA())) {
                        break;
                    }
                    return false;
                }
                if (!PointerType.equals-impl0(((PointerInputChange) changes.get(i)).getType-T8wyACA(), PointerType.Companion.getMouse-T8wyACA())) {
                    break;
                }
                i++;
            }
        }
        List changes2 = pointerEvent.getChanges();
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            PointerInputChange pointerInputChange = (PointerInputChange) changes2.get(i2);
            if (!(z ? PointerEventKt.changedToDown(pointerInputChange) : PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange))) {
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ boolean isChangedToDown$default(PointerEvent pointerEvent, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = TapGestureDetector_androidKt.firstDownRefersToPrimaryMouseButtonOnly();
        }
        return isChangedToDown(pointerEvent, z, z2);
    }

    private static final Job launchAwaitingReset(CoroutineScope coroutineScope, Job job, CoroutineStart coroutineStart, Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        return BuildersKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new C01291(job, function2, null), 1, (Object) null);
    }

    public static /* synthetic */ Job launchAwaitingReset$default(CoroutineScope coroutineScope, Job job, CoroutineStart coroutineStart, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineStart = getCoroutineStartForCurrentDispatchBehavior();
        }
        return launchAwaitingReset(coroutineScope, job, coroutineStart, function2);
    }

    public static final void setDetectTapGesturesEnableNewDispatchingBehavior(boolean z) {
        ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled = z;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object waitForLongPress(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation<? super LongPressResult> continuation) {
        C01301 c01301;
        Ref.ObjectRef objectRef;
        if (continuation instanceof C01301) {
            c01301 = (C01301) continuation;
            int i = c01301.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01301.label = i - Integer.MIN_VALUE;
            } else {
                c01301 = new C01301(continuation);
            }
        } else {
            c01301 = new C01301(continuation);
        }
        Object obj = c01301.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01301.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                objectRef2.element = LongPressResult.Canceled.INSTANCE;
                long longPressTimeoutMillis = awaitPointerEventScope.getViewConfiguration().getLongPressTimeoutMillis();
                C01312 c01312 = new C01312(pointerEventPass, objectRef2, null);
                c01301.L$0 = objectRef2;
                c01301.label = 1;
                if (awaitPointerEventScope.withTimeout(longPressTimeoutMillis, c01312, c01301) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                objectRef = objectRef2;
            } else {
                if (i2 != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                objectRef = (Ref.ObjectRef) c01301.L$0;
                ResultKt.throwOnFailure(obj);
            }
            return objectRef.element;
        } catch (PointerEventTimeoutCancellationException unused) {
            return LongPressResult.Success.INSTANCE;
        }
    }

    public static /* synthetic */ Object waitForLongPress$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return waitForLongPress(awaitPointerEventScope, pointerEventPass, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x007f  */
    /* JADX WARN: Code duplicated, block: B:28:0x0099  */
    /* JADX WARN: Code duplicated, block: B:30:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:45:0x00ea A[LOOP:1: B:23:0x007d->B:45:0x00ea, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:51:0x008b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x00b7 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00c4 -> B:13:0x0037). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope r17, androidx.compose.ui.input.pointer.PointerEventPass r18, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerInputChange> r19) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object waitForUpOrCancellation$default(AwaitPointerEventScope awaitPointerEventScope, PointerEventPass pointerEventPass, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            pointerEventPass = PointerEventPass.Main;
        }
        return waitForUpOrCancellation(awaitPointerEventScope, pointerEventPass, continuation);
    }

    public static /* synthetic */ Object awaitFirstDown$default(AwaitPointerEventScope awaitPointerEventScope, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return awaitFirstDown(awaitPointerEventScope, z, continuation);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with PointerEventPass instead.")
    public static final /* synthetic */ Object awaitFirstDown(AwaitPointerEventScope awaitPointerEventScope, boolean z, Continuation continuation) {
        return awaitFirstDown(awaitPointerEventScope, z, PointerEventPass.Main, continuation);
    }
}
