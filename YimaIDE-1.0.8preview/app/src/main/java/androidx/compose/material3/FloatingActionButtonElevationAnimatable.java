package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.material3.internal.ElevationKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0015\u0010\u0010\u001a\u00020\u0003*\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0004\b\u0011\u0010\u0012J0\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0086@¢\u0006\u0004\b\u0015\u0010\u0016J\u000e\u0010\u0017\u001a\u00020\u0014H\u0082@¢\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u000eH\u0086@¢\u0006\u0002\u0010\u001bJ\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001dR\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0004\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0005\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\u0006\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\tR\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/compose/material3/FloatingActionButtonElevationAnimatable;", "", "defaultElevation", "Landroidx/compose/ui/unit/Dp;", "pressedElevation", "hoveredElevation", "focusedElevation", "<init>", "(FFFFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "animatable", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "lastTargetInteraction", "Landroidx/compose/foundation/interaction/Interaction;", "targetInteraction", "calculateTarget", "calculateTarget-u2uoSUM", "(Landroidx/compose/foundation/interaction/Interaction;)F", "updateElevation", "", "updateElevation-lDy3nrA", "(FFFFLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "snapElevation", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "animateElevation", "to", "(Landroidx/compose/foundation/interaction/Interaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "asState", "Landroidx/compose/runtime/State;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class FloatingActionButtonElevationAnimatable {
    private final Animatable<Dp, AnimationVector1D> animatable;
    private float defaultElevation;
    private float focusedElevation;
    private float hoveredElevation;
    private Interaction lastTargetInteraction;
    private float pressedElevation;
    private Interaction targetInteraction;

    /* JADX INFO: renamed from: androidx.compose.material3.FloatingActionButtonElevationAnimatable$animateElevation$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.FloatingActionButtonElevationAnimatable", f = "FloatingActionButton.kt", i = {0}, l = {753}, m = "animateElevation", n = {"to"}, s = {"L$0"})
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
            return FloatingActionButtonElevationAnimatable.this.animateElevation(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.FloatingActionButtonElevationAnimatable$snapElevation$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    @DebugMetadata(c = "androidx.compose.material3.FloatingActionButtonElevationAnimatable", f = "FloatingActionButton.kt", i = {}, l = {739}, m = "snapElevation", n = {}, s = {})
    public static final class C00471 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public C00471(Continuation<? super C00471> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FloatingActionButtonElevationAnimatable.this.snapElevation(this);
        }
    }

    private FloatingActionButtonElevationAnimatable(float f, float f2, float f3, float f4) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.hoveredElevation = f3;
        this.focusedElevation = f4;
        this.animatable = new Animatable<>(Dp.m6020boximpl(this.defaultElevation), VectorConvertersKt.getVectorConverter(Dp.INSTANCE), (Object) null, (String) null, 12, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: renamed from: calculateTarget-u2uoSUM, reason: not valid java name */
    private final float m488calculateTargetu2uoSUM(Interaction interaction) {
        if (interaction instanceof PressInteraction.Press) {
            return this.pressedElevation;
        }
        if (interaction instanceof HoverInteraction.Enter) {
            return this.hoveredElevation;
        }
        return interaction instanceof FocusInteraction.Focus ? this.focusedElevation : this.defaultElevation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object snapElevation(Continuation<? super Unit> continuation) {
        C00471 c00471;
        if (continuation instanceof C00471) {
            c00471 = (C00471) continuation;
            int i = c00471.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00471.label = i - Integer.MIN_VALUE;
            } else {
                c00471 = new C00471(continuation);
            }
        } else {
            c00471 = new C00471(continuation);
        }
        Object obj = c00471.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00471.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                float fM488calculateTargetu2uoSUM = m488calculateTargetu2uoSUM(this.targetInteraction);
                if (!Dp.m6027equalsimpl0(((Dp) this.animatable.getTargetValue()).m6036unboximpl(), fM488calculateTargetu2uoSUM)) {
                    Animatable<Dp, AnimationVector1D> animatable = this.animatable;
                    Dp dpM6020boximpl = Dp.m6020boximpl(fM488calculateTargetu2uoSUM);
                    c00471.label = 1;
                    if (animatable.snapTo(dpM6020boximpl, c00471) == coroutine_suspended) {
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
            this.lastTargetInteraction = this.targetInteraction;
            return Unit.INSTANCE;
        } catch (Throwable th) {
            this.lastTargetInteraction = this.targetInteraction;
            throw th;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object animateElevation(Interaction interaction, Continuation<? super Unit> continuation) {
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        try {
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                float fM488calculateTargetu2uoSUM = m488calculateTargetu2uoSUM(interaction);
                this.targetInteraction = interaction;
                if (!Dp.m6027equalsimpl0(((Dp) this.animatable.getTargetValue()).m6036unboximpl(), fM488calculateTargetu2uoSUM)) {
                    Animatable<Dp, AnimationVector1D> animatable = this.animatable;
                    Interaction interaction2 = this.lastTargetInteraction;
                    anonymousClass1.L$0 = interaction;
                    anonymousClass1.label = 1;
                    if (ElevationKt.m1388animateElevationrAjV9yQ(animatable, fM488calculateTargetu2uoSUM, interaction2, interaction, anonymousClass1) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i2 != 1) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                interaction = (Interaction) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
            }
            this.lastTargetInteraction = interaction;
            this = Unit.INSTANCE;
            return this;
        } catch (Throwable th) {
            this.lastTargetInteraction = interaction;
            throw th;
        }
    }

    public final State<Dp> asState() {
        return this.animatable.asState();
    }

    /* JADX INFO: renamed from: updateElevation-lDy3nrA, reason: not valid java name */
    public final Object m489updateElevationlDy3nrA(float f, float f2, float f3, float f4, Continuation<? super Unit> continuation) {
        this.defaultElevation = f;
        this.pressedElevation = f2;
        this.hoveredElevation = f3;
        this.focusedElevation = f4;
        Object objSnapElevation = snapElevation(continuation);
        return objSnapElevation == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objSnapElevation : Unit.INSTANCE;
    }

    public /* synthetic */ FloatingActionButtonElevationAnimatable(float f, float f2, float f3, float f4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4);
    }
}
