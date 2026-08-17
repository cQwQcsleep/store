package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.collection.LongObjectMapKt;
import androidx.collection.MutableLongObjectMap;
import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.hapticfeedback.HapticFeedbackType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00010B\u007f\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000b\u0012\u0006\u0010\u0011\u001a\u00020\u000b\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0015\u0010\u0016J\b\u0010 \u001a\u00020!H\u0016J{\u0010\"\u001a\u00020\u00052\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00072\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\b#J\f\u0010$\u001a\u00020\u0005*\u00020%H\u0016J\u0017\u0010&\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020(H\u0014¢\u0006\u0004\b)\u0010*J\u0017\u0010+\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020(H\u0014¢\u0006\u0004\b,\u0010*J\b\u0010-\u001a\u00020\u0005H\u0014J\b\u0010.\u001a\u00020\u0005H\u0016J\b\u0010/\u001a\u00020\u0005H\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Landroidx/compose/foundation/CombinedClickableNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/foundation/AbstractClickableNode;", "onClick", "Lkotlin/Function0;", "", "onLongClickLabel", "", "onLongClick", "onDoubleClick", "hapticFeedbackEnabled", "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indicationNodeFactory", "Landroidx/compose/foundation/IndicationNodeFactory;", "useLocalIndication", "enabled", "onClickLabel", "role", "Landroidx/compose/ui/semantics/Role;", "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/foundation/IndicationNodeFactory;ZZLjava/lang/String;Landroidx/compose/ui/semantics/Role;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getHapticFeedbackEnabled", "()Z", "setHapticFeedbackEnabled", "(Z)V", "longKeyPressJobs", "Landroidx/collection/MutableLongObjectMap;", "Lkotlinx/coroutines/Job;", "doubleKeyClickStates", "Landroidx/compose/foundation/CombinedClickableNode$DoubleKeyClickState;", "createPointerInputNodeIfNeeded", "Landroidx/compose/ui/input/pointer/SuspendingPointerInputModifierNode;", "update", "update-2tQrsxU", "applyAdditionalSemantics", "Landroidx/compose/ui/semantics/SemanticsPropertyReceiver;", "onClickKeyDownEvent", "event", "Landroidx/compose/ui/input/key/KeyEvent;", "onClickKeyDownEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "onClickKeyUpEvent", "onClickKeyUpEvent-ZmokQxo", "onCancelKeyInput", "onReset", "resetKeyPressState", "DoubleKeyClickState", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class CombinedClickableNode extends AbstractClickableNode implements CompositionLocalConsumerModifierNode {
    private final MutableLongObjectMap<DoubleKeyClickState> doubleKeyClickStates;
    private boolean hapticFeedbackEnabled;
    private final MutableLongObjectMap<Job> longKeyPressJobs;
    private Function0<Unit> onDoubleClick;
    private Function0<Unit> onLongClick;
    private String onLongClickLabel;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/CombinedClickableNode$DoubleKeyClickState;", "", "job", "Lkotlinx/coroutines/Job;", "<init>", "(Lkotlinx/coroutines/Job;)V", "getJob", "()Lkotlinx/coroutines/Job;", "doubleTapMinTimeMillisElapsed", "", "getDoubleTapMinTimeMillisElapsed", "()Z", "setDoubleTapMinTimeMillisElapsed", "(Z)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class DoubleKeyClickState {
        public static final int $stable = 8;
        private boolean doubleTapMinTimeMillisElapsed;
        private final Job job;

        public DoubleKeyClickState(Job job) {
            this.job = job;
        }

        public final boolean getDoubleTapMinTimeMillisElapsed() {
            return this.doubleTapMinTimeMillisElapsed;
        }

        public final Job getJob() {
            return this.job;
        }

        public final void setDoubleTapMinTimeMillisElapsed(boolean z) {
            this.doubleTapMinTimeMillisElapsed = z;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class AnonymousClass1 implements PointerInputEventHandler {

        /* JADX INFO: renamed from: androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/PressGestureScope;", "offset", "Landroidx/compose/ui/geometry/Offset;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.CombinedClickableNode$createPointerInputNodeIfNeeded$1$3", f = "Clickable.kt", i = {}, l = {1047}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        public static final class AnonymousClass3 extends SuspendLambda implements Function3<PressGestureScope, Offset, Continuation<? super Unit>, Object> {
            /* synthetic */ long J$0;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ CombinedClickableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(CombinedClickableNode combinedClickableNode, Continuation<? super AnonymousClass3> continuation) {
                super(3, continuation);
                this.this$0 = combinedClickableNode;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
                return m378invoked4ec7I((PressGestureScope) obj, ((Offset) obj2).unbox-impl(), (Continuation) obj3);
            }

            /* JADX INFO: renamed from: invoke-d-4ec7I, reason: not valid java name */
            public final Object m378invoked4ec7I(PressGestureScope pressGestureScope, long j, Continuation<? super Unit> continuation) {
                AnonymousClass3 anonymousClass3 = new AnonymousClass3(this.this$0, continuation);
                anonymousClass3.L$0 = pressGestureScope;
                anonymousClass3.J$0 = j;
                return anonymousClass3.invokeSuspend(Unit.INSTANCE);
            }

            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    PressGestureScope pressGestureScope = (PressGestureScope) this.L$0;
                    long j = this.J$0;
                    if (this.this$0.getEnabled()) {
                        CombinedClickableNode combinedClickableNode = this.this$0;
                        this.label = 1;
                        if (combinedClickableNode.m286handlePressInteractiond4ec7I(pressGestureScope, j, this) == coroutine_suspended) {
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

        public static Unit a(CombinedClickableNode combinedClickableNode, Offset offset) {
            if (combinedClickableNode.getEnabled()) {
                combinedClickableNode.getOnClick().invoke();
            }
            return Unit.INSTANCE;
        }

        public static Unit b(CombinedClickableNode combinedClickableNode, Offset offset) {
            Function0 function0 = combinedClickableNode.onDoubleClick;
            if (function0 != null) {
                function0.invoke();
            }
            return Unit.INSTANCE;
        }

        public static Unit c(CombinedClickableNode combinedClickableNode, Offset offset) {
            Function0 function0 = combinedClickableNode.onLongClick;
            if (function0 != null) {
                function0.invoke();
            }
            if (combinedClickableNode.getHapticFeedbackEnabled()) {
                ((HapticFeedback) CompositionLocalConsumerModifierNodeKt.currentValueOf(combinedClickableNode, CompositionLocalsKt.getLocalHapticFeedback())).performHapticFeedback-CdsT49E(HapticFeedbackType.Companion.getLongPress-5zf0vsI());
            }
            return Unit.INSTANCE;
        }

        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            Function1 function1;
            Function1 function2;
            if (!CombinedClickableNode.this.getEnabled() || CombinedClickableNode.this.onDoubleClick == null) {
                function1 = null;
            } else {
                final CombinedClickableNode combinedClickableNode = CombinedClickableNode.this;
                function1 = new Function1() { // from class: androidx.compose.foundation.f
                    public final Object invoke(Object obj) {
                        return CombinedClickableNode.AnonymousClass1.b(combinedClickableNode, (Offset) obj);
                    }
                };
            }
            if (!CombinedClickableNode.this.getEnabled() || CombinedClickableNode.this.onLongClick == null) {
                function2 = null;
            } else {
                final CombinedClickableNode combinedClickableNode2 = CombinedClickableNode.this;
                function2 = new Function1() { // from class: androidx.compose.foundation.g
                    public final Object invoke(Object obj) {
                        return CombinedClickableNode.AnonymousClass1.c(combinedClickableNode2, (Offset) obj);
                    }
                };
            }
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(CombinedClickableNode.this, null);
            final CombinedClickableNode combinedClickableNode3 = CombinedClickableNode.this;
            Object objDetectTapGestures = TapGestureDetectorKt.detectTapGestures(pointerInputScope, function1, function2, anonymousClass3, new Function1() { // from class: androidx.compose.foundation.h
                public final Object invoke(Object obj) {
                    return CombinedClickableNode.AnonymousClass1.a(combinedClickableNode3, (Offset) obj);
                }
            }, continuation);
            return objDetectTapGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objDetectTapGestures : Unit.INSTANCE;
        }
    }

    private CombinedClickableNode(Function0<Unit> function0, String str, Function0<Unit> function1, Function0<Unit> function2, boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, boolean z3, String str2, Role role) {
        super(mutableInteractionSource, indicationNodeFactory, z2, z3, str2, role, function0, null);
        this.onLongClickLabel = str;
        this.onLongClick = function1;
        this.onDoubleClick = function2;
        this.hapticFeedbackEnabled = z;
        this.longKeyPressJobs = LongObjectMapKt.mutableLongObjectMapOf();
        this.doubleKeyClickStates = LongObjectMapKt.mutableLongObjectMapOf();
    }

    public static boolean d(CombinedClickableNode combinedClickableNode) {
        Function0<Unit> function0 = combinedClickableNode.onLongClick;
        if (function0 == null) {
            return true;
        }
        function0.invoke();
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0048 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:15:0x004a A[LOOP:0: B:5:0x0018->B:15:0x004a, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:29:0x0094 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x0096 A[LOOP:2: B:20:0x0065->B:30:0x0096, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:34:0x0056 A[EDGE_INSN: B:34:0x0056->B:17:0x0056 BREAK  A[LOOP:0: B:5:0x0018->B:15:0x004a], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:39:0x0099 A[EDGE_INSN: B:39:0x0099->B:31:0x0099 BREAK  A[LOOP:2: B:20:0x0065->B:30:0x0096], SYNTHETIC] */
    private final void resetKeyPressState() {
        long j;
        long j2;
        long j3;
        MutableLongObjectMap<Job> mutableLongObjectMap = this.longKeyPressJobs;
        Object[] objArr = mutableLongObjectMap.values;
        long[] jArr = mutableLongObjectMap.metadata;
        int length = jArr.length - 2;
        if (length >= 0) {
            int i = 0;
            j = 128;
            j2 = 255;
            while (true) {
                long j4 = jArr[i];
                j3 = -9187201950435737472L;
                if ((((~j4) << 7) & j4 & (-9187201950435737472L)) == -9187201950435737472L) {
                    if (i != length) {
                        break;
                        break;
                    }
                    i++;
                } else {
                    int i2 = 8 - ((~(i - length)) >>> 31);
                    for (int i3 = 0; i3 < i2; i3++) {
                        if ((j4 & 255) < 128) {
                            Job.DefaultImpls.cancel$default((Job) objArr[(i << 3) + i3], (CancellationException) null, 1, (Object) null);
                        }
                        j4 >>= 8;
                    }
                    if (i2 != 8) {
                        break;
                    } else if (i != length) {
                        break;
                    } else {
                        i++;
                    }
                }
            }
        } else {
            j = 128;
            j2 = 255;
            j3 = -9187201950435737472L;
        }
        mutableLongObjectMap.clear();
        MutableLongObjectMap<DoubleKeyClickState> mutableLongObjectMap2 = this.doubleKeyClickStates;
        Object[] objArr2 = mutableLongObjectMap2.values;
        long[] jArr2 = mutableLongObjectMap2.metadata;
        int length2 = jArr2.length - 2;
        if (length2 >= 0) {
            int i4 = 0;
            while (true) {
                long j5 = jArr2[i4];
                if ((((~j5) << 7) & j5 & j3) == j3) {
                    if (i4 != length2) {
                        break;
                        break;
                    }
                    i4++;
                } else {
                    int i5 = 8 - ((~(i4 - length2)) >>> 31);
                    for (int i6 = 0; i6 < i5; i6++) {
                        if ((j5 & j2) < j) {
                            Job.DefaultImpls.cancel$default(((DoubleKeyClickState) objArr2[(i4 << 3) + i6]).getJob(), (CancellationException) null, 1, (Object) null);
                        }
                        j5 >>= 8;
                    }
                    if (i5 != 8) {
                        break;
                    } else if (i4 != length2) {
                        break;
                    } else {
                        i4++;
                    }
                }
            }
        }
        mutableLongObjectMap2.clear();
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public void applyAdditionalSemantics(SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (this.onLongClick != null) {
            SemanticsPropertiesKt.onLongClick(semanticsPropertyReceiver, this.onLongClickLabel, new Function0() { // from class: androidx.compose.foundation.e
                public final Object invoke() {
                    return Boolean.valueOf(CombinedClickableNode.d(this.b));
                }
            });
        }
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public SuspendingPointerInputModifierNode createPointerInputNodeIfNeeded() {
        return SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1());
    }

    public final boolean getHapticFeedbackEnabled() {
        return this.hapticFeedbackEnabled;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    public void onCancelKeyInput() {
        resetKeyPressState();
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyDownEvent-ZmokQxo */
    public boolean mo289onClickKeyDownEventZmokQxo(KeyEvent event) {
        boolean z;
        long j = KeyEvent_androidKt.getKey-ZmokQxo(event);
        if (this.onLongClick == null || this.longKeyPressJobs.get(j) != null) {
            z = false;
        } else {
            this.longKeyPressJobs.set(j, BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new CombinedClickableNode$onClickKeyDownEvent$1(this, null), 3, (Object) null));
            z = true;
        }
        DoubleKeyClickState doubleKeyClickState = this.doubleKeyClickStates.get(j);
        if (doubleKeyClickState != null) {
            if (doubleKeyClickState.getJob().isActive()) {
                Job.DefaultImpls.cancel$default(doubleKeyClickState.getJob(), (CancellationException) null, 1, (Object) null);
                if (!doubleKeyClickState.getDoubleTapMinTimeMillisElapsed()) {
                    getOnClick().invoke();
                    this.doubleKeyClickStates.remove(j);
                    return z;
                }
            } else {
                this.doubleKeyClickStates.remove(j);
            }
        }
        return z;
    }

    @Override // androidx.compose.foundation.AbstractClickableNode
    /* JADX INFO: renamed from: onClickKeyUpEvent-ZmokQxo */
    public boolean mo290onClickKeyUpEventZmokQxo(KeyEvent event) {
        Function0<Unit> function0;
        long j = KeyEvent_androidKt.getKey-ZmokQxo(event);
        boolean z = false;
        if (this.longKeyPressJobs.get(j) != null) {
            Job job = this.longKeyPressJobs.get(j);
            if (job != null) {
                if (job.isActive()) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                } else {
                    z = true;
                }
            }
            this.longKeyPressJobs.remove(j);
        }
        if (this.onDoubleClick != null) {
            if (this.doubleKeyClickStates.get(j) != null) {
                if (!z && (function0 = this.onDoubleClick) != null) {
                    function0.invoke();
                }
                this.doubleKeyClickStates.remove(j);
            } else if (!z) {
                this.doubleKeyClickStates.set(j, new DoubleKeyClickState(BuildersKt.launch$default(getCoroutineScope(), (CoroutineContext) null, (CoroutineStart) null, new CombinedClickableNode$onClickKeyUpEvent$2(this, j, null), 3, (Object) null)));
            }
        } else if (!z) {
            getOnClick().invoke();
        }
        return true;
    }

    public void onReset() {
        super/*androidx.compose.ui.Modifier.Node*/.onReset();
        resetKeyPressState();
    }

    public final void setHapticFeedbackEnabled(boolean z) {
        this.hapticFeedbackEnabled = z;
    }

    /* JADX INFO: renamed from: update-2tQrsxU, reason: not valid java name */
    public final void m377update2tQrsxU(Function0<Unit> onClick, String onLongClickLabel, Function0<Unit> onLongClick, Function0<Unit> onDoubleClick, MutableInteractionSource interactionSource, IndicationNodeFactory indicationNodeFactory, boolean useLocalIndication, boolean enabled, String onClickLabel, Role role) {
        boolean z;
        if (!Intrinsics.areEqual(this.onLongClickLabel, onLongClickLabel)) {
            this.onLongClickLabel = onLongClickLabel;
            SemanticsModifierNodeKt.invalidateSemantics(this);
        }
        if ((this.onLongClick == null) != (onLongClick == null)) {
            disposeInteractions();
            SemanticsModifierNodeKt.invalidateSemantics(this);
            z = true;
        } else {
            z = false;
        }
        this.onLongClick = onLongClick;
        if ((this.onDoubleClick == null) != (onDoubleClick == null)) {
            z = true;
        }
        this.onDoubleClick = onDoubleClick;
        boolean z2 = getEnabled() == enabled ? z : true;
        m294updateCommonO2vRcR0(interactionSource, indicationNodeFactory, useLocalIndication, enabled, onClickLabel, role, onClick);
        if (z2) {
            resetPointerInputHandler();
        }
    }

    public /* synthetic */ CombinedClickableNode(Function0 function0, String str, Function0 function1, Function0 function2, boolean z, MutableInteractionSource mutableInteractionSource, IndicationNodeFactory indicationNodeFactory, boolean z2, boolean z3, String str2, Role role, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, str, function1, function2, z, mutableInteractionSource, indicationNodeFactory, z2, z3, str2, role);
    }
}
