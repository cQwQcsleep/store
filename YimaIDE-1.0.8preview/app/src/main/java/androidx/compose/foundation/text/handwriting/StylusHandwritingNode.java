package androidx.compose.foundation.text.handwriting;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetector_androidKt;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNodeKt;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0011\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0015\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J'\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 J\b\u0010!\u001a\u00020\u0007H\u0016J\u0006\u0010\"\u001a\u00020\u0007R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/foundation/text/handwriting/StylusHandwritingNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/focus/FocusEventModifierNode;", "Landroidx/compose/ui/focus/FocusRequesterModifierNode;", "onHandwritingSlopExceeded", "Lkotlin/Function0;", "", "<init>", "(Lkotlin/jvm/functions/Function0;)V", "getOnHandwritingSlopExceeded", "()Lkotlin/jvm/functions/Function0;", "setOnHandwritingSlopExceeded", "focused", "", "onFocusEvent", "focusState", "Landroidx/compose/ui/focus/FocusState;", "touchBoundsExpansion", "Landroidx/compose/ui/node/TouchBoundsExpansion;", "getTouchBoundsExpansion-RZrCHBk", "()J", "suspendingPointerInputModifierNode", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "resetPointerInputHandler", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class StylusHandwritingNode extends DelegatingNode implements PointerInputModifierNode, FocusEventModifierNode, FocusRequesterModifierNode {
    public static final int $stable = 8;
    private boolean focused;
    private Function0<Unit> onHandwritingSlopExceeded;
    private final SuspendingPointerInputModifierNode suspendingPointerInputModifierNode = delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1

        /* JADX INFO: renamed from: androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1$1, reason: invalid class name */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.handwriting.StylusHandwritingNode$suspendingPointerInputModifierNode$1$1", f = "StylusHandwriting.kt", i = {0, 1, 1, 1, 2, 2}, l = {116, 144, 182}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "firstDown", "pass", "$this$awaitEachGesture", "firstDown"}, s = {"L$0", "L$0", "L$1", "L$2", "L$0", "L$1"}, v = 1)
        public static final class AnonymousClass1 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ StylusHandwritingNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(StylusHandwritingNode stylusHandwritingNode, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = stylusHandwritingNode;
            }

            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return create(awaitPointerEventScope, continuation).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Code restructure failed: missing block: B:13:0x0057, code lost:
            
                if (r8 == r1) goto L74;
             */
            /* JADX WARN: Code restructure failed: missing block: B:40:0x00f2, code lost:
            
                if (r9 == r1) goto L74;
             */
            /* JADX WARN: Code restructure failed: missing block: B:73:0x01a5, code lost:
            
                if (r5 == r1) goto L74;
             */
            /* JADX WARN: Code restructure failed: missing block: B:74:0x01a7, code lost:
            
                return r1;
             */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00f2 -> B:42:0x00f6). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x01a5 -> B:75:0x01a8). Please report as a decompilation issue!!! */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public final Object invokeSuspend(Object obj) {
                AwaitPointerEventScope awaitPointerEventScope;
                Object objAwaitFirstDown;
                AwaitPointerEventScope awaitPointerEventScope2;
                PointerEventPass pointerEventPass;
                PointerInputChange pointerInputChange;
                Object objAwaitPointerEvent;
                AwaitPointerEventScope awaitPointerEventScope3;
                Object obj2;
                AwaitPointerEventScope awaitPointerEventScope4;
                PointerInputChange pointerInputChange2;
                Object objAwaitPointerEvent2;
                Object obj3;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                int i2 = 2;
                Object obj4 = null;
                if (i != 0) {
                    if (i == 1) {
                        awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objAwaitFirstDown = obj;
                    } else if (i == 2) {
                        pointerEventPass = (PointerEventPass) this.L$2;
                        pointerInputChange = (PointerInputChange) this.L$1;
                        awaitPointerEventScope2 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objAwaitPointerEvent = obj;
                        PointerEvent pointerEvent = (PointerEvent) objAwaitPointerEvent;
                        List changes = pointerEvent.getChanges();
                        int size = changes.size();
                        int i3 = 0;
                        while (true) {
                            if (i3 >= size) {
                                awaitPointerEventScope3 = awaitPointerEventScope2;
                                obj2 = null;
                                break;
                            }
                            obj2 = changes.get(i3);
                            PointerInputChange pointerInputChange3 = (PointerInputChange) obj2;
                            if (!pointerInputChange3.isConsumed()) {
                                awaitPointerEventScope3 = awaitPointerEventScope2;
                                if (PointerId.equals-impl0(pointerInputChange3.getId-J3iCeTQ(), pointerInputChange.getId-J3iCeTQ()) && pointerInputChange3.getPressed()) {
                                    break;
                                }
                            } else {
                                awaitPointerEventScope3 = awaitPointerEventScope2;
                            }
                            i3++;
                            awaitPointerEventScope2 = awaitPointerEventScope3;
                        }
                        PointerInputChange pointerInputChange4 = (PointerInputChange) obj2;
                        if (pointerInputChange4 == null || pointerInputChange4.getUptimeMillis() - pointerInputChange.getUptimeMillis() >= awaitPointerEventScope3.getViewConfiguration().getLongPressTimeoutMillis() || TapGestureDetector_androidKt.isDeepPress(pointerEvent)) {
                            pointerInputChange4 = null;
                        } else {
                            if (Offset.getDistance-impl(Offset.minus-MK-Hz9U(pointerInputChange4.getPosition-F1C5BW0(), pointerInputChange.getPosition-F1C5BW0())) <= awaitPointerEventScope3.getViewConfiguration().getHandwritingSlop()) {
                                awaitPointerEventScope2 = awaitPointerEventScope3;
                                i2 = 2;
                                this.L$0 = awaitPointerEventScope2;
                                this.L$1 = pointerInputChange;
                                this.L$2 = pointerEventPass;
                                this.label = i2;
                                objAwaitPointerEvent = awaitPointerEventScope2.awaitPointerEvent(pointerEventPass, this);
                            }
                            PointerEventPass pointerEventPass2 = PointerEventPass.Initial;
                            this.L$0 = awaitPointerEventScope4;
                            this.L$1 = pointerInputChange2;
                            obj4 = null;
                            this.L$2 = null;
                            this.label = 3;
                            objAwaitPointerEvent2 = awaitPointerEventScope4.awaitPointerEvent(pointerEventPass2, this);
                        }
                        if (pointerInputChange4 == null) {
                            return Unit.INSTANCE;
                        }
                        if (!this.this$0.focused) {
                            FocusRequesterModifierNodeKt.requestFocus(this.this$0);
                        }
                        this.this$0.getOnHandwritingSlopExceeded().invoke();
                        pointerInputChange4.consume();
                        awaitPointerEventScope4 = awaitPointerEventScope3;
                        pointerInputChange2 = pointerInputChange;
                        PointerEventPass pointerEventPass3 = PointerEventPass.Initial;
                        this.L$0 = awaitPointerEventScope4;
                        this.L$1 = pointerInputChange2;
                        obj4 = null;
                        this.L$2 = null;
                        this.label = 3;
                        objAwaitPointerEvent2 = awaitPointerEventScope4.awaitPointerEvent(pointerEventPass3, this);
                    } else {
                        if (i != 3) {
                            k2d.a("call to 'resume' before 'invoke' with coroutine");
                            return null;
                        }
                        pointerInputChange2 = (PointerInputChange) this.L$1;
                        awaitPointerEventScope4 = (AwaitPointerEventScope) this.L$0;
                        ResultKt.throwOnFailure(obj);
                        objAwaitPointerEvent2 = obj;
                    }
                    List changes2 = ((PointerEvent) objAwaitPointerEvent2).getChanges();
                    int size2 = changes2.size();
                    int i4 = 0;
                    while (true) {
                        if (i4 >= size2) {
                            obj3 = obj4;
                            break;
                        }
                        obj3 = changes2.get(i4);
                        PointerInputChange pointerInputChange5 = (PointerInputChange) obj3;
                        if (!pointerInputChange5.isConsumed() && PointerId.equals-impl0(pointerInputChange5.getId-J3iCeTQ(), pointerInputChange2.getId-J3iCeTQ()) && pointerInputChange5.getPressed()) {
                            break;
                        }
                        i4++;
                    }
                    PointerInputChange pointerInputChange6 = (PointerInputChange) obj3;
                    if (pointerInputChange6 == null) {
                        return Unit.INSTANCE;
                    }
                    pointerInputChange6.consume();
                    PointerEventPass pointerEventPass4 = PointerEventPass.Initial;
                    this.L$0 = awaitPointerEventScope4;
                    this.L$1 = pointerInputChange2;
                    obj4 = null;
                    this.L$2 = null;
                    this.label = 3;
                    objAwaitPointerEvent2 = awaitPointerEventScope4.awaitPointerEvent(pointerEventPass4, this);
                } else {
                    ResultKt.throwOnFailure(obj);
                    awaitPointerEventScope = (AwaitPointerEventScope) this.L$0;
                    PointerEventPass pointerEventPass5 = PointerEventPass.Initial;
                    this.L$0 = awaitPointerEventScope;
                    this.label = 1;
                    objAwaitFirstDown = TapGestureDetectorKt.awaitFirstDown(awaitPointerEventScope, true, pointerEventPass5, this);
                }
                PointerInputChange pointerInputChange7 = (PointerInputChange) objAwaitFirstDown;
                int i5 = pointerInputChange7.getType-T8wyACA();
                PointerType.Companion companion = PointerType.Companion;
                if (!PointerType.equals-impl0(i5, companion.getStylus-T8wyACA()) && !PointerType.equals-impl0(pointerInputChange7.getType-T8wyACA(), companion.getEraser-T8wyACA())) {
                    return Unit.INSTANCE;
                }
                PointerEventPass pointerEventPass6 = (this.this$0.focused || (Float.intBitsToFloat((int) (pointerInputChange7.getPosition-F1C5BW0() >> 32)) >= 0.0f && Float.intBitsToFloat((int) (pointerInputChange7.getPosition-F1C5BW0() >> 32)) < ((float) ((int) (awaitPointerEventScope.getSize-YbymL2g() >> 32))) && Float.intBitsToFloat((int) (pointerInputChange7.getPosition-F1C5BW0() & 4294967295L)) >= 0.0f && Float.intBitsToFloat((int) (pointerInputChange7.getPosition-F1C5BW0() & 4294967295L)) < ((float) ((int) (awaitPointerEventScope.getSize-YbymL2g() & 4294967295L))))) ? PointerEventPass.Initial : PointerEventPass.Main;
                awaitPointerEventScope2 = awaitPointerEventScope;
                pointerEventPass = pointerEventPass6;
                pointerInputChange = pointerInputChange7;
                this.L$0 = awaitPointerEventScope2;
                this.L$1 = pointerInputChange;
                this.L$2 = pointerEventPass;
                this.label = i2;
                objAwaitPointerEvent = awaitPointerEventScope2.awaitPointerEvent(pointerEventPass, this);
            }
        }

        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            Object objAwaitEachGesture = ForEachGestureKt.awaitEachGesture(pointerInputScope, new AnonymousClass1(this.this$0, null), continuation);
            return objAwaitEachGesture == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objAwaitEachGesture : Unit.INSTANCE;
        }
    }));

    public StylusHandwritingNode(Function0<Unit> function0) {
        this.onHandwritingSlopExceeded = function0;
    }

    public final Function0<Unit> getOnHandwritingSlopExceeded() {
        return this.onHandwritingSlopExceeded;
    }

    /* JADX INFO: renamed from: getTouchBoundsExpansion-RZrCHBk, reason: not valid java name */
    public long m1473getTouchBoundsExpansionRZrCHBk() {
        return StylusHandwritingKt.getHandwritingBoundsExpansion().roundToTouchBoundsExpansion-TW6G1oQ(DelegatableNodeKt.requireDensity(this));
    }

    public void onCancelPointerInput() {
        this.suspendingPointerInputModifierNode.onCancelPointerInput();
    }

    public void onFocusEvent(FocusState focusState) {
        this.focused = focusState.isFocused();
    }

    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void m1474onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        this.suspendingPointerInputModifierNode.onPointerEvent-H0pRuoY(pointerEvent, pass, bounds);
    }

    public final void resetPointerInputHandler() {
        this.suspendingPointerInputModifierNode.resetPointerInputHandler();
    }

    public final void setOnHandwritingSlopExceeded(Function0<Unit> function0) {
        this.onHandwritingSlopExceeded = function0;
    }
}
