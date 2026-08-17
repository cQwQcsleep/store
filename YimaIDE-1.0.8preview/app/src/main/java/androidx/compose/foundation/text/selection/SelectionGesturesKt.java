package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.foundation.text.selection.SelectionGesturesKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.ViewConfiguration;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0002\u001a\u00020\u0003*\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H\u0000\u001a\"\u0010\b\u001a\u00020\u0007*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0080@¢\u0006\u0002\u0010\u000e\u001a\"\u0010\u000f\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013H\u0080@¢\u0006\u0002\u0010\u0014\u001a*\u0010\u0015\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0001H\u0082@¢\u0006\u0002\u0010\u0017\u001a*\u0010\u0018\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0080@¢\u0006\u0002\u0010\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u0013*\u00020\u0010H\u0082@¢\u0006\u0002\u0010\u001e\u001a \u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"STATIC_KEY", "", "updateSelectionTouchMode", "Landroidx/compose/ui/Modifier;", "updateTouchMode", "Lkotlin/Function1;", "", "", "awaitSelectionGestures", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "mouseSelectionObserver", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "(Landroidx/compose/ui/input/pointer/PointerInputScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/TextDragObserver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionFirstPress", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "observer", "downEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "touchSelectionSubsequentPress", "clicks", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "mouseSelection", "clicksCounter", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "down", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "awaitDown", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "distanceIsTolerable", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "change1", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "change2", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class SelectionGesturesKt {
    private static final int STATIC_KEY = 8675309;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0}, l = {340}, m = "awaitDown", n = {"$this$awaitDown"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.awaitDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitSelectionGestures$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitSelectionGestures$2", f = "SelectionGestures.kt", i = {0}, l = {111, 119, 122, 124}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"}, v = 1)
    public static final class AnonymousClass2 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ClicksCounter $clicksCounter;
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(ClicksCounter clicksCounter, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$clicksCounter = clicksCounter;
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$clicksCounter, this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
            return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code restructure failed: missing block: B:27:0x007d, code lost:
        
            if (androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelection(r1, r2, r3, r13, r12) == r0) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0094, code lost:
        
            if (androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelectionFirstPress(r1, r7, r13, r12) == r0) goto L37;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x00a5, code lost:
        
            if (androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelectionSubsequentPress(r1, r7, r13, r3, r12) == r0) goto L37;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            AwaitPointerEventScope awaitPointerEventScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                this.L$0 = awaitPointerEventScope;
                this.label = 1;
                obj = SelectionGesturesKt.awaitDown(awaitPointerEventScope, this);
                if (obj != coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i == 1) {
                awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2 && i != 3 && i != 4) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
            PointerEvent pointerEvent = (PointerEvent) obj;
            this.$clicksCounter.update(pointerEvent);
            boolean zIsMouseOrTouchPad = SelectionGestures_androidKt.isMouseOrTouchPad(pointerEvent);
            if (zIsMouseOrTouchPad && PointerEvent_androidKt.isPrimaryPressed-aHzCx-E(pointerEvent.getButtons-ry648PA())) {
                List changes = pointerEvent.getChanges();
                int size = changes.size();
                int i2 = 0;
                while (true) {
                    if (i2 >= size) {
                        MouseSelectionObserver mouseSelectionObserver = this.$mouseSelectionObserver;
                        ClicksCounter clicksCounter = this.$clicksCounter;
                        this.L$0 = null;
                        this.label = 2;
                    } else {
                        if (((PointerInputChange) changes.get(i2)).isConsumed()) {
                            break;
                        }
                        i2++;
                    }
                }
            }
            if (!zIsMouseOrTouchPad) {
                int clicks = this.$clicksCounter.getClicks();
                TextDragObserver textDragObserver = this.$textDragObserver;
                if (clicks == 1) {
                    this.L$0 = null;
                    this.label = 3;
                } else {
                    int clicks2 = this.$clicksCounter.getClicks();
                    this.L$0 = null;
                    this.label = 4;
                }
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1, 1}, l = {267, 294}, m = "mouseSelection", n = {"$this$mouseSelection", "observer", "$this$mouseSelection", "observer", "dragConsumed"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2"}, v = 1)
    public static final class C01921 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C01921(Continuation<? super C01921> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelection(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionFirstPress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {141, 145}, m = "touchSelectionFirstPress", n = {"$this$touchSelectionFirstPress", "observer", "firstDown", "$this$touchSelectionFirstPress", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"}, v = 1)
    public static final class C01931 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C01931(Continuation<? super C01931> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionFirstPress(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelectionSubsequentPress$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 0, 1, 1}, l = {193, 232}, m = "touchSelectionSubsequentPress", n = {"$this$touchSelectionSubsequentPress", "observer", "overSlop", "pointerId", "$this$touchSelectionSubsequentPress", "observer"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0", "L$1"}, v = 1)
    public static final class C01941 extends ContinuationImpl {
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        public C01941(Continuation<? super C01941> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelectionSubsequentPress(null, null, null, 0, this);
        }
    }

    public static Unit a(MouseSelectionObserver mouseSelectionObserver, PointerInputChange pointerInputChange) {
        if (mouseSelectionObserver.mo1664onExtendDragk4lQ0M(pointerInputChange.getPosition-F1C5BW0())) {
            pointerInputChange.consume();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:17:0x0043 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0054  */
    /* JADX WARN: Code duplicated, block: B:23:0x0061 A[LOOP:0: B:19:0x0052->B:23:0x0061, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:26:0x0037 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0041 -> B:18:0x0044). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = (androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L34
            if (r2 != r3) goto L2d
            java.lang.Object r7 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L44
        L2d:
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            k2d.a(r7)
            r7 = 0
            return r7
        L34:
            kotlin.ResultKt.throwOnFailure(r8)
        L37:
            androidx.compose.ui.input.pointer.PointerEventPass r8 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r7.awaitPointerEvent(r8, r0)
            if (r8 != r1) goto L44
            return r1
        L44:
            androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
            java.util.List r2 = r8.getChanges()
            r4 = r2
            java.util.Collection r4 = (java.util.Collection) r4
            int r4 = r4.size()
            r5 = 0
        L52:
            if (r5 >= r4) goto L64
            java.lang.Object r6 = r2.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDown(r6)
            if (r6 != 0) goto L61
            goto L37
        L61:
            int r5 = r5 + 1
            goto L52
        L64:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object awaitSelectionGestures(PointerInputScope pointerInputScope, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super Unit> continuation) {
        Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass2(new ClicksCounter(pointerInputScope.getViewConfiguration()), mouseSelectionObserver, textDragObserver, null), continuation);
        return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
    }

    public static Unit b(TextDragObserver textDragObserver, PointerInputChange pointerInputChange) {
        textDragObserver.mo1410onDragk4lQ0M(PointerEventKt.positionChange(pointerInputChange));
        pointerInputChange.consume();
        return Unit.INSTANCE;
    }

    public static Unit c(MouseSelectionObserver mouseSelectionObserver, SelectionAdjustment selectionAdjustment, Ref.BooleanRef booleanRef, PointerInputChange pointerInputChange) {
        if (mouseSelectionObserver.mo1662onDrag3MmeM6k(pointerInputChange.getPosition-F1C5BW0(), selectionAdjustment)) {
            pointerInputChange.consume();
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    public static Unit d(TextDragObserver textDragObserver, PointerInputChange pointerInputChange) {
        textDragObserver.mo1410onDragk4lQ0M(PointerEventKt.positionChange(pointerInputChange));
        pointerInputChange.consume();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean distanceIsTolerable(ViewConfiguration viewConfiguration, PointerInputChange pointerInputChange, PointerInputChange pointerInputChange2) {
        return Offset.getDistance-impl(Offset.minus-MK-Hz9U(pointerInputChange.getPosition-F1C5BW0(), pointerInputChange2.getPosition-F1C5BW0())) < DragGestureDetectorKt.m574pointerSlopE8SPZFQ(viewConfiguration, pointerInputChange.getType-T8wyACA());
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0096 A[Catch: all -> 0x0051, TryCatch #0 {all -> 0x0051, blocks: (B:20:0x004d, B:31:0x008e, B:33:0x0096, B:35:0x00a7, B:37:0x00b3, B:28:0x0074), top: B:69:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:35:0x00a7 A[Catch: all -> 0x0051, TryCatch #0 {all -> 0x0051, blocks: (B:20:0x004d, B:31:0x008e, B:33:0x0096, B:35:0x00a7, B:37:0x00b3, B:28:0x0074), top: B:69:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x00b3 A[Catch: all -> 0x0051, TRY_LEAVE, TryCatch #0 {all -> 0x0051, blocks: (B:20:0x004d, B:31:0x008e, B:33:0x0096, B:35:0x00a7, B:37:0x00b3, B:28:0x0074), top: B:69:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:60:0x0136 A[Catch: all -> 0x003a, TryCatch #1 {all -> 0x003a, blocks: (B:13:0x0035, B:54:0x0119, B:56:0x0121, B:58:0x0125, B:60:0x0136, B:62:0x0142, B:50:0x00ec), top: B:69:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x0142 A[Catch: all -> 0x003a, TRY_LEAVE, TryCatch #1 {all -> 0x003a, blocks: (B:13:0x0035, B:54:0x0119, B:56:0x0121, B:58:0x0125, B:60:0x0136, B:62:0x0142, B:50:0x00ec), top: B:69:0x0023 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x0145 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object mouseSelection(AwaitPointerEventScope awaitPointerEventScope, final MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C01921 c01921;
        final SelectionAdjustment none;
        AwaitPointerEventScope awaitPointerEventScope2;
        Ref.BooleanRef booleanRef;
        List changes;
        int size;
        PointerInputChange pointerInputChange;
        List changes2;
        int size2;
        PointerInputChange pointerInputChange2;
        if (continuation instanceof C01921) {
            c01921 = (C01921) continuation;
            int i = c01921.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01921.label = i - Integer.MIN_VALUE;
            } else {
                c01921 = new C01921(continuation);
            }
        } else {
            c01921 = new C01921(continuation);
        }
        Object objM571dragjO51t88 = c01921.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01921.label;
        int i3 = 0;
        try {
            try {
                if (i2 == 0) {
                    ResultKt.throwOnFailure(objM571dragjO51t88);
                    PointerInputChange pointerInputChange3 = (PointerInputChange) pointerEvent.getChanges().get(0);
                    if (!PointerEvent_androidKt.isShiftPressed-5xRPYO0(pointerEvent.getKeyboardModifiers-k7X9c1A())) {
                        int clicks = clicksCounter.getClicks();
                        if (clicks != 1) {
                            none = clicks != 2 ? SelectionAdjustment.INSTANCE.getParagraph() : SelectionAdjustment.INSTANCE.getWord();
                        } else {
                            none = SelectionAdjustment.INSTANCE.getNone();
                        }
                        if (mouseSelectionObserver.mo1665onStart9KIMszo(pointerInputChange3.getPosition-F1C5BW0(), none, clicksCounter.getClicks())) {
                            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                            booleanRef2.element = !Intrinsics.areEqual(none, SelectionAdjustment.INSTANCE.getNone());
                            long j = pointerInputChange3.getId-J3iCeTQ();
                            Function1 function1 = new Function1() { // from class: b4d
                                public final Object invoke(Object obj) {
                                    return SelectionGesturesKt.c(mouseSelectionObserver, none, booleanRef2, (PointerInputChange) obj);
                                }
                            };
                            c01921.L$0 = awaitPointerEventScope;
                            c01921.L$1 = mouseSelectionObserver;
                            c01921.L$2 = booleanRef2;
                            c01921.label = 2;
                            objM571dragjO51t88 = DragGestureDetectorKt.m571dragjO51t88(awaitPointerEventScope, j, function1, c01921);
                            if (objM571dragjO51t88 != coroutine_suspended) {
                                awaitPointerEventScope2 = awaitPointerEventScope;
                                booleanRef = booleanRef2;
                                if (((Boolean) objM571dragjO51t88).booleanValue()) {
                                    changes2 = awaitPointerEventScope2.getCurrentEvent().getChanges();
                                    size2 = changes2.size();
                                    while (i3 < size2) {
                                        pointerInputChange2 = (PointerInputChange) changes2.get(i3);
                                        if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                            pointerInputChange2.consume();
                                        }
                                        i3++;
                                    }
                                }
                                mouseSelectionObserver.onDragDone();
                            }
                            return coroutine_suspended;
                        }
                    } else if (mouseSelectionObserver.mo1663onExtendk4lQ0M(pointerInputChange3.getPosition-F1C5BW0())) {
                        pointerInputChange3.consume();
                        long j2 = pointerInputChange3.getId-J3iCeTQ();
                        Function1 function2 = new Function1() { // from class: a4d
                            public final Object invoke(Object obj) {
                                return SelectionGesturesKt.a(mouseSelectionObserver, (PointerInputChange) obj);
                            }
                        };
                        c01921.L$0 = awaitPointerEventScope;
                        c01921.L$1 = mouseSelectionObserver;
                        c01921.label = 1;
                        objM571dragjO51t88 = DragGestureDetectorKt.m571dragjO51t88(awaitPointerEventScope, j2, function2, c01921);
                        if (objM571dragjO51t88 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        if (((Boolean) objM571dragjO51t88).booleanValue()) {
                            changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                            size = changes.size();
                            while (i3 < size) {
                                pointerInputChange = (PointerInputChange) changes.get(i3);
                                if (PointerEventKt.changedToUp(pointerInputChange)) {
                                    pointerInputChange.consume();
                                }
                                i3++;
                            }
                        }
                        mouseSelectionObserver.onDragDone();
                    }
                } else if (i2 == 1) {
                    mouseSelectionObserver = (MouseSelectionObserver) c01921.L$1;
                    awaitPointerEventScope = (AwaitPointerEventScope) c01921.L$0;
                    ResultKt.throwOnFailure(objM571dragjO51t88);
                    if (((Boolean) objM571dragjO51t88).booleanValue()) {
                        changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                        size = changes.size();
                        while (i3 < size) {
                            pointerInputChange = (PointerInputChange) changes.get(i3);
                            if (PointerEventKt.changedToUp(pointerInputChange)) {
                                pointerInputChange.consume();
                            }
                            i3++;
                        }
                    }
                    mouseSelectionObserver.onDragDone();
                } else {
                    if (i2 != 2) {
                        k2d.a("call to 'resume' before 'invoke' with coroutine");
                        return null;
                    }
                    booleanRef = (Ref.BooleanRef) c01921.L$2;
                    mouseSelectionObserver = (MouseSelectionObserver) c01921.L$1;
                    awaitPointerEventScope2 = (AwaitPointerEventScope) c01921.L$0;
                    ResultKt.throwOnFailure(objM571dragjO51t88);
                    if (((Boolean) objM571dragjO51t88).booleanValue() && booleanRef.element) {
                        changes2 = awaitPointerEventScope2.getCurrentEvent().getChanges();
                        size2 = changes2.size();
                        while (i3 < size2) {
                            pointerInputChange2 = (PointerInputChange) changes2.get(i3);
                            if (PointerEventKt.changedToUp(pointerInputChange2)) {
                                pointerInputChange2.consume();
                            }
                            i3++;
                        }
                    }
                    mouseSelectionObserver.onDragDone();
                }
                return Unit.INSTANCE;
            } catch (Throwable th) {
                mouseSelectionObserver.onDragDone();
                throw th;
            }
        } catch (Throwable th2) {
            mouseSelectionObserver.onDragDone();
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a3, code lost:
    
        if (r12 == r1) goto L32;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object touchSelectionFirstPress(AwaitPointerEventScope awaitPointerEventScope, final TextDragObserver textDragObserver, PointerEvent pointerEvent, Continuation<? super Unit> continuation) {
        C01931 c01931;
        PointerInputChange pointerInputChange;
        if (continuation instanceof C01931) {
            c01931 = (C01931) continuation;
            int i = c01931.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c01931.label = i - Integer.MIN_VALUE;
            } else {
                c01931 = new C01931(continuation);
            }
        } else {
            c01931 = new C01931(continuation);
        }
        Object objM561awaitLongPressOrCancellationrnUCldI = c01931.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c01931.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(objM561awaitLongPressOrCancellationrnUCldI);
                pointerInputChange = (PointerInputChange) CollectionsKt.first(pointerEvent.getChanges());
                long j = pointerInputChange.getId-J3iCeTQ();
                c01931.L$0 = awaitPointerEventScope;
                c01931.L$1 = textDragObserver;
                c01931.L$2 = pointerInputChange;
                c01931.label = 1;
                objM561awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m561awaitLongPressOrCancellationrnUCldI(awaitPointerEventScope, j, c01931);
                if (objM561awaitLongPressOrCancellationrnUCldI == coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i2 == 1) {
                PointerInputChange pointerInputChange2 = (PointerInputChange) c01931.L$2;
                textDragObserver = (TextDragObserver) c01931.L$1;
                AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) c01931.L$0;
                ResultKt.throwOnFailure(objM561awaitLongPressOrCancellationrnUCldI);
                pointerInputChange = pointerInputChange2;
                awaitPointerEventScope = awaitPointerEventScope2;
            } else {
                if (i2 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                textDragObserver = (TextDragObserver) c01931.L$1;
                awaitPointerEventScope = (AwaitPointerEventScope) c01931.L$0;
                ResultKt.throwOnFailure(objM561awaitLongPressOrCancellationrnUCldI);
            }
            if (((Boolean) objM561awaitLongPressOrCancellationrnUCldI).booleanValue()) {
                List changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                int size = changes.size();
                for (int i3 = 0; i3 < size; i3++) {
                    PointerInputChange pointerInputChange3 = (PointerInputChange) changes.get(i3);
                    if (PointerEventKt.changedToUp(pointerInputChange3)) {
                        pointerInputChange3.consume();
                    }
                }
                textDragObserver.onStop();
            } else {
                textDragObserver.onCancel();
            }
            return Unit.INSTANCE;
            PointerInputChange pointerInputChange4 = (PointerInputChange) objM561awaitLongPressOrCancellationrnUCldI;
            if (pointerInputChange4 != null && distanceIsTolerable(awaitPointerEventScope.getViewConfiguration(), pointerInputChange, pointerInputChange4)) {
                textDragObserver.mo1411onStart3MmeM6k(pointerInputChange4.getPosition-F1C5BW0(), SelectionAdjustment.INSTANCE.getWord());
                long j2 = pointerInputChange4.getId-J3iCeTQ();
                Function1 function1 = new Function1() { // from class: c4d
                    public final Object invoke(Object obj) {
                        return SelectionGesturesKt.b(textDragObserver, (PointerInputChange) obj);
                    }
                };
                c01931.L$0 = awaitPointerEventScope;
                c01931.L$1 = textDragObserver;
                c01931.L$2 = null;
                c01931.label = 2;
                objM561awaitLongPressOrCancellationrnUCldI = DragGestureDetectorKt.m571dragjO51t88(awaitPointerEventScope, j2, function1, c01931);
            }
            return Unit.INSTANCE;
        } catch (CancellationException e) {
            textDragObserver.onCancel();
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00df, code lost:
    
        if (r14 == r1) goto L48;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final Object touchSelectionSubsequentPress(AwaitPointerEventScope awaitPointerEventScope, final TextDragObserver textDragObserver, PointerEvent pointerEvent, int i, Continuation<? super Unit> continuation) {
        C01941 c01941;
        long j;
        Ref.LongRef longRef;
        if (continuation instanceof C01941) {
            c01941 = (C01941) continuation;
            int i2 = c01941.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c01941.label = i2 - Integer.MIN_VALUE;
            } else {
                c01941 = new C01941(continuation);
            }
        } else {
            c01941 = new C01941(continuation);
        }
        Object objWithTimeoutOrNull = c01941.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c01941.label;
        try {
            if (i3 == 0) {
                ResultKt.throwOnFailure(objWithTimeoutOrNull);
                PointerInputChange pointerInputChange = (PointerInputChange) CollectionsKt.first(pointerEvent.getChanges());
                j = pointerInputChange.getId-J3iCeTQ();
                textDragObserver.mo1411onStart3MmeM6k(pointerInputChange.getPosition-F1C5BW0(), i > 2 ? SelectionAdjustment.INSTANCE.getParagraph() : SelectionAdjustment.INSTANCE.getWord());
                longRef = new Ref.LongRef();
                longRef.element = Offset.Companion.getUnspecified-F1C5BW0();
                long longPressTimeoutMillis = awaitPointerEventScope.getViewConfiguration().getLongPressTimeoutMillis();
                SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1 = new SelectionGesturesKt$touchSelectionSubsequentPress$downResolution$1(j, longRef, null);
                c01941.L$0 = awaitPointerEventScope;
                c01941.L$1 = textDragObserver;
                c01941.L$2 = longRef;
                c01941.J$0 = j;
                c01941.label = 1;
                objWithTimeoutOrNull = awaitPointerEventScope.withTimeoutOrNull(longPressTimeoutMillis, selectionGesturesKt$touchSelectionSubsequentPress$downResolution$1, c01941);
                if (objWithTimeoutOrNull == coroutine_suspended) {
                }
                return coroutine_suspended;
            }
            if (i3 == 1) {
                long j2 = c01941.J$0;
                longRef = (Ref.LongRef) c01941.L$2;
                TextDragObserver textDragObserver2 = (TextDragObserver) c01941.L$1;
                AwaitPointerEventScope awaitPointerEventScope2 = (AwaitPointerEventScope) c01941.L$0;
                try {
                    ResultKt.throwOnFailure(objWithTimeoutOrNull);
                    j = j2;
                    textDragObserver = textDragObserver2;
                    awaitPointerEventScope = awaitPointerEventScope2;
                } catch (CancellationException e) {
                    e = e;
                    textDragObserver = textDragObserver2;
                    textDragObserver.onCancel();
                    throw e;
                }
            } else {
                if (i3 != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                textDragObserver = (TextDragObserver) c01941.L$1;
                awaitPointerEventScope = (AwaitPointerEventScope) c01941.L$0;
                ResultKt.throwOnFailure(objWithTimeoutOrNull);
            }
            if (((Boolean) objWithTimeoutOrNull).booleanValue()) {
                List changes = awaitPointerEventScope.getCurrentEvent().getChanges();
                int size = changes.size();
                for (int i4 = 0; i4 < size; i4++) {
                    PointerInputChange pointerInputChange2 = (PointerInputChange) changes.get(i4);
                    if (PointerEventKt.changedToUp(pointerInputChange2)) {
                        pointerInputChange2.consume();
                    }
                }
                textDragObserver.onStop();
            } else {
                textDragObserver.onCancel();
            }
            return Unit.INSTANCE;
            DownResolution downResolution = (DownResolution) objWithTimeoutOrNull;
            if (downResolution == null) {
                downResolution = DownResolution.Timeout;
            }
            if (downResolution == DownResolution.Cancel) {
                textDragObserver.onCancel();
                return Unit.INSTANCE;
            }
            if (downResolution == DownResolution.Up) {
                textDragObserver.onStop();
                return Unit.INSTANCE;
            }
            if (downResolution == DownResolution.Drag) {
                textDragObserver.mo1410onDragk4lQ0M(longRef.element);
            }
            Function1 function1 = new Function1() { // from class: d4d
                public final Object invoke(Object obj) {
                    return SelectionGesturesKt.d(textDragObserver, (PointerInputChange) obj);
                }
            };
            c01941.L$0 = awaitPointerEventScope;
            c01941.L$1 = textDragObserver;
            c01941.L$2 = null;
            c01941.label = 2;
            objWithTimeoutOrNull = DragGestureDetectorKt.m571dragjO51t88(awaitPointerEventScope, j, function1, c01941);
        } catch (CancellationException e2) {
            e = e2;
        }
    }

    public static final Modifier updateSelectionTouchMode(Modifier modifier, final Function1<? super Boolean, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, Integer.valueOf(STATIC_KEY), new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.selection.SelectionGesturesKt.updateSelectionTouchMode.1

            /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1", f = "SelectionGestures.kt", i = {0}, l = {94}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"}, v = 1)
            public static final class C00401 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
                private /* synthetic */ Object L$0;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                public C00401(Function1<? super Boolean, Unit> function1, Continuation<? super C00401> continuation) {
                    super(2, continuation);
                    this.$updateTouchMode = function1;
                }

                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00401 c00401 = new C00401(this.$updateTouchMode, continuation);
                    c00401.L$0 = obj;
                    return c00401;
                }

                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code duplicated, block: B:11:0x002e A[RETURN] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002c -> B:12:0x002f). Please report as a decompilation issue!!! */
                /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:11:0x002e
                    	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                    	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                    	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                    */
                public final java.lang.Object invokeSuspend(java.lang.Object r5) {
                    /*
                        r4 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r4.label
                        r2 = 1
                        if (r1 == 0) goto L1a
                        if (r1 != r2) goto L13
                        java.lang.Object r1 = r4.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r5)
                        goto L2f
                    L13:
                        java.lang.String r4 = "call to 'resume' before 'invoke' with coroutine"
                        k2d.a(r4)
                        r4 = 0
                        return r4
                    L1a:
                        kotlin.ResultKt.throwOnFailure(r5)
                        java.lang.Object r5 = r4.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                        r1 = r5
                    L22:
                        androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r4.L$0 = r1
                        r4.label = r2
                        java.lang.Object r5 = r1.awaitPointerEvent(r5, r4)
                        if (r5 != r0) goto L2f
                        return r0
                    L2f:
                        androidx.compose.ui.input.pointer.PointerEvent r5 = (androidx.compose.ui.input.pointer.PointerEvent) r5
                        kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r3 = r4.$updateTouchMode
                        boolean r5 = androidx.compose.foundation.text.selection.SelectionGestures_androidKt.isMouseOrTouchPad(r5)
                        r5 = r5 ^ r2
                        java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
                        r3.invoke(r5)
                        goto L22
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C01951.C00401.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objAwaitPointerEventScope = pointerInputScope.awaitPointerEventScope(new C00401(function1, null), continuation);
                return objAwaitPointerEventScope == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitPointerEventScope : Unit.INSTANCE;
            }
        });
    }
}
