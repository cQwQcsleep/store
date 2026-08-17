package androidx.compose.foundation;

import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.node.PointerInputModifierNode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004J'\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\nH\u0016J\u000e\u0010\u0016\u001a\u00020\nH\u0082@¢\u0006\u0002\u0010\u0017J\u000e\u0010\u0018\u001a\u00020\nH\u0082@¢\u0006\u0002\u0010\u0017J\b\u0010\u0019\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/HoverableNode;", "Landroidx/compose/ui/node/PointerInputModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "<init>", "(Landroidx/compose/foundation/interaction/MutableInteractionSource;)V", "hoverInteraction", "Landroidx/compose/foundation/interaction/HoverInteraction$Enter;", "updateInteractionSource", "", "onPointerEvent", "pointerEvent", "Landroidx/compose/ui/input/pointer/PointerEvent;", "pass", "Landroidx/compose/ui/input/pointer/PointerEventPass;", "bounds", "Landroidx/compose/ui/unit/IntSize;", "onPointerEvent-H0pRuoY", "(Landroidx/compose/ui/input/pointer/PointerEvent;Landroidx/compose/ui/input/pointer/PointerEventPass;J)V", "onCancelPointerInput", "onDetach", "emitEnter", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitExit", "tryEmitExit", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class HoverableNode extends Modifier.Node implements PointerInputModifierNode {
    private HoverInteraction.Enter hoverInteraction;
    private MutableInteractionSource interactionSource;

    /* JADX INFO: renamed from: androidx.compose.foundation.HoverableNode$emitEnter$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.HoverableNode", f = "Hoverable.kt", i = {0}, l = {106}, m = "emitEnter", n = {"interaction"}, s = {"L$0"}, v = 1)
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
            return HoverableNode.this.emitEnter(this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.HoverableNode$emitExit$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.HoverableNode", f = "Hoverable.kt", i = {}, l = {114}, m = "emitExit", n = {}, s = {}, v = 1)
    public static final class C00971 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C00971(Continuation<? super C00971> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return HoverableNode.this.emitExit(this);
        }
    }

    public HoverableNode(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object emitEnter(Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        HoverInteraction.Enter enter;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.hoverInteraction == null) {
                HoverInteraction.Enter enter2 = new HoverInteraction.Enter();
                MutableInteractionSource mutableInteractionSource = this.interactionSource;
                anonymousClass1.L$0 = enter2;
                anonymousClass1.label = 1;
                if (mutableInteractionSource.emit(enter2, anonymousClass1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                enter = enter2;
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        enter = (HoverInteraction.Enter) anonymousClass1.L$0;
        ResultKt.throwOnFailure(obj);
        this.hoverInteraction = enter;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object emitExit(Continuation<? super Unit> continuation) {
        C00971 c00971;
        if (continuation instanceof C00971) {
            c00971 = (C00971) continuation;
            int i = c00971.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00971.label = i - Integer.MIN_VALUE;
            } else {
                c00971 = new C00971(continuation);
            }
        } else {
            c00971 = new C00971(continuation);
        }
        Object obj = c00971.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00971.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            HoverInteraction.Enter enter = this.hoverInteraction;
            if (enter != null) {
                HoverInteraction.Exit exit = new HoverInteraction.Exit(enter);
                MutableInteractionSource mutableInteractionSource = this.interactionSource;
                c00971.label = 1;
                if (mutableInteractionSource.emit(exit, c00971) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            k2d.a("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
        ResultKt.throwOnFailure(obj);
        this.hoverInteraction = null;
        return Unit.INSTANCE;
    }

    private final void tryEmitExit() {
        HoverInteraction.Enter enter = this.hoverInteraction;
        if (enter != null) {
            this.interactionSource.tryEmit(new HoverInteraction.Exit(enter));
            this.hoverInteraction = null;
        }
    }

    public void onCancelPointerInput() {
        tryEmitExit();
    }

    public void onDetach() {
        tryEmitExit();
    }

    /* JADX INFO: renamed from: onPointerEvent-H0pRuoY, reason: not valid java name */
    public void m388onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pass, long bounds) {
        if (pass == PointerEventPass.Main) {
            int i = pointerEvent.getType-7fucELk();
            PointerEventType.Companion companion = PointerEventType.Companion;
            if (PointerEventType.equals-impl0(i, companion.getEnter-7fucELk())) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new HoverableNode$onPointerEvent$1(this, null), 3, (Object) null);
            } else if (PointerEventType.equals-impl0(i, companion.getExit-7fucELk())) {
                BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new HoverableNode$onPointerEvent$2(this, null), 3, (Object) null);
            }
        }
    }

    public final void updateInteractionSource(MutableInteractionSource interactionSource) {
        if (Intrinsics.areEqual(this.interactionSource, interactionSource)) {
            return;
        }
        tryEmitExit();
        this.interactionSource = interactionSource;
    }
}
