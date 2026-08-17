package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventKt;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0011\u0018\u00002\u00020\u0001BM\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0017\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ'\u0010\u001f\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001aH\u0016¢\u0006\u0004\b%\u0010&J\b\u0010'\u001a\u00020\u000fH\u0016JQ\u0010(\u001a\u00020\u000f2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0002\b)J\u0017\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,H\u0004¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,H\u0004¢\u0006\u0004\b0\u0010.R\u0014\u0010\u0012\u001a\u00020\u0007X\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0013\u0010\u0014R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Landroidx/compose/foundation/ClickableNode;", "Landroidx/compose/foundation/AbstractClickableNode;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "useLocalIndication", "", "enabled", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "isSuspendingPointerInputEnabled", "isSuspendingPointerInputEnabled$annotations", "()V", "createPointerInputNodeIfNeeded", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "getExtendedTouchPadding", "Landroidx/compose/ui/geometry/Size;", "size", "Landroidx/compose/ui/unit/IntSize;", "getExtendedTouchPadding-hWWAJMo", "(J)J", "downEvent", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "update", "update-O2vRcR0", "onClickKeyDownEvent", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "onClickKeyDownEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onClickKeyUpEvent", "onClickKeyUpEvent-ZmokQxo", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class ClickableNode extends AbstractClickableNode {
    public static final int $stable = 8;
    private PointerInputChange downEvent;
    private final boolean isSuspendingPointerInputEnabled;

    /* JADX INFO: renamed from: androidx.compose.foundation.ClickableNode$createPointerInputNodeIfNeeded$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements PointerInputEventHandler {

        /* JADX INFO: renamed from: androidx.compose.foundation.ClickableNode$createPointerInputNodeIfNeeded$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "offset", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.ClickableNode$createPointerInputNodeIfNeeded$1$1", f = "Clickable.kt", i = {}, l = {885}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class C00081 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
            /* synthetic */ long J$0;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ ClickableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00081(ClickableNode clickableNode, Continuation<? super C00081> continuation) {
                super(3, continuation);
                this.this$0 = clickableNode;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return m375invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).unbox-impl(), (Continuation) obj3);
            }

            /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final Object m375invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                C00081 c00081 = new C00081(this.this$0, continuation);
                c00081.L$0 = pressGestureScope;
                c00081.J$0 = j;
                return c00081.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                    long j = this.J$0;
                    if (this.this$0.getEnabled()) {
                        ClickableNode clickableNode = this.this$0;
                        this.label = 1;
                        if (clickableNode.m286handlePressInteractiond4ec7I(pressGestureScope, j, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
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

        public AnonymousClass1() {
        }

        public static Unit a(ClickableNode clickableNode, Offset offset) {
            if (clickableNode.getEnabled()) {
                clickableNode.getOnClick().invoke();
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            C00081 c00081 = new C00081(ClickableNode.this, null);
            final ClickableNode clickableNode = ClickableNode.this;
            Object objDetectTapAndPress = TapGestureDetectorKt.detectTapAndPress(pointerInputScope, c00081, new Function1() { // from class: androidx.compose.foundation.d
                public final Object invoke(Object obj) {
                    return ClickableNode.AnonymousClass1.a(clickableNode, (Offset) obj);
                }
            }, continuation);
            return objDetectTapAndPress == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapAndPress : Unit.INSTANCE;
        }
    }

    private ClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, boolean z2, String str, Role role, Function0<Unit> function0) {
        super(mutableInteractionSource, indicationNodeFactory, z, z2, str, role, function0, null);
        this.isSuspendingPointerInputEnabled = (ComposeFoundationFlags.isDetectTapGesturesImmediateCoroutineDispatchEnabled && ComposeFoundationFlags.isNonSuspendingPointerInputInClickableEnabled) ? false : true;
    }

    /* JADX INFO: renamed from: getExtendedTouchPadding-hWWAJMo, reason: not valid java name */
    private final long m373getExtendedTouchPaddinghWWAJMo(long size) {
        long j = DelegatableNodeKt.requireDensity(this).toSize-XkaWNTQ(((ViewConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, CompositionLocalsKt.getLocalViewConfiguration())).getMinimumTouchTargetSize-MYxV2XQ());
        float fMax = Math.max(0.0f, Float.intBitsToFloat((int) (j >> 32)) - ((int) (size >> 32))) / 2.0f;
        return Size.constructor-impl((((long) Float.floatToRawIntBits(Math.max(0.0f, Float.intBitsToFloat((int) (j & 4294967295L)) - ((int) (size & 4294967295L))) / 2.0f)) & 4294967295L) | (Float.floatToRawIntBits(fMax) << 32));
    }

    private static /* synthetic */ void isSuspendingPointerInputEnabled$annotations() {
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public SuspendingPointerInputModifierNode createPointerInputNodeIfNeeded() {
        if (this.isSuspendingPointerInputEnabled) {
            return SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1());
        }
        return null;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public void onCancelPointerInput() {
        super.onCancelPointerInput();
        if (this.downEvent != null) {
            this.downEvent = null;
            handlePressInteractionCancel(false);
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyDownEvent-ZmokQxo */
    public final boolean mo289onClickKeyDownEventZmokQxo(KeyEvent event) {
        return false;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyUpEvent-ZmokQxo */
    public final boolean mo290onClickKeyUpEventZmokQxo(KeyEvent event) {
        getOnClick().invoke();
        return true;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY */
    public void mo292onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        super.mo292onPointerEventH0pRuoY(pointerEvent, pass, bounds);
        if (this.isSuspendingPointerInputEnabled) {
            return;
        }
        if (pass != PointerEventPass.Main) {
            if (pass != PointerEventPass.Final || this.downEvent == null) {
                return;
            }
            List changes = pointerEvent.getChanges();
            int size = changes.size();
            for (int i = 0; i < size; i++) {
                PointerInputChange pointerInputChange = (PointerInputChange) changes.get(i);
                if (pointerInputChange.isConsumed() && !Intrinsics.areEqual(pointerInputChange, this.downEvent)) {
                    this.downEvent = null;
                    handlePressInteractionCancel(false);
                    return;
                }
            }
            return;
        }
        PointerInputChange pointerInputChange2 = this.downEvent;
        if (pointerInputChange2 == null) {
            if (TapGestureDetectorKt.isChangedToDown$default(pointerEvent, true, false, 2, null)) {
                PointerInputChange pointerInputChange3 = (PointerInputChange) pointerEvent.getChanges().get(0);
                pointerInputChange3.consume();
                this.downEvent = pointerInputChange3;
                if (getEnabled()) {
                    m288handlePressInteractionStart3MmeM6k(pointerInputChange3.getPosition-F1C5BW0(), false);
                    return;
                }
                return;
            }
            return;
        }
        List changes2 = pointerEvent.getChanges();
        int size2 = changes2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            if (!PointerEventKt.changedToUp((PointerInputChange) changes2.get(i2))) {
                long jM373getExtendedTouchPaddinghWWAJMo = m373getExtendedTouchPaddinghWWAJMo(bounds);
                List changes3 = pointerEvent.getChanges();
                int size3 = changes3.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    PointerInputChange pointerInputChange4 = (PointerInputChange) changes3.get(i3);
                    if (pointerInputChange4.isConsumed() || PointerEventKt.m1856isOutOfBoundsjwHxaWs(pointerInputChange4, bounds, jM373getExtendedTouchPaddinghWWAJMo)) {
                        this.downEvent = null;
                        handlePressInteractionCancel(false);
                        return;
                    }
                }
                return;
            }
        }
        ((PointerInputChange) pointerEvent.getChanges().get(0)).consume();
        if (getEnabled()) {
            m287handlePressInteractionRelease3MmeM6k(pointerInputChange2.getPosition-F1C5BW0(), false);
            getOnClick().invoke();
        }
        this.downEvent = null;
    }

    /* JADX INFO: renamed from: update-O2vRcR0, reason: not valid java name */
    public final void m374updateO2vRcR0(MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role, Function0<Unit> onClick) {
        m294updateCommonO2vRcR0(interactionSource, indicationNodeFactory, useLocalIndication, enabled, onClickLabel, role, onClick);
    }

    public /* synthetic */ ClickableNode(MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z, boolean z2, String str, Role role, Function0 function0, DefaultConstructorMarker defaultConstructorMarker) {
        this(mutableInteractionSource, indicationNodeFactory, z, z2, str, role, function0);
    }
}
