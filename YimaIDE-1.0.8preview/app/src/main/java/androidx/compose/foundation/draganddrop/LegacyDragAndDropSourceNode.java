package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draganddrop.DragAndDropModifierNode;
import androidx.compose.ui.draganddrop.DragAndDropNodeKt;
import androidx.compose.ui.draganddrop.DragAndDropTransferData;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutAwareModifierNode;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002BI\u0012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007\u0012'\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\u0007¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0016¢\u0006\u0004\b\u001c\u0010\u001dR+\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R=\u0010\b\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t¢\u0006\u0002\b\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006\u001e"}, d2 = {"Landroidx/compose/foundation/draganddrop/LegacyDragAndDropSourceNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/LayoutAwareModifierNode;", "drawDragDecoration", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "dragAndDropSourceHandler", "Lkotlin/Function2;", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Lkotlin/coroutines/Continuation;", "", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)V", "getDrawDragDecoration", "()Lkotlin/jvm/functions/Function1;", "setDrawDragDecoration", "(Lkotlin/jvm/functions/Function1;)V", "getDragAndDropSourceHandler", "()Lkotlin/jvm/functions/Function2;", "setDragAndDropSourceHandler", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "size", "Landroidx/compose/ui/unit/IntSize;", "J", "onRemeasured", "onRemeasured-ozmzZPI", "(J)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LegacyDragAndDropSourceNode extends DelegatingNode implements LayoutAwareModifierNode {
    public static final int $stable = 8;
    private Function2<? super DragAndDropSourceScope, ? super Continuation<? super Unit>, ? extends Object> dragAndDropSourceHandler;
    private Function1<? super DrawScope, Unit> drawDragDecoration;
    private long size = IntSize.Companion.getZero-YbymL2g();

    public LegacyDragAndDropSourceNode(Function1<? super DrawScope, Unit> function1, Function2<? super DragAndDropSourceScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.drawDragDecoration = function1;
        this.dragAndDropSourceHandler = function2;
        final DragAndDropModifierNode dragAndDropModifierNodeDelegate = delegate(DragAndDropNodeKt.DragAndDropModifierNode());
        delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new PointerInputEventHandler() { // from class: androidx.compose.foundation.draganddrop.LegacyDragAndDropSourceNode.1
            public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
                Object objInvoke = LegacyDragAndDropSourceNode.this.getDragAndDropSourceHandler().invoke(new C00111(pointerInputScope, dragAndDropModifierNodeDelegate, LegacyDragAndDropSourceNode.this), continuation);
                return objInvoke == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objInvoke : Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.draganddrop.LegacyDragAndDropSourceNode$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\u0083\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016JC\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2'\u0010\n\u001a#\b\u0001\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\r\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b¢\u0006\u0002\b\u000eH\u0096A¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0097\u0001¢\u0006\u0004\b\u0013\u0010\u0014J\u0014\u0010\u0010\u001a\u00020\u0011*\u00020\u0015H\u0097\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u0012*\u00020\u0011H\u0097\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0014\u0010\u0018\u001a\u00020\u0012*\u00020\u001bH\u0097\u0001¢\u0006\u0004\b\u0019\u0010\u001cJ\u0014\u0010\u0018\u001a\u00020\u0012*\u00020\u0015H\u0097\u0001¢\u0006\u0004\b\u001d\u0010\u001eJ\u0014\u0010\u001f\u001a\u00020 *\u00020!H\u0097\u0001¢\u0006\u0004\b\"\u0010#J\u0014\u0010$\u001a\u00020\u001b*\u00020\u0012H\u0097\u0001¢\u0006\u0004\b%\u0010\u001cJ\u0014\u0010$\u001a\u00020\u001b*\u00020\u0015H\u0097\u0001¢\u0006\u0004\b&\u0010\u001eJ\r\u0010'\u001a\u00020(*\u00020)H\u0097\u0001J\u0014\u0010*\u001a\u00020!*\u00020 H\u0097\u0001¢\u0006\u0004\b+\u0010#J\u0014\u0010,\u001a\u00020\u0015*\u00020\u0011H\u0097\u0001¢\u0006\u0004\b-\u0010.J\u0014\u0010,\u001a\u00020\u0015*\u00020\u001bH\u0097\u0001¢\u0006\u0004\b-\u0010/J\u0014\u0010,\u001a\u00020\u0015*\u00020\u0012H\u0097\u0001¢\u0006\u0004\b0\u0010/R\u0014\u00101\u001a\u00020\u001b8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b2\u00103R\u0014\u00104\u001a\u00020!8VX\u0096\u0005¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00107\u001a\u00020\u001b8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b8\u00103R$\u0010;\u001a\u00020:2\u0006\u00109\u001a\u00020:8V@VX\u0096\u000f¢\u0006\f\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0012\u0010@\u001a\u00020AX\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u00106R\u0012\u0010C\u001a\u00020DX\u0096\u0005¢\u0006\u0006\u001a\u0004\bE\u0010F¨\u0006G"}, d2 = {"androidx/compose/foundation/draganddrop/LegacyDragAndDropSourceNode$1$1", "Landroidx/compose/foundation/draganddrop/DragAndDropSourceScope;", "Landroidx/compose/ui/input/pointer/PointerInputScope;", "startTransfer", "", "transferData", "Landroidx/compose/ui/draganddrop/DragAndDropTransferData;", "awaitPointerEventScope", "R", "", "block", "Lkotlin/Function2;", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(I)F", "", "(F)F", "toDp-GaN1DYA", "(J)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(I)J", "(F)J", "toSp-0xMU5do", "density", "getDensity", "()F", "extendedTouchPadding", "getExtendedTouchPadding-NH-jbRc", "()J", "fontScale", "getFontScale", "_", "", "interceptOutOfBoundsChildEvents", "getInterceptOutOfBoundsChildEvents", "()Z", "setInterceptOutOfBoundsChildEvents", "(Z)V", "size", "Landroidx/compose/ui/unit/IntSize;", "getSize-YbymL2g", "viewConfiguration", "Landroidx/compose/ui/platform/ViewConfiguration;", "getViewConfiguration", "()Landroidx/compose/ui/platform/ViewConfiguration;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
            public static final class C00111 implements DragAndDropSourceScope, PointerInputScope {
                private final /* synthetic */ PointerInputScope $$delegate_0;
                final /* synthetic */ DragAndDropModifierNode $dragAndDropModifierNode;
                final /* synthetic */ LegacyDragAndDropSourceNode this$0;

                public C00111(PointerInputScope pointerInputScope, DragAndDropModifierNode dragAndDropModifierNode, LegacyDragAndDropSourceNode legacyDragAndDropSourceNode) {
                    this.$dragAndDropModifierNode = dragAndDropModifierNode;
                    this.this$0 = legacyDragAndDropSourceNode;
                    this.$$delegate_0 = pointerInputScope;
                }

                public <R> Object awaitPointerEventScope(Function2<? super AwaitPointerEventScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
                    return this.$$delegate_0.awaitPointerEventScope(function2, continuation);
                }

                public float getDensity() {
                    return this.$$delegate_0.getDensity();
                }

                /* JADX INFO: renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
                public long m493getExtendedTouchPaddingNHjbRc() {
                    return this.$$delegate_0.getExtendedTouchPadding-NH-jbRc();
                }

                public float getFontScale() {
                    return this.$$delegate_0.getFontScale();
                }

                public boolean getInterceptOutOfBoundsChildEvents() {
                    return this.$$delegate_0.getInterceptOutOfBoundsChildEvents();
                }

                /* JADX INFO: renamed from: getSize-YbymL2g, reason: not valid java name */
                public long m494getSizeYbymL2g() {
                    return this.$$delegate_0.getSize-YbymL2g();
                }

                public ViewConfiguration getViewConfiguration() {
                    return this.$$delegate_0.getViewConfiguration();
                }

                /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
                public int m495roundToPxR2X_6o(long j) {
                    return this.$$delegate_0.roundToPx--R2X_6o(j);
                }

                /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
                public int m496roundToPx0680j_4(float f) {
                    return this.$$delegate_0.roundToPx-0680j_4(f);
                }

                public void setInterceptOutOfBoundsChildEvents(boolean z) {
                    this.$$delegate_0.setInterceptOutOfBoundsChildEvents(z);
                }

                @Override // androidx.compose.foundation.draganddrop.DragAndDropSourceScope
                public void startTransfer(DragAndDropTransferData transferData) {
                    this.$dragAndDropModifierNode.drag-12SF9DM(transferData, IntSizeKt.toSize-ozmzZPI(m494getSizeYbymL2g()), this.this$0.getDrawDragDecoration());
                }

                /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
                public float m497toDpGaN1DYA(long j) {
                    return this.$$delegate_0.toDp-GaN1DYA(j);
                }

                /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
                public float m498toDpu2uoSUM(float f) {
                    return this.$$delegate_0.toDp-u2uoSUM(f);
                }

                /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
                public long m500toDpSizekrfVVM(long j) {
                    return this.$$delegate_0.toDpSize-k-rfVVM(j);
                }

                /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
                public float m501toPxR2X_6o(long j) {
                    return this.$$delegate_0.toPx--R2X_6o(j);
                }

                /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
                public float m502toPx0680j_4(float f) {
                    return this.$$delegate_0.toPx-0680j_4(f);
                }

                public Rect toRect(DpRect dpRect) {
                    return this.$$delegate_0.toRect(dpRect);
                }

                /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
                public long m503toSizeXkaWNTQ(long j) {
                    return this.$$delegate_0.toSize-XkaWNTQ(j);
                }

                /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
                public long m504toSp0xMU5do(float f) {
                    return this.$$delegate_0.toSp-0xMU5do(f);
                }

                /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
                public long m505toSpkPz2Gy4(float f) {
                    return this.$$delegate_0.toSp-kPz2Gy4(f);
                }

                /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
                public float m499toDpu2uoSUM(int i) {
                    return this.$$delegate_0.toDp-u2uoSUM(i);
                }

                /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
                public long m506toSpkPz2Gy4(int i) {
                    return this.$$delegate_0.toSp-kPz2Gy4(i);
                }
            }
        }));
    }

    public final Function2<DragAndDropSourceScope, Continuation<? super Unit>, Object> getDragAndDropSourceHandler() {
        return this.dragAndDropSourceHandler;
    }

    public final Function1<DrawScope, Unit> getDrawDragDecoration() {
        return this.drawDragDecoration;
    }

    /* JADX INFO: renamed from: onRemeasured-ozmzZPI, reason: not valid java name */
    public void m492onRemeasuredozmzZPI(long size) {
        this.size = size;
    }

    public final void setDragAndDropSourceHandler(Function2<? super DragAndDropSourceScope, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this.dragAndDropSourceHandler = function2;
    }

    public final void setDrawDragDecoration(Function1<? super DrawScope, Unit> function1) {
        this.drawDragDecoration = function1;
    }
}
